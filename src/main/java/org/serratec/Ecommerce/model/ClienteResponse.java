package org.serratec.Ecommerce.model;

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
public class ClienteResponse {

    private UUID id;

    private String nome;

    private String telefone;

    private String email;

    private String cpf;

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
                }
        );
    }
}