package com.kodlamaio.rentalService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.common.rentalPayment.PayMoneyRequest;

import feign.Headers;

@FeignClient(value="paymentClient", url="http://localhost:9011/payment/api/payments")

public interface PaymentClient {
	@RequestMapping(method= RequestMethod.POST ,value = "") 
	@Headers(value="Content-Type: application/json")
	Object add(@RequestBody PayMoneyRequest payMoneyRequest);
}
