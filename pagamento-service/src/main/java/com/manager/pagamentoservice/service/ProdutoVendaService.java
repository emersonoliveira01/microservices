package com.manager.pagamentoservice.service;

import com.manager.pagamentoservice.entity.ProdutoVenda;
import com.manager.pagamentoservice.repository.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoVendaService {

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public ProdutoVenda create(ProdutoVenda produtoVenda) {
        return produtoVendaRepository.save(produtoVenda);
    }

    public List<ProdutoVenda> findyAll() {
        return produtoVendaRepository.findAll();
    }

    public ProdutoVenda findById(Long id) {
        return produtoVendaRepository.findById(id).orElse(null);
    }
}
