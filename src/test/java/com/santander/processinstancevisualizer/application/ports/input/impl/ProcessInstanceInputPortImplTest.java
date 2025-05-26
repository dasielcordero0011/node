package com.santander.processinstancevisualizer.application.ports.input.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.application.usecases.GetProcessInstanceUseCase;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

@ExtendWith(MockitoExtension.class)
class ProcessInstanceInputPortImplTest {

   @Mock
   private GetProcessInstanceUseCase getProcessInstanceUseCase;

   @InjectMocks
   private ProcessInstanceInputPortImpl inputPort;

   @Test
   void testHandle() {
      InstanceInputModel inputModel = InstanceInputModel
            .builder()
            .processId("process-123")
            .processVersion("1.0")
            .status("ACTIVE")
            .isClosed(false)
            .startDate(LocalDate.of(2023, 1, 1))
            .build();

      PaginatedListResponse expectedResponse = PaginatedListResponse.builder().totalItems(1).totalPages(1).currentPage(0).items(List.of()).build();

      when(getProcessInstanceUseCase.execute(inputModel)).thenReturn(expectedResponse);

      PaginatedListResponse result = inputPort.handle(inputModel);

      assertEquals(expectedResponse, result);
      verify(getProcessInstanceUseCase).execute(inputModel);
   }
}
