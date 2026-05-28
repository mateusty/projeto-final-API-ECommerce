package org.serratec.Ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.Ecommerce.model.*;
import org.serratec.Ecommerce.model.ErrorResponse;
import org.serratec.Ecommerce.service.SubcategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/subcategorias")
@Tag(name = "Subcategorias", description = "Gerenciamento de subcategorias")
public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    public SubcategoriaController (SubcategoriaService subcategoriaService){
        this.subcategoriaService = subcategoriaService;
    }

    @PostMapping
    @Operation(summary = "Criar subcategoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criada com sucesso",
                    content = @Content(schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> criarSubcategoria (@Valid @RequestBody SubcategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoriaService.criarSubcategoria(request));
    }

    @GetMapping
    @Operation(summary = "Listar subcategorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma subcategoria cadastrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<SubcategoriaResponse>> listarSubcategorias() {
        return ResponseEntity.ok(subcategoriaService.listarSubcategorias());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar subcategoria por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria encontrada",
                    content = @Content(schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> buscarPorId(
            @Parameter(description = "ID da subcategoria")
            @PathVariable UUID id) {
        return ResponseEntity.ok(subcategoriaService.buscarPorId(id));
    }

    @GetMapping("/nome")
    @Operation(summary = "Buscar subcategoria por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria encontrada",
                    content = @Content(schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Nome não informado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> buscarPorNome(
            @Parameter(description = "Nome da subcategoria")
            @RequestParam String nome) {
        return ResponseEntity.ok(subcategoriaService.buscarPorNome(nome));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar subcategorias por categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada ou sem subcategorias",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<SubcategoriaResponse>> listarPorCategoria(
            @Parameter(description = "ID da categoria")
            @PathVariable UUID categoriaId) {
        return ResponseEntity.ok(subcategoriaService.listarPorCategoria(categoriaId));
    }

    @PutMapping ("/{id}")
    @Operation(summary = "Atualizar subcategoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> atualizarSubcategoria(
            @Parameter(description = "ID da subcategoria")
            @PathVariable UUID id , @Valid @RequestBody SubcategoriaUpdateRequest request) {
        return ResponseEntity.ok(subcategoriaService.atualizarSubcategoria( id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar subcategoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Subcategoria com produtos vinculados",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subcategoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deletarSubcategoria(
            @Parameter(description = "ID da subcategoria")
            @PathVariable UUID id) {
        subcategoriaService.deletarSubcategoria(id);
        return ResponseEntity.noContent().build();
    }


}