package com.kodlamaio.rentalService.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
	private String carId;
	
	private int rentedForDays;
	private double dailyPrice;
}
