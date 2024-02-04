package com.hcm.mistry.jms.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcm.mistry.mysql.entity.Employee;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

@Component
public class EmployeeMessageConverter implements MessageConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMessageConverter.class);

	ObjectMapper mapper;

	public EmployeeMessageConverter() {
		mapper = new ObjectMapper();
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException {
		Employee person = (Employee) object;
		String payload = null;
		try {
			payload = mapper.writeValueAsString(person);
			LOGGER.info("outbound json='{}'", payload);
		} catch (JsonProcessingException e) {
			LOGGER.error("error converting form person", e);
		}

		TextMessage message = session.createTextMessage();
		message.setText(payload);

		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException {
		TextMessage textMessage = (TextMessage) message;
		String payload = textMessage.getText();
		LOGGER.info("inbound json='{}'", payload);

		Employee person = null;
		try {
			person = mapper.readValue(payload, Employee.class);
		} catch (Exception e) {
			LOGGER.error("error converting to person", e);
		}

		return person;
	}
}