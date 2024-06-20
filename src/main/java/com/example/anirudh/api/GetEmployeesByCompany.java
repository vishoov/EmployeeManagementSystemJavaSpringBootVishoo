package com.example.vishoov.api;

import com.example.vishoov.accessor.dao.EmployeeDAO;
import com.example.vishoov.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class GetEmployeesByCompany {

    private final EmployeeDAO employeeDAO;

    public List<Employee> getEmployeesByCompanyAPI(String companyName) {
        long startTime = System.currentTimeMillis();
        log.info("Starting getEmployeesByCompanyName");
        List<Employee> employeesByCompanyName = employeeDAO.getEmployeesByCompanyName(companyName);
        log.info("getEmployeesByCompanyName finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employeesByCompanyName;
    }
}
