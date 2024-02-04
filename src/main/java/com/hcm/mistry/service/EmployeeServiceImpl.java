package com.hcm.mistry.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.mistry.mysql.entity.Employee;
import com.hcm.mistry.repo.mysql.EmployeeRepository;
import com.hcm.mistry.solace.pub.SolacePublisher;

@Service
public class EmployeeServiceImpl implements EmployeeService<Employee>{

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private SolacePublisher solacePublisher;

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee e = employeeRepository.save(employee);
        solacePublisher.sendMessage("test/employee/create",e);
        //solacePublisher.sendMessage("test/employee/create");
        return e;
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> employee1 = employeeRepository.findById(id);

        if (employee1.isPresent()) {
            Employee originalEmployee = employee1.get();

            if (Objects.nonNull(employee.getEmployeeName()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
                originalEmployee.setEmployeeName(employee.getEmployeeName());
            }
            if (Objects.nonNull(employee.getEmployeeSalary()) && employee.getEmployeeSalary() != 0) {
                originalEmployee.setEmployeeSalary(employee.getEmployeeSalary());
            }
            solacePublisher.sendMessage("test/employee/update", originalEmployee);
            return employeeRepository.save(originalEmployee);
        }
        return null;
    }

    @Override
    public String deleteDepartmentById(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
        return "No such employee in the database";
    }
}
