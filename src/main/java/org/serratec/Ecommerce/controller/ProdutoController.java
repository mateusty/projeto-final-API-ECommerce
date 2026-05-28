package org.serratec.Ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
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
@Tag(name = "Produto", description = "Classe responsável pela manipulação dos produtos do E-Commerce")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(
            summary = "Cadastrar",
            description = "Cadastrar produto dentro de uma categoria e subcategoria."
    )
    @ApiResponses (
            value = {
                    @ApiResponse(
                    description = "Produto cadastrado com sucesso.",
                    responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Falha ao cadastrar: Inválido ou já cadastrado.",
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = InvalidDataException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Falha ao encontrar categoria ou subcategoria.",
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    )
    })
    @PostMapping
    public ResponseEntity<Void> cadastrarProduto(@Valid @RequestBody ProdutoRequest produto) {
        this.produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Buscar",
            description = "Endpoint para buscar produtos."
    )

    @ApiResponses (
            value = {
                    @ApiResponse(
                            description = "Produtos buscados com sucesso.",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscarProduto(
            @RequestParam(required = false) String nomeProduto,
            @RequestParam(required = false) UUID categoriaId,
            @RequestParam(required = false) UUID subcategoriaId) {
        List<ProdutoResponse> produtos = this.produtoService.buscarProduto(nomeProduto, categoriaId, subcategoriaId);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @Operation(
            summary = "Atualizar",
            description = "Atualização dos dados de um produto existente."
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "ID do produto que será atualizado",
                            in = ParameterIn.PATH
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Produto atualizado com sucesso.",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Produto não encontrado.",
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable UUID id, @RequestBody ProdutoUpdateRequest produto) {
        this.produtoService.atualizarProduto(id, produto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Remover",
            description = "Remover produto cadastrado."
    )
    @Parameters(
            value = {
                    @Parameter(
                            name = "id",
                            description = "ID do produto",
                            in = ParameterIn.PATH
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Produto removido com sucesso.",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Produto não encontrado.",
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable UUID id) {
        this.produtoService.removerProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
