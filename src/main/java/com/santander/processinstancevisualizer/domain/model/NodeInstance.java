package com.santander.processinstancevisualizer.domain.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NodeInstance {

   @JsonProperty("node_instance_id")
   private String nodeInstanceId;

   @JsonProperty("node_definition_id")
   private String nodeDefinitionId;

   @JsonProperty("node_definition_name")
   private String nodeDefinitionName;

   @JsonProperty("node_instance_status")
   private String nodeInstanceStatus;

   @JsonProperty("node_instance_start_date")
   private ZonedDateTime nodeInstanceStartDate;

   @JsonProperty("node_instance_end_date")
   private ZonedDateTime nodeInstanceEndDate;

   @JsonProperty("node_instance_execution_owner")
   private String nodeInstanceExecutionOwner;

   @JsonProperty("node_instance_error_details")
   private List<String> nodeInstanceErrorDetails;

   @JsonProperty("node_instance_last_updated")
   private ZonedDateTime nodeInstanceLastUpdated;

   @JsonProperty("node_instance_asignees")
   private List<String> nodeInstanceAsignees;

   @JsonProperty("node_instance_inputs")
   private Map<String, Object> nodeInstanceInputs;

   @JsonProperty("node_instance_outputs")
   private Map<String, Object> nodeInstanceOutputs;

}
