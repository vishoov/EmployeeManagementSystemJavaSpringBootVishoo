package com.example.vishoov.cache;

import com.example.vishoov.cache.cacheUpdate.CacheUpdateTask;
import com.example.vishoov.cache.caches.InMemoryCache;
import com.example.vishoov.cache.caches.impl.EmployeeCache;
import com.example.vishoov.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CacheManager {

    private final InMemoryCache employeeCache;

    private final CacheUpdateTask cacheUpdateTask;

    @Autowired
    public CacheManager(InMemoryCache employeeCache, CacheUpdateTask cacheUpdateTask) {
        this.employeeCache = employeeCache;
        this.cacheUpdateTask = cacheUpdateTask;
        buildCache();
        cacheUpdateTask.setupCacheUpdate();
    }

    private void buildCache() {
        log.info("building cache");
        initCache();
        log.info("cache building completed");
    }

    private void initCache() {
        log.info("building employee Cache");
        final long startTime = System.currentTimeMillis();
        employeeCache.setAll();
        log.info("employee cache building completed in {} ms", System.currentTimeMillis() - startTime);
    }

    public void setEmployee(Employee employee) {
        employeeCache.set(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeCache.getAll();
    }

    public Employee getEmployee(int employeeId) {
        return employeeCache.get(employeeId);
    }
}
