package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados necessários para criação de uma nova subcategoria")

public class SubcategoriaRequest {

    @NotBlank(message = " O nome não pode estar vazio")
    @Size( min = 2 , max = 50)
    @Schema(description = "Nome da subcategoria", example = "Ração Seca", minLength = 2, maxLength = 50)
    private String nome;

    @Size( max = 200,message = " Descreva em até 200 caracteres")
    @Schema(description = "Descrição da subcategoria (máximo 200 caracteres)", example = "Ração seca para cães e gatos", maxLength = 200)
    private String descricao;

    @NotNull( message = "O Id é obrigatório")
    @Schema(description = "UUID da categoria à qual esta subcategoria pertence", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID categoriaId;

}
