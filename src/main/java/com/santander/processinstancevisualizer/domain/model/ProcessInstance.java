package com.santander.processinstancevisualizer.domain.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessInstance {

   @JsonProperty("process_definition_id")
   private String processDefinitionId;

   @JsonProperty("process_version")
   private String processVersion;

   @JsonProperty("process_instance_id")
   private String processInstanceId;

   @JsonProperty("parent_process_instance_id")
   private String parentProcessInstanceId;

   @JsonProperty("root_process_instance_id")
   private String rootProcessInstanceId;

   @JsonProperty("process_instance_business_key")
   private String processInstanceBusinessKey;

   @JsonProperty("process_instance_status")
   private String processInstanceStatus;

   @JsonProperty("process_instance_start_date")
   private ZonedDateTime processInstanceStartDate;

   @JsonProperty("process_instance_end_date")
   private ZonedDateTime processInstanceEndDate;

   @JsonProperty("process_instance_last_updated")
   private ZonedDateTime processInstanceLastUpdated;

   @JsonProperty("process_instance_sla_due_date")
   private ZonedDateTime processInstanceSlaDueDate;

   @JsonProperty("process_instance_created_by")
   private String processInstanceCreatedBy;

   @JsonProperty("nodes")
   private List<NodeInstance> nodes;
}
