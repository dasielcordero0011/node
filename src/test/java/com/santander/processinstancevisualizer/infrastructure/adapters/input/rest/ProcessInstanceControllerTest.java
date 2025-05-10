package com.santander.processinstancevisualizer.infrastructure.adapters.input.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceInputPort;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

@ExtendWith(MockitoExtension.class)
class ProcessInstanceControllerTest {

   @Mock
   private ProcessInstanceInputPort processInstanceInputPort;

   @InjectMocks
   private ProcessInstanceController controller;

   @Test
   void testGetInstances() {
      String processDefinitionId = "process-123";
      String processVersion = "1.0";
      String status = "ACTIVE";
      Boolean isClosed = false;
      LocalDate startDate = LocalDate.of(2023, 1, 1);

      PaginatedListResponse expectedResponse = PaginatedListResponse.builder().totalItems(1).totalPages(1).currentPage(0).items(List.of()).build();

      when(processInstanceInputPort.handle(any(InstanceInputModel.class))).thenReturn(expectedResponse);

      ResponseEntity<PaginatedListResponse> response = controller.getInstances(processDefinitionId, processVersion, status, isClosed, startDate);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(expectedResponse, response.getBody());
   }

   @Test
   void testGetInstancesWithMinimalParameters() {
      String processDefinitionId = "process-123";

      PaginatedListResponse expectedResponse = PaginatedListResponse.builder().totalItems(1).totalPages(1).currentPage(0).items(List.of()).build();

      when(processInstanceInputPort.handle(any(InstanceInputModel.class))).thenReturn(expectedResponse);

      ResponseEntity<PaginatedListResponse> response = controller.getInstances(processDefinitionId, null, null, null, null);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(expectedResponse, response.getBody());
   }
}
