package com.example.lightserver.dto;

import lombok.Getter;

import java.util.Date;
@Getter
public class SensorDto {
    private int sensorId;
    private double illum;
    private double cct;
    private String datetime;
}
