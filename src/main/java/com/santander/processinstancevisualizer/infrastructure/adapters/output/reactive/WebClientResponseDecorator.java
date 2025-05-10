package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.exceptions.APIOutputAdapterException;

import reactor.core.publisher.Mono;

@Component
public class WebClientResponseDecorator {

   public <T> T execute(WebClient.ResponseSpec responseSpec, Class<T> responseType) {
      return responseSpec
            .onStatus(HttpStatusCode::is4xxClientError, responseStatus -> Mono.error(APIOutputAdapterException
                  .builder()
                  .httpStatus(HttpStatus.resolve(responseStatus.statusCode().value()))
                  .message("Error 4XX communicating with the external API")
                  .build()))
            .onStatus(HttpStatusCode::is5xxServerError, responseStatus -> Mono.error(APIOutputAdapterException
                  .builder()
                  .httpStatus(HttpStatus.resolve(responseStatus.statusCode().value()))
                  .message("Error 5XX communicating with the external API")
                  .build()))
            .bodyToMono(responseType)
            .block();
   }
}
