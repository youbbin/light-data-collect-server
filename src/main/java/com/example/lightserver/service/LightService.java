package com.example.lightserver.service;

import com.example.lightserver.dto.SensorDto;
import com.example.lightserver.entity.Light;
import com.example.lightserver.entity.LightAvg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.query.Update;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
@Slf4j
@Service
@RequiredArgsConstructor
public class LightService {

    @Qualifier("mongoTemplate")
    private final MongoTemplate mongoTemplate;

    @Qualifier("mongoTemplate1m")
    private final MongoTemplate mongoTemplate1m;

//    @Qualifier("mongoTemplate_fix")
//    private final MongoTemplate mongoTemplate_fix;

    private final Calibration calibration;

    /*
        1초마다의 센싱 데이터 저장
     */
    public String save(SensorDto sensorDto) {

        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sensorDto.getDatetime(), input);

        Query query = new Query(Criteria.where("_id").is(dateTime.withNano(0)));
        Update update = new Update()
                .set("cct_" + sensorDto.getSensorId(), calibration.getCct(sensorDto.getSensorId(), sensorDto.getR(), sensorDto.getG(), sensorDto.getB(), sensorDto.getC()))
                .set("illum_" + sensorDto.getSensorId(), calibration.getTriY(sensorDto.getSensorId(), sensorDto.getR(), sensorDto.getG(), sensorDto.getB(), sensorDto.getC()))
                .set("r_" + sensorDto.getSensorId(), sensorDto.getR())
                .set("g_" + sensorDto.getSensorId(), sensorDto.getG())
                .set("b_" + sensorDto.getSensorId(), sensorDto.getB())
                .set("c_" + sensorDto.getSensorId(), sensorDto.getC());

        mongoTemplate.upsert(query, update, "light");
        log.info(sensorDto.getDatetime() + " >> Sensor " + sensorDto.getSensorId() + " Save Completed!");

        // 센서 보정용
//        if(sensorDto.getSensorId() == 5){ // 보정할 센서 번호
//            mongoTemplate_fix.upsert(query, update, "light");
//        }

        return "Save Completed !";
    }

    public Light getStatus() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        Query query = new Query(Criteria.where("_id").is(now));

        Light test = mongoTemplate1m.findOne(query, Light.class, "light");
        return test;

    }

    /*
        1분 평균을 lab_401_1m_db에 따로 저장
     */
    @Scheduled(cron = "30 * * * * *") // 매일 매시 매분 30초마다 실행
    public void saveAverages1m() throws ClassNotFoundException {
        int sensor_num = 9;
        Class<?> lightClass = Class.forName("com.example.lightserver.entity.Light");
        Class<?> lightAvgClass = Class.forName("com.example.lightserver.entity.LightAvg");

        // 현재 시간 -1분 데이터 정리
        LocalDateTime oneMinuteAgoFrom = LocalDateTime.now().minusMinutes(1).withSecond(0).withNano(0);
        LocalDateTime oneMinuteAgoTo = oneMinuteAgoFrom.plusMinutes(1).withSecond(0).withNano(0);
        Query query = new Query(Criteria.where("_id").gte(oneMinuteAgoFrom).lt(oneMinuteAgoTo));
        List<Light> dataWithinOneMinute = mongoTemplate.find(query, Light.class, "light");

        if (!dataWithinOneMinute.isEmpty()) {

            double[] sumIlluminance = new double[sensor_num];
            double[] sumCct = new double[sensor_num];

            double[] averageIlluminance = new double[sensor_num];
            double[] averageCct = new double[sensor_num];

            int totalCount = 0;
            Arrays.fill(sumIlluminance, 0.0);
            Arrays.fill(sumCct, 0.0);

            for (int i = 0; i < sensor_num; i++) {
                totalCount = 0;
                for (Light light : dataWithinOneMinute) {
                    try{
                        // 1분 동안의 조도 가져와서 총합 계산
                        Method getIllumMethod = lightClass.getMethod("getIllum_" + (i + 1));
                        double illumValue = (double) getIllumMethod.invoke(light);
                        sumIlluminance[i] += illumValue;

                        // 1분 동안의 색온도 가져와서 총합 계산
                        Method getCctMethod = lightClass.getMethod("getCct_"+ (i + 1));
                        double cctValue = (double) getCctMethod.invoke(light);
                        sumCct[i] += cctValue;

                        totalCount++;
                    }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        log.error(e.getMessage());
                    }
                }

                // 평균 계산
                averageIlluminance[i] = sumIlluminance[i] / totalCount;
                averageCct[i] = sumCct[i] / totalCount;
            }


            // LightAvg 객체에 할당
            LightAvg lightAvg = new LightAvg();
            lightAvg.set_id(oneMinuteAgoFrom);

            for (int i = 0; i < sensor_num; i++) {
                try{
                    Method setIllumAvgMethod = lightAvgClass.getMethod("setIllum_" + (i + 1), double.class);
                    setIllumAvgMethod.invoke(lightAvg, averageIlluminance[i]);
                    Method setCctAvgMethod = lightAvgClass.getMethod("setCct_" + (i + 1), double.class);
                    setCctAvgMethod.invoke(lightAvg, averageCct[i]);

                }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }


            // mongodb에 저장
            mongoTemplate1m.insert(lightAvg,"light");

            log.info(oneMinuteAgoFrom+" >> ****** 1m Data Save Completed! ******");
        }
    }
}
