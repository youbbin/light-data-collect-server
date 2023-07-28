package com.example.lightserver.dto;

import lombok.Getter;

import java.util.Date;
@Getter
public class SensorDto {
    private int sensorId;
    private int r;
    private int g;
    private int b;
    private int c;
    private String datetime;
}
