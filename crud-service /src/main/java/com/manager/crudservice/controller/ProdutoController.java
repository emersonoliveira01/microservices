package com.manager.crudservice.controller;

import com.manager.crudservice.entity.Produto;
import com.manager.crudservice.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    public Produto save(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @GetMapping("/findall")
    public List<Produto> findAll() {
         return produtoService.findAll();
    }

    @GetMapping("/{produto}")
    public Produto findyById( @PathVariable Long produtoId) {
        return produtoService.findById(produtoId);
    }

    @DeleteMapping("delete/{produto}")
    public void delete(@PathVariable Long produtoId) {
        produtoService.delete(produtoId);
    }
}
