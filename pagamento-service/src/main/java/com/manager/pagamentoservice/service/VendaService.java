package com.manager.pagamentoservice.service;

import com.manager.pagamentoservice.entity.ProdutoVenda;
import com.manager.pagamentoservice.entity.Venda;
import com.manager.pagamentoservice.repository.ProdutoVendaRepository;
import com.manager.pagamentoservice.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public Venda create(Venda venda) {
        Venda vendaRetorno = vendaRepository.save(venda);
        List<ProdutoVenda> produtoVendas = new ArrayList<>();
        vendaRetorno.getProdutos().stream().forEach(item -> {
            ProdutoVenda produtoVenda = new ProdutoVenda();
            produtoVenda.setVenda(vendaRetorno);
            produtoVendas.add(produtoVendaRepository.save(item));
        });
        vendaRetorno.setProdutos(produtoVendas);
        return vendaRetorno;
    }

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Venda findyById(Long id) {
        return vendaRepository.findById(id).orElse(null);
    }

}
