package com.example.employeesystemapi.service;

import com.example.employeesystemapi.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee (Employee employee);

    List<Employee> getALlEmployees ();

    boolean deleteEmployee (Long id);

    Employee getEmployeeById (Long id);

    Employee updateEmployee (Long id, Employee employee);
}
