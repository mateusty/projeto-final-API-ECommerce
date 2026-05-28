package org.serratec.Ecommerce.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Subcategoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa os dados completos de uma subcategoria na resposta da API")

public class SubcategoriaResponse {

    @Schema(description = "ID único da subcategoria", example = "550e8400-e29b-41d4-a716-446655440000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Nome da subcategoria", example = "Ração Seca")
    private String nome;

    @Schema(description = "Descrição da subcategoria", example = "Ração seca para cães e gatos")
    private String descricao;

    @Schema(description = "Nome da categoria à qual esta subcategoria pertence", example = "Alimentação")
    private String nomeCategoria;


    public SubcategoriaResponse(Subcategoria subcategoria){
        this.id =  subcategoria.getId();
        this.nome = subcategoria.getNome();
        this.descricao = subcategoria.getDescricao();
        this.nomeCategoria = subcategoria.getCategoria().getNome();
    }
}
