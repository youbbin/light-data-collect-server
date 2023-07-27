package com.example.lightserver.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class Light {
    private int sensorId;
    private double illum;
    private double cct;
    private LocalDateTime datetime;
}
