package com.kodlamaio.invoiceservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedEvent;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;

@Service
public class RentalConsumer {
	private InvoiceService invoiceService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "Created")
	public void consume(RentalCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
 

		// save the order event into the database
	}

	public void consume(RentalUpdatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		 
		// save the order event into the database
	}

}
