package com.kodlamaio.paymentservice.business.abstracts;

import com.kodlamaio.paymentservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;

public interface PaymentService {

	CreatePaymentResponse add(CreatePaymentRequest request);

	void paymentReceived(String cardNo,String cardHolder,String cvv,double balance); 
	void delete(String id);
}
