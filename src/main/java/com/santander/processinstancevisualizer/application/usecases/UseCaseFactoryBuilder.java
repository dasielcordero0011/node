package com.santander.processinstancevisualizer.application.usecases;

import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceDetailsOutputPort;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceOutputPort;
import com.santander.processinstancevisualizer.application.usecases.impl.UseCaseFactoryImpl;

public class UseCaseFactoryBuilder {

   public static UseCaseFactory getInstance(ProcessInstanceOutputPort processInstanceOutputPort,
         ProcessInstanceDetailsOutputPort processInstanceDetailsOutputPort) {
      return UseCaseFactoryImpl
            .builder()
            .processInstanceOutputPort(processInstanceOutputPort)
            .processInstanceDetailsOutputPort(processInstanceDetailsOutputPort)
            .build();
   }
}
