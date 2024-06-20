package com.example.vishoo.accessor;

import com.example.vishoo.model.Employee;

import java.util.List;

public interface EmployeeInformationAccessor {

    Employee saveEmployee(Employee employee);

    Employee getEmployee(int employeeId);

    List<Employee> getAllEmployees();
}
