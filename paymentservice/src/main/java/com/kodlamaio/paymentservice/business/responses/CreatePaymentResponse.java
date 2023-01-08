package com.kodlamaio.paymentservice.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentResponse {
	private String id;
	private String cardNo;

	private String cardHolder;

	private int cvv;

	private String rentalId;

	private double balance;
	private int status;
}
