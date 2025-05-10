package com.santander.processinstancevisualizer.application.ports.input;

import com.santander.processinstancevisualizer.application.ports.input.impl.InputPortsFactoryImpl;
import com.santander.processinstancevisualizer.application.usecases.UseCaseFactory;

public class InputPortsFactoryBuilder {

   public static InputPortsFactory getInstance(UseCaseFactory useCaseFactory) {
      return InputPortsFactoryImpl.builder().useCaseFactory(useCaseFactory).build();
   }
}
