package com.example.lightserver.controller;

import com.example.lightserver.dto.SensorDto;
import com.example.lightserver.service.lightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class SensorController {
    private final lightService lightService;
    @PostMapping("/illum")
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
}
