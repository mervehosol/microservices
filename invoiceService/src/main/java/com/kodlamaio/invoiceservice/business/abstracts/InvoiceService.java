package com.kodlamaio.invoiceservice.business.abstracts;

import java.util.List;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.responses.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.entities.Invoice;



public interface InvoiceService {
	Invoice add(PaymentCreatedEvent paymentCreatedEvent);
	 GetInvoiceResponse getById(String id);
	    CreateInvoiceResponse add(CreateInvoiceRequest request);
	    UpdateInvoiceResponse update(UpdateInvoiceRequest request, String id);
	    void delete(String id);
	    void createInvoice(Invoice invoice);
		List<GetAllInvoicesResponse> getAll();
	


}
