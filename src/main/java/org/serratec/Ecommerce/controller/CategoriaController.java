package org.serratec.Ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.Ecommerce.model.CategoriaRequest;
import org.serratec.Ecommerce.model.CategoriaResponse;
import org.serratec.Ecommerce.model.CategoriaUpdateRequest;
import org.serratec.Ecommerce.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {

        this.categoriaService = categoriaService;
    }
    // referente ao POST
    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria (@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(request));
    }

    //referente ao GET
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<CategoriaResponse>> listarCategoriasAtivas() {
        return ResponseEntity.ok(categoriaService.listarCategoriasAtivas());
    }

    @GetMapping("/inativas")
    public ResponseEntity<List<CategoriaResponse>> listarCategoriasInativas() {
        return ResponseEntity.ok(categoriaService.listarCategoriasInativas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
    @GetMapping("/nome")
    public ResponseEntity<CategoriaResponse> buscarPorNome( @RequestParam String nome) {
        return ResponseEntity.ok(categoriaService.buscarPorNome(nome));
    }

    // referente ao PUT
    @PutMapping ("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(@PathVariable UUID id , @Valid @RequestBody CategoriaUpdateRequest request) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria( id, request));
    }

    // referente ao PATCH
    @PatchMapping("/{id}/status")
    public ResponseEntity<CategoriaResponse> alterarStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaService.alterarStatus(id));
    }

    // referente ao DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }





}
