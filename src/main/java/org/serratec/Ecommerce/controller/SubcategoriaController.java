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
    @Operation(summary = "Criar subcategoria", description = "Cria uma nova subcategoria vinculada a uma categoria existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou subcategoria já cadastrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada com esse ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Subcategoria já existe com este nome para esta categoria",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> criarSubcategoria (@Valid @RequestBody SubcategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoriaService.criarSubcategoria(request));
    }

    @GetMapping
    @Operation(summary = "Listar subcategorias", description = "Retorna todas as subcategorias cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma subcategoria cadastrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<SubcategoriaResponse>> listarSubcategorias() {
        return ResponseEntity.ok(subcategoriaService.listarSubcategorias());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar subcategoria por ID", description = "Busca uma subcategoria específica pelo seu UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "ID inválido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma subcategoria encontrada com esse ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> buscarPorId(
            @Parameter(description = "ID da subcategoria", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(subcategoriaService.buscarPorId(id));
    }

    @GetMapping("/nome")
    @Operation(summary = "Buscar subcategoria por nome", description = "Busca uma subcategoria específica pelo seu nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategoria encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Nome não informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Subcategoria não foi encontrada com esse nome",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> buscarPorNome(
            @Parameter(description = "Nome da subcategoria", example = "Smartphones", required = true)
            @RequestParam String nome) {
        return ResponseEntity.ok(subcategoriaService.buscarPorNome(nome));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar subcategorias por categoria", description = "Retorna todas as subcategorias de uma categoria específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "ID da categoria inválido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada ou sem subcategorias cadastradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<SubcategoriaResponse>> listarPorCategoria(
            @Parameter(description = "ID da categoria", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID categoriaId) {
        return ResponseEntity.ok(subcategoriaService.listarPorCategoria(categoriaId));
    }

    @PutMapping ("/{id}")
    @Operation(summary = "Atualizar subcategoria", description = "Atualiza os dados de uma subcategoria existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubcategoriaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou nome já cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma subcategoria encontrada com esse ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Nome da subcategoria já existe para esta categoria",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SubcategoriaResponse> atualizarSubcategoria(
            @Parameter(description = "ID da subcategoria", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody SubcategoriaUpdateRequest request) {
        return ResponseEntity.ok(subcategoriaService.atualizarSubcategoria(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar subcategoria", description = "Remove uma subcategoria do sistema (apenas se não tiver produtos vinculados)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido ou não é possível deletar uma subcategoria com produtos vinculados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma subcategoria encontrada com esse ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deletarSubcategoria(
            @Parameter(description = "ID da subcategoria", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id) {
        subcategoriaService.deletarSubcategoria(id);
        return ResponseEntity.noContent().build();
    }
}