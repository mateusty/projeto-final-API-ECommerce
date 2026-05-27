package org.serratec.Ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.Ecommerce.model.ProdutoRequest;
import org.serratec.Ecommerce.model.ProdutoResponse;
import org.serratec.Ecommerce.model.ProdutoUpdateRequest;
import org.serratec.Ecommerce.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/produtos")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarProduto(@Valid @RequestBody ProdutoRequest produto) {
        this.produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscarProduto(
            @RequestParam(required = false) String nomeProduto,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String subcategoria) {
        List<ProdutoResponse> produtos = this.produtoService.buscarProduto(nomeProduto, categoria, subcategoria);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoUpdateRequest produto) {
        this.produtoService.atualizarProduto(id, produto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable UUID id) {
        this.produtoService.removerProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
