package com.kodlamaio.invoiceservice.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.common.events.invoice.InvoiceCreatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.responses.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceservice.entities.Invoice;
import com.kodlamaio.invoiceservice.kafka.InvoiceProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public  class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	 private ModelMapperService modelMapperService;
	    private InvoiceProducer invoiceProducer;
		
	

	@Override
	public Invoice add(PaymentCreatedEvent paymentCreatedEvent) {
		Invoice invoice=new Invoice();
		invoice.setId(UUID.randomUUID().toString());
		invoice.setRentalId(paymentCreatedEvent.getRentalId());
		
		Invoice result= this.invoiceRepository.save(invoice);
		return result;
	}

	@Override
	public GetInvoiceResponse getById(String id) {
		return null;
	}

	@Override
	public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setId(UUID.randomUUID().toString());
		
		Invoice createdInvoice = invoiceRepository.save(invoice);
		
		InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
		invoiceCreatedEvent.setPaymentId(createdInvoice.getPaymentId());
		invoiceCreatedEvent.setMessage("Payment Created");
		
		this.invoiceProducer.sendMessage(invoiceCreatedEvent);
		
		CreateInvoiceResponse createPaymentResponse = modelMapperService.forResponse().map(invoice, CreateInvoiceResponse.class);
		
		return createPaymentResponse;
	}

	@Override
	public UpdateInvoiceResponse update(UpdateInvoiceRequest request, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GetAllInvoicesResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
