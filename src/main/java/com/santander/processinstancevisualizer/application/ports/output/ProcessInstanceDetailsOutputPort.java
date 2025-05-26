package com.santander.processinstancevisualizer.application.ports.output;

import com.santander.processinstancevisualizer.application.ports.Port;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

public interface ProcessInstanceDetailsOutputPort extends Port<InstanceDetailsInputModel, ProcessInstance> {

   ProcessInstance handle(InstanceDetailsInputModel inputModel);
}
