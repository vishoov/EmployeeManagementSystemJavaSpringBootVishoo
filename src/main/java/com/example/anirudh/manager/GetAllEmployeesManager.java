package com.example.vishoov.manager;

import com.example.vishoov.accessor.EmployeeInformationAccessor;
import com.example.vishoov.model.Employee;
import com.example.vishoov.model.getAllEmployeesModel.GetAllEmployeeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class GetAllEmployeesManager {
    private final EmployeeInformationAccessor employeeInformationCacheAccessor;

    private final EmployeeInformationAccessor employeeInformationDBAccessor;

    @Autowired
    public GetAllEmployeesManager(@Qualifier("EmployeeInformationCacheAccessor") EmployeeInformationAccessor employeeInformationCacheAccessor, @Qualifier("EmployeeInformationDBAccessor") EmployeeInformationAccessor employeeInformationDBAccessor) {
        this.employeeInformationCacheAccessor = employeeInformationCacheAccessor;
        this.employeeInformationDBAccessor = employeeInformationDBAccessor;
    }

    public List<Employee> getAllEmployeesManager(GetAllEmployeeInput getAllEmployeeInput) {
        boolean realtimeDataRequired = Objects.nonNull(getAllEmployeeInput) && getAllEmployeeInput.isRealTimeDataRequired();
        return realtimeDataRequired ? employeeInformationDBAccessor.getAllEmployees() : employeeInformationCacheAccessor.getAllEmployees();
    }
}
