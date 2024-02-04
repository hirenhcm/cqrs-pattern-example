package com.hcm.mistry.solace.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.hcm.mistry.mysql.entity.IEmployee;

@Service
public class SolacePublisher {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public SolacePublisher(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
		//jmsTemplate.setMessageConverter(new EmployeeMessageConverter());
		jmsTemplate.setPubSubDomain(true);
		
	}
	
	public void sendMessage(String dest, IEmployee employee) {
		System.out.println("==========SENDING MESSAGE========== " + employee.toString() + " at:" + System.currentTimeMillis());
		jmsTemplate.convertAndSend(dest, employee);
	}

}
