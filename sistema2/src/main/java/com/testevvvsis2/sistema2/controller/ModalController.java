package com.testevvvsis2.sistema2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testevvvsis2.sistema2.model.Modal;
import com.testevvvsis2.sistema2.service.ModalService;

@RestController
@RequestMapping("/modal")
public class ModalController {
    
    @Autowired
    private ModalService modalService;

    @PostMapping
    public ResponseEntity<Modal> cadastrar(@RequestBody Modal modal) {
        
        modalService.cadastrar(modal);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String atualizarModal(@PathVariable("id") Long id, @RequestBody Modal modal) {

        return modalService.atualizarModal(id, modal);

    }

}