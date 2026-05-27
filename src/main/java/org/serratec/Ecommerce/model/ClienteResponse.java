package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.entity.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa os dados completos de um cliente na resposta da API")
public class ClienteResponse {

    @Schema(description = "ID único do cliente", example = "550e8400-e29b-41d4-a716-446655440000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Nome completo do cliente/tutor", example = "João Silva", required = true)
    private String nome;

    @Schema(description = "Telefone para contato (10 ou 11 dígitos)", example = "11987654321", pattern = "^\\d{10,11}$")
    private String telefone;

    @Schema(description = "Email do cliente (usado para login e notificações)", example = "joao@email.com", required = true)
    private String email;

    @Schema(description = "CPF do cliente (apenas números)", example = "12345678900", required = true)
    private String cpf;

    @Schema(description = "Lista de endereços associados ao cliente")
    private List<EnderecoResponse> enderecos;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.enderecos = new ArrayList<>();
        cliente.getEnderecos().forEach(endereco -> {
            this.enderecos.add(new EnderecoResponse(endereco));
        });
    }
}