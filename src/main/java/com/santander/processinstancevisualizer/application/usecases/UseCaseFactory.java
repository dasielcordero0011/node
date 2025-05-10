package com.santander.processinstancevisualizer.application.usecases;

public interface UseCaseFactory {
    GetProcessInstanceUseCase getGetProcessInstanceUseCase();
    GetInstanceDetailsUseCase getGetInstanceDetailsUseCase();

}
