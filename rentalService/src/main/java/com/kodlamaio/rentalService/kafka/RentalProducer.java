package com.kodlamaio.rentalService.kafka;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedEvent;

import lombok.AllArgsConstructor;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

	//kafka dediğimiz ikili mesaj
@Service
@AllArgsConstructor
public class RentalProducer { //producer inventroy nin consumera gönderiyor ,consumer alıyor bu araç kiralanmış bunun statetini
							 //güncellemem lazım diyor
	
							 //ve son olarak inventorynin managerına gönderiyor.  produce(gönderici) consumer(alıcı)
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class); //loglama burada console a bilgi basmak için

	private NewTopic topic; //topic oluşturduk

	private KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate1;
	private KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate2;
	public void sendMessage(RentalCreatedEvent rentalCreatedEvent) { 
		LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));

		Message<RentalCreatedEvent> message = MessageBuilder.withPayload(rentalCreatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();

		kafkaTemplate1.send(message);
	}
	public void sendMessage(RentalUpdatedEvent rentalUpdatedEvent) {
		LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));
		
		Message<RentalUpdatedEvent> message = MessageBuilder
				.withPayload(rentalUpdatedEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate2.send(message);
	}
	
	
	

}
