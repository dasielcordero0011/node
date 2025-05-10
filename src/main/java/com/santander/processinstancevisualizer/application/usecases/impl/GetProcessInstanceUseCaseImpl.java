package com.santander.processinstancevisualizer.application.usecases.impl;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceOutputPort;
import com.santander.processinstancevisualizer.application.usecases.GetProcessInstanceUseCase;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class GetProcessInstanceUseCaseImpl implements GetProcessInstanceUseCase {

   private final ProcessInstanceOutputPort processInstanceOutputPort;

   @Override
   public PaginatedListResponse execute(InstanceInputModel input) {
      return processInstanceOutputPort.handle(input);
   }
}
