package com.santander.processinstancevisualizer.application.usecases;

import com.santander.processinstancevisualizer.application.ApplicationComponent;

@FunctionalInterface
public interface UseCase<I, O> extends ApplicationComponent<I, O> {
    O execute(I input);
}
