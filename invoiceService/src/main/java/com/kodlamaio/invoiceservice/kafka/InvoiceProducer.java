package com.kodlamaio.invoiceservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.invoice.InvoiceCreatedEvent;

@Service
public class InvoiceProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceProducer.class);
    
    

    public InvoiceProducer(NewTopic topic, KafkaTemplate<String, InvoiceCreatedEvent> kafkaTemplateCreated) {
		super();
		this.topic = topic;
		this.kafkaTemplateCreated = kafkaTemplateCreated;
	}


	private NewTopic topic;

    private KafkaTemplate<String, InvoiceCreatedEvent> kafkaTemplateCreated;


    public void sendMessage(InvoiceCreatedEvent invoiceCreatedEvent) {
        LOGGER.info(String.format("Payment created event => %s", invoiceCreatedEvent.toString()));

        Message<InvoiceCreatedEvent> message = MessageBuilder
                .withPayload(invoiceCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        kafkaTemplateCreated.send(message);
    }
}
