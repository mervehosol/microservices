package com.kodlamaio.rentalService.business.responses.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalResponse {
	@NotNull
	@NotEmpty
	private String carId;
	@NotNull
	private int rentedForDays;
	@NotNull
	private double dailyPrice;
}
