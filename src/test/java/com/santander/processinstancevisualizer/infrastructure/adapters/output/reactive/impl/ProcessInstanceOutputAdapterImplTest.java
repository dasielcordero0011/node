package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;
import com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.WebClientResponseDecorator;
import com.santander.processinstancevisualizer.infrastructure.config.ApiProperties;

@ExtendWith(MockitoExtension.class)
class ProcessInstanceOutputAdapterImplTest {

   @Mock
   private WebClient webClient;

   @Mock
   private ApiProperties apiProperties;

   @Mock
   private WebClientResponseDecorator responseDecorator;

   private ProcessInstanceOutputAdapterImpl adapter;

   @BeforeEach
   void setUp() {
      adapter = new ProcessInstanceOutputAdapterImpl(webClient, apiProperties, responseDecorator);
   }

   @Test
   void testHandle() {
      InstanceInputModel inputModel = new InstanceInputModel("id1", "pid1", "v1", "running", LocalDate.now(), LocalDate.now(), false);

      PaginatedListResponse expectedResponse = PaginatedListResponse.builder().totalItems(1).totalPages(1).currentPage(0).items(List.of()).build();

      WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
      WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
      WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

      lenient().when(webClient.get()).thenReturn(requestHeadersUriSpec);
      lenient().when(apiProperties.getProcessInstanceEndpoint()).thenReturn("/test-endpoint/{processId}");
      lenient().when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
      lenient().when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

      when(responseDecorator.execute(any(WebClient.ResponseSpec.class), eq(PaginatedListResponse.class))).thenReturn(expectedResponse);

      PaginatedListResponse result = adapter.handle(inputModel);

      assertNotNull(result);
      assertEquals(1, result.getTotalItems());
   }
}
