package com.manager.crudservice.service;

import com.manager.crudservice.entity.Produto;
import com.manager.crudservice.exception.InfrastructureException;
import com.manager.crudservice.listener.ProdutoSendMessenge;
import com.manager.crudservice.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoSendMessenge produtoSendMessenge;

    public Produto save(Produto produto) {
        Produto protudoRetorno = produtoRepository.save(produto);
        produtoSendMessenge.sendMessage(protudoRetorno);
        return protudoRetorno;
    }

    public List<Produto> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        if(Objects.isNull(produtos) || produtos.isEmpty())
            throw new InfrastructureException("Nenhum produto encontrado.");
        return produtos;
    }

    public Produto findById(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new InfrastructureException("Produto não encontrado."));
        return produto;
    }

    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new InfrastructureException("Produto não encontrado."));
        produtoRepository.delete(produto);
    }
}
