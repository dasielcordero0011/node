package com.santander.processinstancevisualizer.application.usecases;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

@FunctionalInterface
public interface GetProcessInstanceUseCase extends UseCase<InstanceInputModel, PaginatedListResponse> {

}
