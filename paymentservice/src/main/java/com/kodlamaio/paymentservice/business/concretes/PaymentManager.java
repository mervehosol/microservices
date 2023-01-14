package com.kodlamaio.paymentservice.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.adapters.PosCheckService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.constants.Messages;
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
	private PosCheckService posCheckService;

	@Override
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) { // sisteme card bilgilerini
																					// kaydediyoruz
		//checkIfCardNumberExists(createPaymentRequest.getCardNo());
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		paymentRepository.save(payment);
		CreatePaymentResponse createPaymentResponse = modelMapperService.forResponse().map(payment, CreatePaymentResponse.class);

		return createPaymentResponse;
	}

	@Override
	public void paymentReceived(String cardNo, String cardHolder, String cvv, double balance) {
		checkIfRentalBalance(cardNo, cardHolder, cvv, balance);// card bilgisi kontrol ve ödeme işlemi

	}

	private void checkIfRentalBalance(String cardNo, String cardHolder, String cvv, double totalPrice) {
		Payment payment = this.paymentRepository.findByCardNoAndCardHolderAndCvv(cardNo, cardHolder, cvv);
		if (payment == null) {
			throw new BusinessException(Messages.InvalidPayment);
		}
		double amount = this.paymentRepository.findByCardNo(cardNo).getBalance();
		if (amount < totalPrice) {
			throw new BusinessException(Messages.InsufficientBalance);
		}
		posCheckService.pay();
		Payment payment2 = this.paymentRepository.findByCardNo(cardNo);
		payment2.setBalance(amount - totalPrice);
		this.paymentRepository.save(payment2);
	}

	@Override
	 public void delete(String id) {
        checkIfPaymentExists(id);
        paymentRepository.deleteById(id);
    }

	private void checkIfPaymentExists(String id) {
		  if (!paymentRepository.existsById(id)) {
	            throw new BusinessException(Messages.PaymentNotFound);
	        }
	    }
}
