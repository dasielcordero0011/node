package com.santander.processinstancevisualizer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InstanceTerminationRequest {

   @NotNull
   @JsonProperty("process_definition_id")
   private String processDefinitionId;

   @NotEmpty
   @JsonProperty("process_instance_ids")
   private List<String> processInstanceIds;
}
