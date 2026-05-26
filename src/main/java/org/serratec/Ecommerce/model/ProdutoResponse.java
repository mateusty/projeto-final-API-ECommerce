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

    //necessário alterar idcategoria e subcategoria, estou apenas montando a estrutura inicial antes de juntar tudo

    private UUID id;
    private String idCategoria;
    private String idSubCategoria;
    private String nomeProduto;
    private Double preco;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.idCategoria = produto.getIdCategoria();
        this.idSubCategoria = produto.getIdSubCategoria();
        this.nomeProduto = produto.getNomeProduto();
        this.preco = produto.getPreco();
    }
}
