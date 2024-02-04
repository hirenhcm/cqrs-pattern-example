package com.hcm.mistry.solace.sub;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.hcm.mistry.couchbase.entity.CbEmployee;
import com.hcm.mistry.mysql.entity.Employee;
import com.hcm.mistry.service.EmployeeService;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

@Service
public class SolaceSubscriber {

	@Autowired
	private EmployeeService<CbEmployee> employeeService;

	@JmsListener(destination = "test-queue")
	public void handle(Employee message) {

		Date receiveTime = new Date();

		if (message instanceof Employee) {
			Employee emp = (Employee) message;
			try {
				System.out.println(
						"Message Received at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(receiveTime));
				//TODO: Create a mapper to convert Employee to CbEmployee
				CbEmployee cbEmployee = new CbEmployee("Employee-" + emp.getEmployeeId(), emp.getEmployeeId(), emp.getEmployeeName(), emp.getEmployeeSalary());
				employeeService.saveEmployee(cbEmployee);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(message.toString());
		}
	}

}
