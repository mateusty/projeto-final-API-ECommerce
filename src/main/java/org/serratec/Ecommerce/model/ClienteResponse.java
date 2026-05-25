package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Endereco;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private String nome;

    private String telefone;

    private String email;

    private String cpf;

    private List<EnderecoResponse> enderecos;

    public ClienteResponse(ClienteRequest cliente, List<Endereco> enderecos) {
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        enderecos.forEach(endereco -> {
            this.enderecos.add(new EnderecoResponse(endereco));
        }
        );
    }
}