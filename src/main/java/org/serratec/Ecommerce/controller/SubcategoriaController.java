package org.serratec.Ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.Ecommerce.model.*;
import org.serratec.Ecommerce.service.SubcategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/subcategorias")

public class SubcategoriaController {
    private final SubcategoriaService subcategoriaService;

    public SubcategoriaController (SubcategoriaService subcategoriaService){
        this.subcategoriaService = subcategoriaService;
    }
    // referente ao POST
    @PostMapping
    public ResponseEntity<SubcategoriaResponse> criarSubcategoria (@Valid @RequestBody SubcategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoriaService.criarSubcategoria(request));
    }
    //referente ao GET
    @GetMapping
    public ResponseEntity<List<SubcategoriaResponse>> listarSubcategorias() {
        return ResponseEntity.ok(subcategoriaService.listarSubcategorias());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubcategoriaResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(subcategoriaService.buscarPorId(id));
    }
    @GetMapping("/nome")
    public ResponseEntity<SubcategoriaResponse> buscarPorNome( @RequestParam String nome) {
        return ResponseEntity.ok(subcategoriaService.buscarPorNome(nome));
    }
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<SubcategoriaResponse>> listarPorCategoria(@PathVariable UUID categoriaId) {
        return ResponseEntity.ok(subcategoriaService.listarPorCategoria(categoriaId));
    }
    // referente ao PUT
    @PutMapping ("/{id}")
    public ResponseEntity<SubcategoriaResponse> atualizarSubcategoria(@PathVariable UUID id , @Valid @RequestBody SubcategoriaUpdateRequest request) {
        return ResponseEntity.ok(subcategoriaService.atualizarSubcategoria( id, request));
    }
    // referente ao DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSubcategoria(@PathVariable UUID id) {
        subcategoriaService.deletarSubcategoria(id);
        return ResponseEntity.noContent().build();
    }


}
