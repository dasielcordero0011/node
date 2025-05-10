package com.santander.processinstancevisualizer.application.ports.output;

import com.santander.processinstancevisualizer.application.ports.Port;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceInputModel;
import com.santander.processinstancevisualizer.domain.model.PaginatedListResponse;

public interface ProcessInstanceOutputPort extends Port<InstanceInputModel, PaginatedListResponse> {

   PaginatedListResponse handle(InstanceInputModel inputModel);

}
