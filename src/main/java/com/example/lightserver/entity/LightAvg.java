package com.example.lightserver.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "light")
public class LightAvg {
    @Id
    private LocalDateTime _id;
    private double illum_1;
    private double cct_1;
    private double illum_2;
    private double cct_2;
    private double illum_3;
    private double cct_3;
    private double illum_4;
    private double cct_4;
    private double illum_5;
    private double cct_5;
    private double illum_6;
    private double cct_6;
    private double illum_7;
    private double cct_7;
    private double illum_8;
    private double cct_8;
    private double illum_9;
    private double cct_9;
}
