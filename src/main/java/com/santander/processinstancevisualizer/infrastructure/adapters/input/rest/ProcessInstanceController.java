package com.santander.processinstancevisualizer.infrastructure.adapters.input.rest;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceInputPort;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/instances")
@RequiredArgsConstructor
@Tag(name = "Instances", description = "Endpoints for querying process instances")
public class ProcessInstanceController {

   private final ProcessInstanceInputPort processInstanceInputPort;

   @Operation(summary = "Get list of process instances by definition", description = "Returns a list of process instances (active, completed, or "
         + "errored) for a given process definition.", responses = {
         @ApiResponse(responseCode = "200", description = "List of process instances", content = @Content(mediaType = "application/json", schema =
         @Schema(implementation = ProcessInstance.class))),
         @ApiResponse(responseCode = "400", description = "Missing or invalid query parameters"),
         @ApiResponse(responseCode = "500", description = "Unexpected internal error") })
   @GetMapping
   public ResponseEntity<PaginatedListResponse> getInstances(
         @Parameter(description = "Unique ID of the process definition", required = true) @RequestParam String process_definition_id,
         @Parameter(description = "Optional filter by version") @RequestParam(required = false) String process_version,
         @Parameter(description = "Optional filter by status (e.g., active, completed, error)") @RequestParam(required = false) String status,
         @Parameter(description = "Optional filter by closure status") @RequestParam(required = false) Boolean is_closed,
         @Parameter(description = "Optional filter by start date") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date) {
      InstanceInputModel input = InstanceInputModel
            .builder()
            .processVersion(process_version)
            .status(status)
            .isClosed(is_closed)
            .processId(process_definition_id)
            .startDate(start_date)
            .build();

      return ResponseEntity.ok(processInstanceInputPort.handle(input));
   }
}
