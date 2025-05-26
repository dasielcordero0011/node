package com.santander.processinstancevisualizer.application.usecases.impl;

import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceDetailsOutputPort;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceOutputPort;
import com.santander.processinstancevisualizer.application.usecases.GetInstanceDetailsUseCase;
import com.santander.processinstancevisualizer.application.usecases.GetProcessInstanceUseCase;
import com.santander.processinstancevisualizer.application.usecases.UseCaseFactory;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class UseCaseFactoryImpl implements UseCaseFactory {

   private final ProcessInstanceOutputPort processInstanceOutputPort;

   private final ProcessInstanceDetailsOutputPort processInstanceDetailsOutputPort;

   @Override
   public GetProcessInstanceUseCase getGetProcessInstanceUseCase() {
      return GetProcessInstanceUseCaseImpl.builder().processInstanceOutputPort(processInstanceOutputPort).build();
   }

   @Override
   public GetInstanceDetailsUseCase getGetInstanceDetailsUseCase() {
      return GetProcessInstanceDetailsUseCaseImpl.builder().processInstanceDetailsOutputPort(processInstanceDetailsOutputPort).build();
   }
}
