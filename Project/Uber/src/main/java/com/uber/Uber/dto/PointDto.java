package com.uber.Uber.dto;

import lombok.Data;

@Data
public class PointDto {
    private Double[] coordinates;
    private String type ="Point";



}
