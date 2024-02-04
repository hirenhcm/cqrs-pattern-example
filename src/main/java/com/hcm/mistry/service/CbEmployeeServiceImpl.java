package com.hcm.mistry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.mistry.couchbase.entity.CbEmployee;
import com.hcm.mistry.repo.couchbase.CbEmployeeRepository;

@Service
public class CbEmployeeServiceImpl implements EmployeeService<CbEmployee> {

	@Autowired
	private CbEmployeeRepository cbEmployeeRepository;

	@Override
	public CbEmployee saveEmployee(CbEmployee employee) {
		employee.setId(Long.toString(employee.getEmployeeId()));
//		employee.setUpdated(DateTime.Builder.);
//		employee.setCreated(DateTime.now());
		return cbEmployeeRepository.save(employee);
	}

	@Override
	public List<CbEmployee> fetchAllEmployees() {
		List<CbEmployee> allEmployees = new ArrayList<>();
		cbEmployeeRepository.findAll().forEach(allEmployees::add);		
		return allEmployees;
	}

	@Override
	public CbEmployee getEmployeeById(Long id) {		
		Optional<CbEmployee> employee = cbEmployeeRepository.findById(Long.toString(id));
		if (employee.isPresent()) {
			return employee.get();
		}
		return null;
	}

	@Override
	public CbEmployee updateEmployeeById(Long id, CbEmployee employee) {
		Optional<CbEmployee> employee1 = cbEmployeeRepository.findById(Long.toString(id));

		if (employee1.isPresent()) {
			CbEmployee originalEmployee = employee1.get();

			if (Objects.nonNull(employee.getEmployeeName()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
				originalEmployee.setEmployeeName(employee.getEmployeeName());
			}
			if (Objects.nonNull(employee.getEmployeeSalary()) && employee.getEmployeeSalary() != 0) {
				originalEmployee.setEmployeeSalary(employee.getEmployeeSalary());
			}
			return cbEmployeeRepository.save(employee);
		}
		return null;
	}

	@Override
	public String deleteDepartmentById(Long id) {
		if (cbEmployeeRepository.findById(Long.toString(id)).isPresent()) {
			cbEmployeeRepository.deleteById(Long.toString(id));
			return "Employee deleted successfully";
		}
		return "No such employee in the database";
	}
}
