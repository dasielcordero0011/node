package com.santander.processinstancevisualizer.application.usecases.impl;

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
import com.santander.processinstancevisualizer.application.ports.output.ProcessInstanceOutputPort;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

@ExtendWith(MockitoExtension.class)
class GetProcessInstanceUseCaseImplTest {

   @Mock
   private ProcessInstanceOutputPort processInstanceOutputPort;

   @InjectMocks
   private GetProcessInstanceUseCaseImpl useCase;

   @Test
   void testExecute() {
      InstanceInputModel inputModel = InstanceInputModel
            .builder()
            .processId("process-123")
            .processVersion("1.0")
            .status("ACTIVE")
            .isClosed(false)
            .startDate(LocalDate.of(2023, 1, 1))
            .build();

      PaginatedListResponse expectedResponse = PaginatedListResponse.builder().totalItems(1).totalPages(1).currentPage(0).items(List.of()).build();

      when(processInstanceOutputPort.handle(inputModel)).thenReturn(expectedResponse);

      PaginatedListResponse result = useCase.execute(inputModel);

      assertEquals(expectedResponse, result);
      verify(processInstanceOutputPort).handle(inputModel);
   }
}
