package com.rota.app.controller;

import com.rota.app.service.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApiControllerTest {

    private ApiService apiServiceMock;
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        apiServiceMock = mock(ApiService.class);
        apiController = new ApiController(apiServiceMock);
    }

    @Test
    void testGetApi() {
        String expectedResponse = "Dados da API mockados";
        when(apiServiceMock.buscarDados()).thenReturn(expectedResponse);

        String result = apiController.getApi();

        assertEquals(expectedResponse, result);
        verify(apiServiceMock, times(1)).buscarDados();
    }

    @Test
    void testOtimizarRota() {
        List<String> enderecos = Arrays.asList("Endereço A", "Endereço B");
        String expectedResponse = "Sucesso mockado";
        when(apiServiceMock.processarMelhorRota(enderecos)).thenReturn(expectedResponse);

        String result = apiController.otimizarRota(enderecos);

        assertEquals(expectedResponse, result);
        verify(apiServiceMock, times(1)).processarMelhorRota(enderecos);
    }
}
