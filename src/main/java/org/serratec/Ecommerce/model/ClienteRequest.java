package org.serratec.Ecommerce.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "O Nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "O CPF informado é inválido ")
    private String cpf;

    @NotBlank(message = "O E-mail é obrigatório")
    @Email(message = "O E-mail informado é inválido")
    private String email;

    @NotBlank(message = "O Telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O Telefone deve conter 10 ou 11 dígitos")
    private String telefone;

    @NotEmpty(message = "O cliente necessita de ao menos 1 endereço")
    private List<EnderecoRequest> enderecos;
}