package com.santander.processinstancevisualizer.application.ports.input;

import com.santander.processinstancevisualizer.application.ports.Port;
import com.santander.processinstancevisualizer.application.ports.input.model.InstanceDetailsInputModel;
import com.santander.processinstancevisualizer.domain.model.ProcessInstance;

public interface ProcessInstanceDetailsInputPort extends Port<InstanceDetailsInputModel, ProcessInstance> {

   ProcessInstance handle(InstanceDetailsInputModel model);
}
