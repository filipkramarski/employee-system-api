package com.example.employeesystemapi.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.employeesystemapi.entity.EmployeeEntity;
import com.example.employeesystemapi.model.Employee;
import com.example.employeesystemapi.repository.EmployeeRepository;
import com.example.employeesystemapi.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Employee> getALlEmployees () {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail()))
                .collect(Collectors.toList());

        return employees;
    }

    @Override
    public boolean deleteEmployee (Long id) {

        EmployeeEntity employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);

        return true;
    }

    @Override
    public Employee getEmployeeById (Long id) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);

        return employee;
    }

    @Override
    public Employee updateEmployee (Long id, Employee employee) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());
        employeeRepository.save(employeeEntity);

        return employee;
    }
}
