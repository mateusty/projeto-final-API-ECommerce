package org.serratec.Ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Subcategoria;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoRequest {

    @NotNull
    private Categoria categoria;

    @NotNull
    private Subcategoria subcategoria;

    @NotBlank
    private String nomeProduto;

    @NotNull
    private Double preco;

}
