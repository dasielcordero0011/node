package com.santander.processinstancevisualizer.application.ports;

import com.santander.processinstancevisualizer.application.ApplicationComponent;

@FunctionalInterface
public interface Port<I, O> extends ApplicationComponent<I, O> {
    O handle(I inputModel);
}
