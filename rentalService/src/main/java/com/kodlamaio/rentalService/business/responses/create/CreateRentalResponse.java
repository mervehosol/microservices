package com.kodlamaio.rentalService.business.responses.create;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalResponse {
	private String id;
	private LocalDateTime dataStarted;
	private int rentedForDays;
	private double dailyPrice;
	private double totalPrice;
}
