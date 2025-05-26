package com.santander.processinstancevisualizer.application.ports.input.impl;

import com.santander.processinstancevisualizer.application.ports.input.InputPortsFactory;
import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceDetailsInputPort;
import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceInputPort;
import com.santander.processinstancevisualizer.application.usecases.UseCaseFactory;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class InputPortsFactoryImpl implements InputPortsFactory {

   private final UseCaseFactory useCaseFactory;

   @Override
   public ProcessInstanceInputPort getProcessInstanceInputPort() {
      return ProcessInstanceInputPortImpl.builder().processInstanceUseCase(useCaseFactory.getGetProcessInstanceUseCase()).build();
   }

   @Override
   public ProcessInstanceDetailsInputPort getProcessInstanceDetailsInputPort() {
      return ProcessInstanceDetailsInputPortImpl.builder().getInstanceDetailsUseCase(useCaseFactory.getGetInstanceDetailsUseCase()).build();
   }

}
