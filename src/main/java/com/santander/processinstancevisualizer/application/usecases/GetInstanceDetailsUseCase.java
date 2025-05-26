package com.santander.processinstancevisualizer.application.usecases;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

public interface GetInstanceDetailsUseCase extends UseCase<InstanceDetailsInputModel, ProcessInstance>{

}
