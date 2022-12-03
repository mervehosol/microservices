package com.kodlamaio.paymentservice.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePaymentRequest {

	private String cardNo;

	private String cardHolder;

	private int cvv;

	private String rentalId;

	private double balance;

}
