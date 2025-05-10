package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceDetailsOutputPort;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;
import com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.WebClientResponseDecorator;
import com.santander.processinstancevisualizer.infrastructure.config.ApiProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcessInstanceDetailsOutputAdapterImpl implements ProcessInstanceDetailsOutputPort {

   private final WebClient webClient;

   private final ApiProperties apiProperties;

   private final WebClientResponseDecorator responseDecorator;

   @Override
   public ProcessInstance handle(InstanceDetailsInputModel inputModel) {
      WebClient.ResponseSpec responseSpec = webClient
            .get()
            .uri(uriBuilder -> uriBuilder.path(apiProperties.getProcessInstanceDetailsEndpoint()).build(inputModel.instanceId()))
            .retrieve();
      return responseDecorator.execute(responseSpec, ProcessInstance.class);
   }
}
