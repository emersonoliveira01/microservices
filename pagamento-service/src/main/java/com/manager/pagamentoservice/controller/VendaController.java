package com.manager.pagamentoservice.controller;

import com.manager.pagamentoservice.entity.Venda;
import com.manager.pagamentoservice.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/save")
    public Venda create(@RequestBody Venda venda) {
        return vendaService.create(venda);
    }
}
