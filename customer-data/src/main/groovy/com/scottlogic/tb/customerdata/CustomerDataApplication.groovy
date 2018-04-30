package com.scottlogic.tb.customerdata

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.handler.annotation.SendTo

@SpringBootApplication
@EnableBinding(Processor)
@Slf4j
class CustomerDataApplication {

	static void main(String[] args) {
		SpringApplication.run CustomerDataApplication, args
	}

	@Autowired
	CustomerRepository repository

	@StreamListener(target = Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	Map decorate(Map event) {
        log.info("Received message: $event")
		final customerData = repository.get(event['userId'] as Integer)
		event << customerData
        log.info("Posting message: $event")
		event
	}

}
