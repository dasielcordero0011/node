package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.exceptions.APIOutputAdapterException;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class WebClientResponseDecoratorTest {

   private WebClientResponseDecorator decorator;

   private WebClient.ResponseSpec responseSpec;

   @BeforeEach
   void setUp() {
      decorator = new WebClientResponseDecorator();
      responseSpec = mock(WebClient.ResponseSpec.class);
   }

   @Test
   void testExecute_With4xxError() {
      when(responseSpec.onStatus(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(responseSpec);
      when(responseSpec.bodyToMono(String.class)).thenReturn(
            Mono.error(new APIOutputAdapterException(HttpStatus.NOT_FOUND, "Error 4XX communicating with the external API", null)));

      APIOutputAdapterException exception = assertThrows(APIOutputAdapterException.class, () -> {
         decorator.execute(responseSpec, String.class);
      });

      assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
      assertEquals("Error 4XX communicating with the external API", exception.getMessage());
   }

   @Test
   void testExecute_With5xxError() {
      when(responseSpec.onStatus(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(responseSpec);
      when(responseSpec.bodyToMono(String.class)).thenReturn(
            Mono.error(new APIOutputAdapterException(HttpStatus.INTERNAL_SERVER_ERROR, "Error 5XX communicating with the external API", null)));

      APIOutputAdapterException exception = assertThrows(APIOutputAdapterException.class, () -> {
         decorator.execute(responseSpec, String.class);
      });

      assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
      assertEquals("Error 5XX communicating with the external API", exception.getMessage());
   }
}
