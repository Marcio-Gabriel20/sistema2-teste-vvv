package com.testevvvsis2.sistema2.client;

import java.io.IOException;

import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testevvvsis2.sistema2.model.Modal;

import reactor.core.publisher.Mono;

public class Client {

    private final String API_BASE_URL = "https://teste-vvv-production.up.railway.app";
    private final String ENDPOINT_PUT_PATH = "/modal/{id}";

    public Modal chamarEndpointPut(Long id, Modal modal) throws IOException {

        WebClient client = WebClient.create(API_BASE_URL);
        Mono<String> response = client.put()
                .uri(ENDPOINT_PUT_PATH, id.toString())
                .bodyValue(modal)
                .retrieve()
                .bodyToMono(String.class);

        String resultado = response.block();

        ObjectMapper mapper = new ObjectMapper();
        Modal modalJ = mapper.readValue(resultado, Modal.class);

        return modalJ;
        
    }

}