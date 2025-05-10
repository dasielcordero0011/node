package com.santander.processinstancevisualizer.application.usecases.impl;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceDetailsOutputPort;
import com.santander.processinstancevisualizer.application.usecases.GetInstanceDetailsUseCase;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class GetProcessInstanceDetailsUseCaseImpl implements GetInstanceDetailsUseCase {

   private final ProcessInstanceDetailsOutputPort processInstanceDetailsOutputPort;

   @Override
   public ProcessInstance execute(InstanceDetailsInputModel input) {
      return processInstanceDetailsOutputPort.handle(input);
   }
}
