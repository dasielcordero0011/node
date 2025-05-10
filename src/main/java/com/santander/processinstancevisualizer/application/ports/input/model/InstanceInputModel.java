package com.santander.processinstancevisualizer.application.ports.input.model;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record InstanceInputModel(String processInstanceId, String processId, String processVersion, String status, LocalDate startDate,
                                 LocalDate end_date, Boolean isClosed) {

}
