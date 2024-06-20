package com.example.vishoov.di;

import com.example.vishoov.validator.EmployeeServiceValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Validator {

    @Bean
    public EmployeeServiceValidator getEmployeesValidate(){
        return new EmployeeServiceValidator();
    }
}
