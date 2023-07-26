package com.example.lightserver.service;
import com.example.lightserver.dto.SensorDto;
import org.springframework.stereotype.Service;

@Service
public class IllumService {
    public String save(SensorDto sensorDto){
        return "Save Completed !";
    }
}
