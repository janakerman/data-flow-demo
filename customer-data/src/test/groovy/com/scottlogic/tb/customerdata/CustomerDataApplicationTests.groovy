package com.scottlogic.tb.customerdata

import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.stream.messaging.Source
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.context.junit4.SpringRunner

import static org.hamcrest.core.IsEqual.equalTo
import static org.junit.Assert.assertThat
import static org.mockito.Mockito.when

@RunWith(SpringRunner)
@SpringBootTest
class CustomerDataApplicationTests {

	@Autowired
	private Source channels

	@Autowired
	private MessageCollector collector

	@MockBean
	private CustomerRepository someService

	@Test
	void testMessages() {
		// given
		when(someService.get(92)).thenReturn(['name': 'Mr. D. Flow'])

		// when
		this.channels.input().send(new GenericMessage<>('{"userId":92,"action":"click"}'))
		Message<Map> messages = collector.forChannel(channels.output()).poll() as Message<Map>

		// then
		final expected = [userId:92, action: "click", name: "Mr. D. Flow"]
		assertThat(messages.getPayload(), equalTo(expected))
	}

}
