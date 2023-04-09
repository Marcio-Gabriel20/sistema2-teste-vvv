package com.testevvvsis2.sistema2.client;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testevvvsis2.sistema2.model.Modal;

import reactor.core.publisher.Mono;

@Configuration
public class Client {

    private final String API_BASE_URL = "https://teste-vvv-production.up.railway.app";
    // private final String ENDPOINT_PUT_PATH = "/modal/{id}";

    public Modal atualizarModal(Long id, Modal modal) throws IOException {

        WebClient client = WebClient.create(API_BASE_URL);
        Mono<String> response = client.put()
                .uri("/modal/{id}", id.toString())
                .bodyValue(modal)
                .retrieve()
                .bodyToMono(String.class);

        String resultado = response.block();

        ObjectMapper mapper = new ObjectMapper();
        Modal modalJ = mapper.readValue(resultado, Modal.class);

        return modalJ;
        
    }

    /*public String cadastrarModal(Modal modal) {

        WebClient client = WebClient.create(API_BASE_URL);
        Mono<String> response = client.post()
                .uri("/modal")
                .bodyValue(modal)
                .retrieve()
                .exchangeToMono(response -> {

                    if(response.statusCode().)

                })
        
        String resultado = response.block();

        return resultado;

    }*/

    

}