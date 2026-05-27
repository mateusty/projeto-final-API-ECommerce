package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Produto;
import org.serratec.Ecommerce.entity.Subcategoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoResponse {

    private UUID id;
    private Categoria categoria;
    private Subcategoria subcategoria;
    private String nomeProduto;
    private Double preco;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.categoria = produto.getCategoria();
        this.subcategoria = produto.getSubcategoria();
        this.nomeProduto = produto.getNomeProduto();
        this.preco = produto.getPreco();
    }
}
