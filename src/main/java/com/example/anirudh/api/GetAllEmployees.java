package com.example.vishoov.api;

import com.example.vishoov.exceptions.RequestFailureException;
import com.example.vishoov.manager.GetAllEmployeesManager;
import com.example.vishoov.model.Employee;
import com.example.vishoov.model.getAllEmployeesModel.GetAllEmployeeInput;
import com.example.vishoov.model.getAllEmployeesModel.GetAllEmployeeOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class GetAllEmployees {
    private final GetAllEmployeesManager employeeManager;

    public GetAllEmployeeOutput getAllEmployeesAPI(GetAllEmployeeInput getAllEmployeeInput) {
        try {
            long startTime = System.currentTimeMillis();
            log.info("Starting getAllEmployees");
            List<Employee> employees = employeeManager.getAllEmployeesManager(getAllEmployeeInput);
            log.info("getAllEmployees finished the request in {} ms", System.currentTimeMillis() - startTime);
            return GetAllEmployeeOutput.builder().employeeList(employees).build();
        } catch (Exception ex) {
            log.error("{} exception caught during execution - {}"
                    , ex.getClass().getSimpleName(), ex.getMessage());
            throw new RequestFailureException(String.format("%s exception caught during execution - %s"
                    , ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }
}
