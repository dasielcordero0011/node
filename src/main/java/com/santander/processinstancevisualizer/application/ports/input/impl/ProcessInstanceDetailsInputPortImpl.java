package com.santander.processinstancevisualizer.application.ports.input.impl;

import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceDetailsInputPort;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.application.usecases.GetInstanceDetailsUseCase;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ProcessInstanceDetailsInputPortImpl implements ProcessInstanceDetailsInputPort {

   private final GetInstanceDetailsUseCase getInstanceDetailsUseCase;

   @Override
   public ProcessInstance handle(InstanceDetailsInputModel inputModel) {
      return getInstanceDetailsUseCase.execute(inputModel);
   }
}
