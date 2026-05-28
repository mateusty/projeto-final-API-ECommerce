package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoRequest {

    @NotNull
    @Schema(nullable = false, description = "FK da categoria dentro da requisição do produto.")
    private UUID categoriaId;

    @NotNull
    @Schema(nullable = false, description = "FK da subcategoria dentro da requisição do produto.")
    private UUID subcategoriaId;

    @NotBlank
    @Schema(nullable = false, description = "Nome do produto a ser passado na requisição", example = "Ração Golden")
    private String nomeProduto;

    @NotNull
    @Schema(nullable = false, description = "Valor do produto", example = "100.00")
    private Double preco;

}
