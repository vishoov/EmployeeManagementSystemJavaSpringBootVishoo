package com.example.vishoov.accessor;

import com.example.vishoov.accessor.dao.EmployeeDAO;
import com.example.vishoov.exceptions.EmployeeNotFoundException;
import com.example.vishoov.cache.CacheManager;
import com.example.vishoov.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeInformationCacheAccessor implements EmployeeInformationAccessor {

    private final EmployeeDAO employeeDAO;
    private final CacheManager cacheManager;

    @Override
    public Employee saveEmployee(Employee employee) {
        throw new IllegalCallerException("Cache does not Support Save Employee Function");
    }

    @Override
    public Employee getEmployee(int employeeId) {
        Employee employee = cacheManager.getEmployee(employeeId);
        // Fetching from DB
        if (Objects.isNull(employee)) {
            log.info("Employee not present in Cache... Fetching from DB");
            Optional<Employee> employeeOptional = employeeDAO.findById(employeeId);
            employeeOptional.ifPresent(cacheManager::setEmployee);
            employee = employeeOptional.orElse(null);
        }
        if (Objects.nonNull(employee))
            return employee;

        log.error("Employee not present in DB...");
        throw new EmployeeNotFoundException("Employee not present in DB...");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return cacheManager.getAllEmployees();
    }
}
