package com.kodlamaio.common.events.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCreatedEvent {

    private String message;
    private String carId;
    private String brandId;
    private String brandName;
    private String modelId;
    private String modelName;
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private int state;
}
