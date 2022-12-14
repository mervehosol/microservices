package com.kodlamaio.rentalService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.rentalService.business.abstracts.RentalService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentConsumer {
	private RentalService rentalService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "Created")
	public void consume(PaymentCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

		//rentalService.updateRentalState(event.getCarId(),3);

		// save the order event into the database
	}
}
