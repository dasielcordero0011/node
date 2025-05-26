package com.santander.processinstancevisualizer.application.ports.input.impl;

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
import com.santander.processinstancevisualizer.application.usecases.GetInstanceDetailsUseCase;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

@ExtendWith(MockitoExtension.class)
class ProcessInstanceDetailsInputPortImplTest {

   @Mock
   private GetInstanceDetailsUseCase getInstanceDetailsUseCase;

   @InjectMocks
   private ProcessInstanceDetailsInputPortImpl inputPort;

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

      when(getInstanceDetailsUseCase.execute(inputModel)).thenReturn(expectedInstance);

      ProcessInstance result = inputPort.handle(inputModel);

      assertEquals(expectedInstance, result);
      verify(getInstanceDetailsUseCase).execute(inputModel);
   }
}
