package com.santander.processinstancevisualizer.infrastructure.adapters.input.rest;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santander.processinstancevisualizer.domain.model.NodeInstance;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

@RestController
@RequestMapping("/mock/v1/instances")
public class MockInstanceController {

   @GetMapping
   public ResponseEntity<PaginatedListResponse> getProcessInstances(@RequestParam String process_definition_id,
         @RequestParam(required = false) String process_version, @RequestParam(required = false) String status,
         @RequestParam(required = false) Boolean is_closed, @RequestParam(required = false) String start_date) {

      return ResponseEntity.ok(PaginatedListResponse.builder().currentPage(0).totalItems(2).totalPages(1).items(getMockedProcessInstances()).build());
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProcessInstance> getProcessInstanceById(@PathVariable String id) {
      List<ProcessInstance> processInstances = getMockedProcessInstances();
      return ResponseEntity.ok(processInstances.get(0));
   }

   public static List<ProcessInstance> getMockedProcessInstances() {
      NodeInstance node = NodeInstance
            .builder()
            .nodeInstanceId("node-inst-789")
            .nodeDefinitionId("node-id12")
            .nodeDefinitionName("Approval Task")
            .nodeInstanceStatus("COMPLETED")
            .nodeInstanceStartDate(ZonedDateTime.parse("2024-01-05T10:30:00Z"))
            .nodeInstanceEndDate(ZonedDateTime.parse("2024-01-05T11:00:00Z"))
            .nodeInstanceExecutionOwner("user2")
            .nodeInstanceErrorDetails(Collections.emptyList())
            .nodeInstanceLastUpdated(ZonedDateTime.parse("2024-01-05T11:00:00Z"))
            .nodeInstanceAsignees(Collections.emptyList())
            .build();

      ProcessInstance instance = ProcessInstance
            .builder()
            .processDefinitionId("proc-123")
            .processVersion("1.0")
            .processInstanceId("inst-001")
            .parentProcessInstanceId("parent-001")
            .rootProcessInstanceId("root-001")
            .processInstanceBusinessKey("business-key-001")
            .processInstanceStatus("ACTIVE")
            .processInstanceStartDate(ZonedDateTime.parse("2024-04-01T09:00:00Z"))
            .processInstanceEndDate(null)
            .processInstanceLastUpdated(ZonedDateTime.parse("2024-04-01T10:00:00Z"))
            .processInstanceSlaDueDate(ZonedDateTime.parse("2024-04-05T09:00:00Z"))
            .processInstanceCreatedBy("user1")
            .nodes(List.of(node))
            .build();

      return List.of(instance);
   }
}

