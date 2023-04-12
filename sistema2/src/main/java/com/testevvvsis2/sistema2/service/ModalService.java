package com.testevvvsis2.sistema2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.testevvvsis2.sistema2.model.Modal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ModalService {

    @Autowired
    private WebClient webClient;

    public Mono<Modal> cadastrarModal(Modal modal) {

        return webClient.post()
            .uri("/modal")
            .bodyValue(modal)
            .retrieve()
            .bodyToMono(Modal.class);

    }

    public Mono<Modal> buscarModalPorId(Long id) {

        return webClient.get()
            .uri("/modal/{id}", id)
            .retrieve()
            .bodyToMono(Modal.class);

    }

    public Flux<Modal> buscarModais() {

        return webClient.get()
            .uri("/modal")
            .retrieve()
            .bodyToFlux(Modal.class);

    }

    public Mono<Modal> atualizarModal(Long id, Modal modal) {

        return webClient.put()
            .uri("/modal/{id}", id)
            .bodyValue(modal)
            .retrieve()
            .bodyToMono(Modal.class);

    }

    public Mono<Void> deletarModal(Long id) {

        return webClient.delete()
            .uri("/modal/{id}", id)
            .retrieve()
            .bodyToMono(Void.class);

    }

}