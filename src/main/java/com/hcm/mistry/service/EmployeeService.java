package com.hcm.mistry.service;

import java.util.List;

import com.hcm.mistry.mysql.entity.Employee;
import com.hcm.mistry.mysql.entity.IEmployee;

public interface EmployeeService<T extends IEmployee> {

	T saveEmployee(T employee);

    List<T> fetchAllEmployees();

    T getEmployeeById(Long id);

    T updateEmployeeById(Long id, T employee);

    String deleteDepartmentById(Long id);

}
