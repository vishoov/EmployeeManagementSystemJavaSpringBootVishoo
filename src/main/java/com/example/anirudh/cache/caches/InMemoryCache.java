package com.example.vishoov.cache.caches;

import com.example.vishoov.model.Employee;

import java.util.List;

public interface InMemoryCache {

    public void setAll();
    public void set(Employee employee);
    public List<Employee> getAll();
    public Employee get(int employeeId);
    public void update();

}
