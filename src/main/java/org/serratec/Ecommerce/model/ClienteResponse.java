package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}