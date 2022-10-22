package com.example.employeesystemapi.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.employeesystemapi.entity.EmployeeEntity;
import com.example.employeesystemapi.model.Employee;
import com.example.employeesystemapi.repository.EmployeeRepository;
import com.example.employeesystemapi.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee (Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepository.save(employeeEntity);

        return employee;
    }
}
