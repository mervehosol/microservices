package com.kodlamaio.inventoryService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedEvent;
import com.kodlamaio.inventoryService.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer { 
	private CarService carService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(RentalCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

		carService.updateCarState(event.getCarId(),3); // state değişimi

		// save the order event into the database
	}

	public void consume(RentalUpdatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		carService.updateCarState(event.getCarId(), 3); //eski araba yeni araba olacak eski müsait durumna gelir.
		// save the order event into the database
	}
}
