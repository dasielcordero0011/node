package com.santander.processinstancevisualizer.application.ports.input;

public interface InputPortsFactory {

   ProcessInstanceInputPort getProcessInstanceInputPort();

   ProcessInstanceDetailsInputPort getProcessInstanceDetailsInputPort();

}
