package org.serratec.Ecommerce.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = " Dados para Atualização de Categoria . Só campos enviados são atualizados ")
public class CategoriaUpdateRequest {


    @Size( min = 2 , max = 50)
    @Schema(description = "Novo nome da categoria", example = "Higiene", minLength = 2, maxLength = 50)
    private String nome;

    @Size( max = 200,message = " Descreva em até 200 caracteres")
    @Schema(description = "Nova descrição da categoria (máximo 200 caracteres)", example = "Produtos de higiene e limpeza ", maxLength = 200)
    private String descricao;
}
