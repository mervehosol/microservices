package com.kodlamaio.filterservice.business.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFiltersResponse {
	private String id;
    private String carId;
    private String modelId;
    private String brandId;
    private String modelName;
    private String brandName;
    private double dailyPrice;
    private int modelYear;
    private String plate;
    private int state;

}
