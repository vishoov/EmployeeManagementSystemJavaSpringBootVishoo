package com.example.vishoov.model.getAllEmployeesModel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllEmployeeInput {
    private boolean realTimeDataRequired;
}
