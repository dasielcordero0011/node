package com.santander.processinstancevisualizer.infrastructure.adapters.input.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceDetailsInputPort;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

@ExtendWith(MockitoExtension.class)
class ProcessInstanceDetailsControllerTest {

   @Mock
   private ProcessInstanceDetailsInputPort processInstanceDetailsInputPort;

   @InjectMocks
   private ProcessInstanceDetailsController controller;

   @Test
   void testGetProcessInstanceById() {
      String instanceId = "instance-123";

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

      when(processInstanceDetailsInputPort.handle(any(InstanceDetailsInputModel.class))).thenReturn(expectedInstance);

      ResponseEntity<ProcessInstance> response = controller.getProcessInstanceById(instanceId);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(expectedInstance, response.getBody());
      assertEquals(instanceId, response.getBody().getProcessInstanceId());
   }
}
