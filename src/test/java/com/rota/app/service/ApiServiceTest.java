package com.rota.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

class ApiServiceTest {

    @Test
    void testProcessarMelhorRota() {
        WebClient.Builder builderMock = mock(WebClient.Builder.class);
        when(builderMock.baseUrl(anyString())).thenReturn(builderMock);
        when(builderMock.build()).thenReturn(mock(WebClient.class));

        ApiService apiService = new ApiService(builderMock);

        List<String> enderecos = Arrays.asList("Endereço 1", "Endereço 2", "Endereço 3");
        String result = apiService.processarMelhorRota(enderecos);

        assertEquals("Sucesso! O Java recebeu 3 endereços para otimização.", result);
    }
}
