package com.kodlamaio.invoiceservice.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateInvoiceRequest {
	private String paymentId;
	
	private String customerFirstName;
	
	private String customerLastName;

	private double tax;
	
	private double totalPrice;
	
	private String address;
}
