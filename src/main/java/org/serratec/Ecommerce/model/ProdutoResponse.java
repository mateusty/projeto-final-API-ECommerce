package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Produto;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoResponse {

    private UUID id;
    private String nomeCategoria;
    private String nomeSubcategoria;
    private String nomeProduto;
    private Double preco;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nomeCategoria = produto.getCategoria().getNome();
        this.nomeSubcategoria = produto.getSubcategoria().getNome();
        this.nomeProduto = produto.getNomeProduto();
        this.preco = produto.getPreco();
    }
}
