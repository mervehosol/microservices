package com.kodlamaio.invoiceservice.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceservice.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public  class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;

	@Override
	public Invoice add(PaymentCreatedEvent paymentCreatedEvent) {
		Invoice invoice=new Invoice();
		invoice.setId(UUID.randomUUID().toString());
		invoice.setRentalId(paymentCreatedEvent.getRentalId());
		
		Invoice result= this.invoiceRepository.save(invoice);
		return result;
	}


	
	
}
