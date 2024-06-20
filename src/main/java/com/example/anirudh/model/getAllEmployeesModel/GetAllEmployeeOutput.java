package com.example.vishoov.model.getAllEmployeesModel;

import com.example.vishoov.model.Employee;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetAllEmployeeOutput {
    private List<Employee> employeeList;
}
