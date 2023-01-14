package com.kodlamaio.invoiceservice.business.abstracts;

import java.util.List;

import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.entities.Invoice;

public interface InvoiceService {
	CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);

	//UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);

	//void delete(String id);

	List<GetAllInvoicesResponse> getAll();

	void createInvoice(Invoice invoice);

}
