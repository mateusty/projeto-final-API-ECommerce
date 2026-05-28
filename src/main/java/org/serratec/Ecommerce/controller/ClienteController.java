package org.serratec.Ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.model.ClienteRequest;
import org.serratec.Ecommerce.model.ClienteResponse;
import org.serratec.Ecommerce.model.ClienteUpdateRequest;
import org.serratec.Ecommerce.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes do PetShop")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID",
            description = "Retorna os dados completos de um cliente específico, incluindo sua lista de endereços.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "ID inválido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class)))
    })
    public ResponseEntity<ClienteResponse> buscarPorId(
            @Parameter(description = "UUID do cliente", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping()
    @Operation(summary = "Buscar clientes", description = "Retorna uma lista de clientes. Se nenhum parâmetro for informado, retorna todos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class)))
    })
    public ResponseEntity<List<ClienteResponse>> buscarCliente(
            @Parameter(description = "CPF do cliente (apenas números)", example = "12345678900")
            @RequestParam(required = false) String cpf,
            @Parameter(description = "Email do cliente", example = "tutor@pet.com.br")
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(clienteService.buscarCliente(cpf, email));
    }

    @PostMapping()
    @Operation(summary = "Cadastrar novo cliente", description = "Cria um novo cliente com seus endereços. O CEP é automaticamente completado via ViaCEP.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou CEP não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "409", description = "Dados já cadastrados (CPF ou Email já existente)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class)))
    })
    public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody ClienteRequest cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.inserirCliente(cliente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente. Apenas os campos enviados serão alterados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "409", description = "Dados já cadastrados (CPF ou Email já existe para outro cliente)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class)))
    })
    public ResponseEntity<Cliente> atualizarCliente(@Valid @RequestBody ClienteUpdateRequest cliente,
                                                    @PathVariable UUID id) {
        return ResponseEntity.ok(this.clienteService.atualizarCliente(cliente, id));
    }
}