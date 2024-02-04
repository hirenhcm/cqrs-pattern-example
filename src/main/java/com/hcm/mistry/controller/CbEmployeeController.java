package com.hcm.mistry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.mistry.couchbase.entity.CbEmployee;
import com.hcm.mistry.service.EmployeeService;

@RestController()
@RequestMapping("couchbase")
public class CbEmployeeController {

    @Autowired
    private EmployeeService<CbEmployee> employeeService;

    @PostMapping("/employee")
    public CbEmployee saveEmployee(@RequestBody CbEmployee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee")
    public List<CbEmployee> getAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public CbEmployee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    public CbEmployee updateEmployee(@PathVariable("id") Long id, @RequestBody CbEmployee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        return employeeService.deleteDepartmentById(id);
    }
}