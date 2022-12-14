package com.kodlamaio.rentalService.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
	
	private String carId;

	private int rentedForDays;

	private double dailyPrice;
	
}
