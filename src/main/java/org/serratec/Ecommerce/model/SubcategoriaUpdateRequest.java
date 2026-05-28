package org.serratec.Ecommerce.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para atualização de uma subcategoria existente. Só os campos enviados serão alterados.")

public class SubcategoriaUpdateRequest {

    @Size( min = 2 , max = 50)
    @Schema(description = "Novo nome da subcategoria", example = "Ração Úmida", minLength = 2, maxLength = 50)
    private String nome;

    @Size( max = 200,message = " Descreva em até 200 caracteres")
    @Schema(description = "Nova descrição da subcategoria (máximo 200 caracteres)", example = "Ração úmida e patês para pets", maxLength = 200)
    private String descricao;

}
