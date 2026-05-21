package org.serratec.Ecommerce.controller;

import org.serratec.Ecommerce.service.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/produtos")
public class Produto {

    private final ProdutoService produtoService;

    public Produto(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
}
