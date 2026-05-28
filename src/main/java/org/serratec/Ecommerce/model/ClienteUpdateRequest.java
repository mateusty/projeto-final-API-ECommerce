package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO para atualização parcial ou total dos dados do cliente - todos os campos são opcionais")
public class ClienteUpdateRequest {

    @Schema(description = "Nome completo do cliente/tutor",
            example = "João Silva Atualizado",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nome;

    @CPF(message = "O CPF informado é inválido")
    @Schema(description = "CPF do cliente (apenas números, 11 dígitos)",
            example = "12345678900",
            pattern = "^\\d{11}$",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String cpf;

    @Email(message = "O E-mail informado é inválido")
    @Schema(description = "Email para contato e notificações do sistema",
            example = "joao.novo@email.com",
            format = "email",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;

    @Pattern(regexp = "\\d{10,11}", message = "O Telefone deve conter 10 ou 11 dígitos")
    @Schema(description = "Telefone para contato com DDD (10 ou 11 dígitos)",
            example = "11999999999",
            pattern = "^\\d{10,11}$",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String telefone;

    @Schema(description = "Lista de endereços do cliente - substitui a lista existente se informada",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<EnderecoUpdateRequest> enderecos;
}