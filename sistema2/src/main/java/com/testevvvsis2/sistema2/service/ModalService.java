package com.testevvvsis2.sistema2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import com.testevvvsis2.sistema2.client.Client;
import com.testevvvsis2.sistema2.model.Modal;

@Service
public class ModalService {

    @Autowired
    private RestTemplate restTemplate;

    private final Client client;

    public ModalService(@Autowired Client client) {

        this.client = client;

    }

    public void cadastrar(Modal modal) {

        String url = "https://teste-vvv-production.up.railway.app/modal";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Modal> requestEntity = new HttpEntity<>(modal, headers);

        ResponseEntity<Modal> responseEntity = restTemplate.postForEntity(url, requestEntity, Modal.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {

            Modal responseBody = responseEntity.getBody();
            System.out.println("Resposta: " + responseBody);

        } else {

            System.out.println("Erro na resposta: " + responseEntity.getStatusCode());

        }

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

    /*public void atualizarStatus(Long id, Modal modal) {

        String url = "https://teste-vvv-production.up.railway.app/modal/"+id;

        Modal modalAtualizado = restTemplate.getForObject(url, Modal.class, id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Modal> requestEntity = new HttpEntity<>(modalAtualizado, headers);

        ResponseEntity<Modal> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Modal.class, id);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Modal responseBody = responseEntity.getBody();
            System.out.println("Resposta: " + responseBody);
        } else {
            System.out.println("Erro na resposta: " + responseEntity.getStatusCode());
        }

    }*/

}