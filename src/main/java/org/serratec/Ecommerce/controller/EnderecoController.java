package org.serratec.Ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.Ecommerce.model.EnderecoResponse;
import org.serratec.Ecommerce.model.EnderecoUpdateRequest;
import org.serratec.Ecommerce.model.ErrorResponse;
import org.serratec.Ecommerce.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/enderecos")
@Tag(name = "Endereços", description = "API para gerenciamento de endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    @Operation(summary = "Listar endereços", description = "Retorna todos os endereços cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum endereço cadastrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<EnderecoResponse>> listarEnderecos() {
        List<EnderecoResponse> enderecos = enderecoService.listarEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/cep/{cep}")
    @Operation(summary = "Buscar endereço por CEP", description = "Busca um endereço específico pelo CEP (apenas números)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponse.class))),
            @ApiResponse(responseCode = "400", description = "CEP inválido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o CEP informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(
            @Parameter(description = "CEP do endereço (apenas números)", example = "01001000", required = true)
            @PathVariable String cep) {
        EnderecoResponse endereco = enderecoService.buscarEndereco(cep);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar endereço por ID", description = "Busca um endereço específico pelo seu UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponse.class))),
            @ApiResponse(responseCode = "400", description = "ID inválido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EnderecoResponse> buscarEnderecoPorId(
            @Parameter(description = "UUID do endereço", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id) {
        EnderecoResponse endereco = enderecoService.buscarEnderecoPorId(id);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar endereço", description = "Atualiza os dados de um endereço existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EnderecoResponse> atualizarEndereco(
            @Parameter(description = "UUID do endereço", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody EnderecoUpdateRequest endereco) {
        EnderecoResponse enderecoAtualizado = enderecoService.atualizarEndereco(endereco, id);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar endereço", description = "Remove um endereço do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido ou endereço vinculado a um cliente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deletarEndereco(
            @Parameter(description = "UUID do endereço", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}