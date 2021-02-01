package com.manager.pagamentoservice.service;

import com.manager.pagamentoservice.entity.Produto;
import com.manager.pagamentoservice.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

   public Produto findyById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

}
