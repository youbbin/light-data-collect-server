package com.example.lightserver.service;
import com.example.lightserver.dto.SensorDto;
import com.example.lightserver.entity.Light;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class lightService {

    private final MongoTemplate mongoTemplate;
    public String save(SensorDto sensorDto){

        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sensorDto.getDatetime(), input);
        Light light = Light.builder()
                .sensorId(sensorDto.getSensorId())
                .illum(sensorDto.getIllum())
                .cct(sensorDto.getCct())
                .datetime(dateTime).build();
        log.info("Sensor Datetime : "+light.getDatetime());
        mongoTemplate.save(light);
        return "Save Completed !";
    }
}
