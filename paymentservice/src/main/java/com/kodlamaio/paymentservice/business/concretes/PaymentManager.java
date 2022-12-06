package com.kodlamaio.paymentservice.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.api.controller.RentalApi;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;
import com.kodlamaio.paymentservice.entities.Payment;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
	private PaymentRepository paymentRepository;
	private ModelMapperService modelMapperService;
	private RentalApi rentalApi;

	@Override
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());

		Payment createdPayment = paymentRepository.save(payment);

		PaymentCreatedEvent paymentCreatedEvent = new PaymentCreatedEvent();
		paymentCreatedEvent.setRentalId(createdPayment.getRentalId());
		paymentCreatedEvent.setMessage("");

		CreatePaymentResponse createPaymentResponse = modelMapperService.forResponse().map(payment, CreatePaymentResponse.class);

		return createPaymentResponse;
	}
	private void checkBalanceEnough(double balance, String rentalId) {
		if (balance<rentalApi.getTotalPrice(rentalId)) {
			throw new BusinessException("BALANCE.IS.NOT.ENOUGH");
		}
	}

}
