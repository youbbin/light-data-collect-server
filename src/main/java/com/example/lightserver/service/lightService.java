package com.example.lightserver.service;

import com.example.lightserver.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.query.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class lightService {

    private final MongoTemplate mongoTemplate;
    private final Calibration calibration;
    public String save(SensorDto sensorDto) {

        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sensorDto.getDatetime(), input);

        Query query = new Query(Criteria.where("datetime").is(dateTime));
        Update update = new Update()
                .set("cct_" + sensorDto.getSensorId(), calibration.getCct(sensorDto.getSensorId(), sensorDto.getR(), sensorDto.getG(), sensorDto.getB()))
                .set("illum_" + sensorDto.getSensorId(), calibration.getTriY(sensorDto.getSensorId(), sensorDto.getR(), sensorDto.getG(), sensorDto.getB()))
                .set("r_" + sensorDto.getSensorId(), sensorDto.getR())
                .set("g_" + sensorDto.getSensorId(), sensorDto.getG())
                .set("b_" + sensorDto.getSensorId(), sensorDto.getB())
                .set("c_" + sensorDto.getSensorId(), sensorDto.getC());

        mongoTemplate.upsert(query, update, "light");

        log.info("Sensor Datetime : " + sensorDto.getDatetime());
        return "Save Completed !";
    }


}
