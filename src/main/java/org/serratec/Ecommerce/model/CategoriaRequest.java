package org.serratec.Ecommerce.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = " Dados para criação de nova Categoria" )
public class CategoriaRequest {

    @NotBlank(message = " O nome não pode estar vazio")
    @Size( min = 2 , max = 50)
    @Schema(description = "Nome da Categoria",example = "Alimentação",minLength = 2, maxLength = 50)
    private String nome;

    @Size( max = 200,message = " Descreva em até 200 caracteres")
    @Schema(description = "Descrição da categoria (máximo 200 caracteres)", example = "Produtos alimentícios para pets", maxLength = 200)
    private String descricao;
}