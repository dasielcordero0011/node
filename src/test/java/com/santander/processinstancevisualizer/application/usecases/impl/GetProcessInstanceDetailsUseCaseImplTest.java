package com.santander.processinstancevisualizer.application.usecases.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceDetailsOutputPort;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

@ExtendWith(MockitoExtension.class)
class GetProcessInstanceDetailsUseCaseImplTest {

   @Mock
   private ProcessInstanceDetailsOutputPort processInstanceDetailsOutputPort;

   @InjectMocks
   private GetProcessInstanceDetailsUseCaseImpl useCase;

   @Test
   void testExecute() {
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

      when(processInstanceDetailsOutputPort.handle(inputModel)).thenReturn(expectedInstance);

      ProcessInstance result = useCase.execute(inputModel);

      assertEquals(expectedInstance, result);
      verify(processInstanceDetailsOutputPort).handle(inputModel);
   }
}
