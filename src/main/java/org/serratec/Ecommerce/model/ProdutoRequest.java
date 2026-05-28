package org.serratec.Ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Subcategoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoRequest {

    @NotNull
    private UUID categoriaId;

    @NotNull
    private UUID subcategoriaId;

    @NotBlank
    private String nomeProduto;

    @NotNull
    private Double preco;

}
