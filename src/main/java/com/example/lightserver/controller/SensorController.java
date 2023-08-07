package com.example.lightserver.controller;

import com.example.lightserver.dto.SensorDto;
import com.example.lightserver.entity.Light;
import com.example.lightserver.service.LightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SensorController {
    private final LightService lightService;
    @PostMapping("/light/save")
    public String save(@RequestBody SensorDto sensorDto){
        return lightService.save(sensorDto);
    }

    @PostMapping("/test")
    public String test(@RequestBody String str){
        return str;
    }

     @GetMapping("/test")
    public String getTest(){
        log.info("test");
        return "test";
    }

    @GetMapping("/light/status")
    public Light getStatus(){
        return lightService.getStatus();
    }
}
