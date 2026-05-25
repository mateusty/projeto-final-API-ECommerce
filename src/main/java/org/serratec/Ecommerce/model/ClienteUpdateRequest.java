package org.serratec.Ecommerce.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteUpdateRequest {
    private String nome;

    @CPF(message = "O CPF informado é inválido")
    private String cpf;

    @Email(message = "O E-mail informado é inválido")
    private String email;

    @Pattern(regexp = "\\d{10,11}", message = "O Telefone deve conter 10 ou 11 dígitos")
    private String telefone;

    private List<EnderecoUpdateRequest> enderecos;
}
