package com.example.vishoov.cache.caches.impl;

import com.example.vishoov.accessor.dao.EmployeeDAO;
import com.example.vishoov.cache.caches.InMemoryCache;
import com.example.vishoov.model.Employee;
import com.google.common.cache.LoadingCache;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeCache implements InMemoryCache {

    @NonNull
    private final LoadingCache<Integer, Employee> employeeCache;

    @NonNull
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeCache(@NonNull EmployeeDAO employeeDAO, @NonNull LoadingCache<Integer, Employee> employeeCache) {
        this.employeeCache = employeeCache;
        this.employeeDAO = employeeDAO;

    }

    @Override
    public void setAll() {
        List<Employee> employeeList = employeeDAO.findAll();
        employeeCache.putAll(employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity())));
    }

    @Override
    public void set(Employee employee) {
        employeeCache.put(employee.getId(), employee);
    }

    @Override
    public List<Employee> getAll() {
        HashMap employeeMap = new HashMap(employeeCache.asMap());
        return employeeMap.values().stream().toList();
    }

    @Override
    public Employee get(int employeeId) {
        return employeeCache.getIfPresent(employeeId);
    }

    @Override
    public void update() {
        setAll();
    }
}
