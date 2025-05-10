package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;
import com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.WebClientResponseDecorator;
import com.santander.processinstancevisualizer.infrastructure.config.ApiProperties;

@ExtendWith(MockitoExtension.class)
class ProcessInstanceDetailsOutputAdapterImplTest {

   @Mock
   private WebClient webClient;

   @Mock
   private ApiProperties apiProperties;

   @Mock
   private WebClientResponseDecorator responseDecorator;

   private ProcessInstanceDetailsOutputAdapterImpl adapter;

   @BeforeEach
   void setUp() {
      adapter = new ProcessInstanceDetailsOutputAdapterImpl(webClient, apiProperties, responseDecorator);
   }

   @Test
   void testHandle() {
      String instanceId = "instance-123";
      InstanceDetailsInputModel inputModel = InstanceDetailsInputModel.builder().instanceId(instanceId).build();

      ProcessInstance expectedInstance = ProcessInstance
            .builder()
            .processInstanceId(instanceId)
            .processDefinitionId("process-def-123")
            .processVersion("1.0")
            .processInstanceStatus("ACTIVE")
            .processInstanceStartDate(ZonedDateTime.now())
            .processInstanceLastUpdated(ZonedDateTime.now())
            .nodes(Collections.emptyList())
            .build();

      WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
      WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
      WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

      lenient().when(webClient.get()).thenReturn(requestHeadersUriSpec);
      lenient().when(apiProperties.getProcessInstanceDetailsEndpoint()).thenReturn("/test-endpoint/{instanceId}");
      lenient().when(requestHeadersUriSpec.uri(any(java.util.function.Function.class))).thenReturn(requestHeadersSpec);
      lenient().when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

      when(responseDecorator.execute(any(WebClient.ResponseSpec.class), eq(ProcessInstance.class))).thenReturn(expectedInstance);

      ProcessInstance result = adapter.handle(inputModel);

      assertNotNull(result);
      assertEquals(expectedInstance, result);
      assertEquals(instanceId, result.getProcessInstanceId());
   }
}
