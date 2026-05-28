package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoUpdateRequest {

    @Schema(nullable = true, description = "FK da categoria dentro do ProdutoUpdateRequest para atualizar", example = "aea55b15-802c-4f3d-a88f-9fb11fb36d86")
    private UUID categoriaId;

    @Schema(nullable = true, description = "FK da subcategoria dentro do ProdutoUpdateRequest para atualizar", example = "aea55b15-802c-4f3d-a88f-9fb11fb36d86")
    private UUID subcategoriaId;

    @Schema(nullable = true, description = "Nome do produto a ser atualizado")
    private String nomeProduto;

    @Schema(nullable = true, description = "Valor do produto a ser atualizado")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

}
