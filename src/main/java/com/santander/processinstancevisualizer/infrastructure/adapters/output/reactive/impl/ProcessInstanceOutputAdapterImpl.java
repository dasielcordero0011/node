package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceOutputPort;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;
import com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.WebClientResponseDecorator;
import com.santander.processinstancevisualizer.infrastructure.config.ApiProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcessInstanceOutputAdapterImpl implements ProcessInstanceOutputPort {

   private final WebClient webClient;

   private final ApiProperties apiProperties;

   private final WebClientResponseDecorator responseDecorator;

   @Override
   public PaginatedListResponse handle(InstanceInputModel inputModel) {
      WebClient.ResponseSpec responseSpec = webClient
            .get()
            .uri(uriBuilder -> uriBuilder.path(apiProperties.getProcessInstanceEndpoint()).build(inputModel.processId()))
            .retrieve();
      return responseDecorator.execute(responseSpec, PaginatedListResponse.class);
   }
}
