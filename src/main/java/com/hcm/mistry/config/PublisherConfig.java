package com.hcm.mistry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class PublisherConfig {
	
	@Autowired
	private JmsTemplate jmsTemplate;
}
