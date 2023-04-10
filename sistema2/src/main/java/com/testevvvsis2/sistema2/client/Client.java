package com.testevvvsis2.sistema2.client;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.testevvvsis2.sistema2.model.Modal;

import reactor.core.publisher.Mono;

@Configuration
public class Client {

    private final String API_BASE_URL = "https://teste-vvv-production.up.railway.app";

    public void atualizarModal(Long id, Modal modal) throws IOException {

        WebClient client = WebClient.create(API_BASE_URL);
        client.put()
            .uri("/modal/{id}", id.toString())
            .bodyValue(modal)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        
    }

    public Modal buscarPorId(Long id) {

        WebClient client = WebClient.create(API_BASE_URL);

        return client.get()
                .uri("/modal")
                .retrieve()
                .bodyToMono(Modal.class)
                .block();

    }

    public void cadastrar(Modal modal) {

        WebClient client = WebClient.create(API_BASE_URL);

        client.post()
            .uri("/modal")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(modal), Modal.class)
            .retrieve()
            .bodyToMono(Modal.class)
            .block();

    }

    public void deletarPorId(Long id) {

        WebClient client = WebClient.create(API_BASE_URL);

        client.delete()
            .uri("/modal")
            .retrieve()
            .bodyToMono(Void.class)
            .block();

    }

}