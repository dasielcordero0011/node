package com.santander.processinstancevisualizer.application.ports.input.impl;

import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceInputPort;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.application.usecases.GetProcessInstanceUseCase;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ProcessInstanceInputPortImpl implements ProcessInstanceInputPort {

   private final GetProcessInstanceUseCase processInstanceUseCase;

   @Override
   public PaginatedListResponse handle(InstanceInputModel inputModel) {
      return processInstanceUseCase.execute(inputModel);
   }
}
