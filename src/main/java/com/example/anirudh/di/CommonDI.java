package com.example.vishoov.di;

import com.example.vishoov.accessor.EmployeeInformationAccessor;
import com.example.vishoov.accessor.EmployeeInformationCacheAccessor;
import com.example.vishoov.accessor.EmployeeInformationDBAccessor;
import com.example.vishoov.accessor.dao.EmployeeDAO;
import com.example.vishoov.cache.CacheManager;
import com.example.vishoov.cache.cacheLoader.EmployeeCacheLoader;
import com.example.vishoov.cache.cacheUpdate.CacheUpdateRunnable;
import com.example.vishoov.cache.cacheUpdate.CacheUpdateTask;
import com.example.vishoov.cache.caches.InMemoryCache;
import com.example.vishoov.cache.caches.impl.EmployeeCache;
import com.example.vishoov.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CommonDI {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public LoadingCache<Integer, Employee> EmployeeCache(EmployeeDAO employeeDAO) {
        return CacheBuilder.newBuilder().refreshAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(1500).build(new EmployeeCacheLoader(employeeDAO));
    }

    @Bean
    public InMemoryCache employeeCache(EmployeeDAO employeeDAO, LoadingCache<Integer, Employee> employeeCache) {
        return new EmployeeCache(employeeDAO, employeeCache);
    }

    @Bean
    public CacheUpdateTask cacheUpdateTask(CacheUpdateRunnable cacheUpdateRunnable) {
        return new CacheUpdateTask(cacheUpdateRunnable);
    }

    @Bean
    public CacheManager cacheManager(InMemoryCache employeeCache, CacheUpdateTask cacheUpdateTask) {
        return new CacheManager(employeeCache, cacheUpdateTask);
    }

    @Bean
    @Qualifier("EmployeeInformationCacheAccessor")
    public EmployeeInformationAccessor getEmployeeInformationCacheAccessor(EmployeeDAO employeeDAO, CacheManager cacheManager) {
        return new EmployeeInformationCacheAccessor(employeeDAO, cacheManager);
    }

    @Bean
    @Qualifier("EmployeeInformationDBAccessor")
    public EmployeeInformationAccessor getEmployeeInformationDBAccessor(EmployeeDAO employeeDAO) {
        return new EmployeeInformationDBAccessor(employeeDAO);
    }

    @Bean
    public CacheUpdateRunnable getCacheUpdateRunnable(InMemoryCache inMemoryCache) {
        return new CacheUpdateRunnable(inMemoryCache);
    }
}
