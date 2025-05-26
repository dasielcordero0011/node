package com.santander.processinstancevisualizer.infrastructure.adapters.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.processinstancevisualizer.application.ports.input.ProcessInstanceDetailsInputPort;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/instances")
@Tag(name = "Instances", description = "Operations related to process instances")
public class ProcessInstanceDetailsController {

   private final ProcessInstanceDetailsInputPort processInstanceDetailsInputPort;

   @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   @Operation(summary = "Get details of a specific process instance", description = "Returns all available metadata for a specific process "
         + "instance, including status, timestamps, ownership, and SLA indicators.", responses = {
         @ApiResponse(responseCode = "200", description = "Process instance details", content = @Content(mediaType = "application/json", schema =
         @Schema(implementation = ProcessInstance.class))),
         @ApiResponse(responseCode = "500", description = "Internal error while retrieving instance", content = @Content(mediaType = "application"
               + "/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = """
                   {
                     "error": {
                       "code": 500,
                       "message": "Unexpected error while retrieving process instance"
                     }
                   }
               """))) })
   public ResponseEntity<ProcessInstance> getProcessInstanceById(@PathVariable String id) {
      InstanceDetailsInputModel input = InstanceDetailsInputModel.builder().instanceId(id).build();
      return ResponseEntity.ok(processInstanceDetailsInputPort.handle(input));
   }
}
