package org.serratec.Ecommerce.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.StatusCategoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados completos de Resposta")

public class CategoriaResponse {

    @Schema(description = "ID único da categoria", example = "550e8400-e29b-41d4-a716-446655440000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Nome da categoria", example = "Alimentação")
    private String nome;

    @Schema(description = "Descrição da categoria", example = "Produtos alimentícios para pets")
    private String descricao;

    @Schema(description = "Status atual da categoria (ATIVA ou INATIVA)", example = "ATIVA")
    private StatusCategoria status;



    public CategoriaResponse (Categoria categoria) {

        this.id = categoria.getId();

        this.nome = categoria.getNome();

        this.descricao = categoria.getDescricao();

        this.status = categoria.getStatusCategoria();
    }

}

