package com.testevvvsis2.sistema2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testevvvsis2.sistema2.model.Modal;
import com.testevvvsis2.sistema2.service.ModalService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/modal")
public class ModalController {

    @Autowired
    private ModalService modalService;

    @GetMapping
    public Flux<Modal> buscarModais() {

        return modalService.buscarModais();

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Modal>> buscarModalPorId(@PathVariable("id") Long id) {

        return modalService.buscarModalPorId(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping
    public Mono<ResponseEntity<Modal>> cadastrarModal(@RequestBody Modal modal) {

        return modalService.cadastrarModal(modal)
            .map(modalM -> ResponseEntity.status(HttpStatus.CREATED).body(modal));

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Modal>> atualizarModal(@PathVariable("id") Long id, @RequestBody Modal modal) {

        return modalService.atualizarModal(id, modal)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletarModal(@PathVariable("id") long id) {

        return modalService.deletarModal(id)
            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
            .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}