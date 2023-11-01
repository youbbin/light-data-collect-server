package com.example.lightserver.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "light")
public class Light {
    @Id
    private LocalDateTime datetime;
    private int r_1;
    private int g_1;
    private int b_1;
    private int c_1;
    private double illum_1;
    private double cct_1;

    private int r_2;
    private int g_2;
    private int b_2;
    private int c_2;
    private double illum_2;
    private double cct_2;

    private int r_3;
    private int g_3;
    private int b_3;
    private int c_3;
    private double illum_3;
    private double cct_3;

    private int r_4;
    private int g_4;
    private int b_4;
    private int c_4;
    private double illum_4;
    private double cct_4;

    private int r_5;
    private int g_5;
    private int b_5;
    private int c_5;
    private double illum_5;
    private double cct_5;

    private int r_6;
    private int g_6;
    private int b_6;
    private int c_6;
    private double illum_6;
    private double cct_6;

    private int r_7;
    private int g_7;
    private int b_7;
    private int c_7;
    private double illum_7;
    private double cct_7;

    private int r_8;
    private int g_8;
    private int b_8;
    private int c_8;
    private double illum_8;
    private double cct_8;

    private int r_9;
    private int g_9;
    private int b_9;
    private int c_9;
    private double illum_9;
    private double cct_9;
}
