package com.santander.processinstancevisualizer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.santander.processinstancevisualizer.application.ports.input.InputPortsFactory;
import com.santander.processinstancevisualizer.application.ports.input.InputPortsFactoryBuilder;
import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceDetailsInputPort;
import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceInputPort;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceDetailsOutputPort;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceOutputPort;
import com.santander.processinstancevisualizer.application.usecases.UseCaseFactoryBuilder;

@Configuration
public class ApplicationConfig {

   private final InputPortsFactory inputPortsFactory;

   public ApplicationConfig(ProcessInstanceOutputPort processInstanceOutputPort, ProcessInstanceDetailsOutputPort processInstanceDetailsOutputPort) {
      final var useCaseFactory = UseCaseFactoryBuilder.getInstance(processInstanceOutputPort, processInstanceDetailsOutputPort);
      this.inputPortsFactory = InputPortsFactoryBuilder.getInstance(useCaseFactory);
   }

   @Bean
   public ProcessInstanceInputPort getProcessDefinitionInputPortInstance() {
      return inputPortsFactory.getProcessInstanceInputPort();
   }

   @Bean
   public ProcessInstanceDetailsInputPort getProcessDefinitionOutputPortInstance() {
      return inputPortsFactory.getProcessInstanceDetailsInputPort();
   }

}

