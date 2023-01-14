package com.kodlamaio.common.events.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreatedEvent {
	private String carId;
	private String fullName;
//	private String modelName;
//
//	private String brandName;
//
//	private int modelYear;

	private double dailyPrice;
	private double totalPrice;
	private int rentedForDays;
	private LocalDate rentedDate;
}
