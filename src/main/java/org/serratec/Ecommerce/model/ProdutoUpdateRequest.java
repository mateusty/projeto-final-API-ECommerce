package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoUpdateRequest {

    private Categoria categoria;
    private Subcategoria subcategoria;
    private String nomeProduto;
    private Double preco;

}
