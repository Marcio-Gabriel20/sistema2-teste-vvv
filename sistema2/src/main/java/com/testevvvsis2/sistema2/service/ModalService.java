package com.testevvvsis2.sistema2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;

import com.testevvvsis2.sistema2.client.Client;
import com.testevvvsis2.sistema2.model.Modal;

import reactor.core.publisher.Mono;

@Service
public class ModalService {

    private final Client client;

    public ModalService(@Autowired Client client) {

        this.client = client;

    }

    public void cadastrar(Modal modal) {

        String url = "https://teste-vvv-production.up.railway.app/modal";

        WebClient webClient = WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        webClient.post()
            .body(BodyInserters.fromValue(modal))
            .exchangeToMono(response -> {
                if (response.statusCode().is2xxSuccessful()) {
                    return response.bodyToMono(Modal.class)
                            .doOnSuccess(responseBody -> System.out.println("Resposta: " + responseBody));
                } else {
                    return response.bodyToMono(String.class)
                            .flatMap(errorMessage -> {
                                System.out.println("Erro na resposta: " + response.statusCode() + " - " + errorMessage);
                                return Mono.empty();
                            });
                }
            })
            .subscribe();

    }

    public String atualizarModal(Long id, Modal newModal) {

        try {
         
            client.chamarEndpointPut(id, newModal);
            return "Dados alterados com sucesso";

        } catch (Exception e) {
            
            System.out.println(e);
            return "\nErro ao atualizar objeto";

        }

    }

}