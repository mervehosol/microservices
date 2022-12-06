package com.kodlamaio.invoiceservice.business.abstracts;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.invoiceservice.entities.Invoice;



public interface InvoiceService {
	Invoice add(PaymentCreatedEvent paymentCreatedEvent);


}
