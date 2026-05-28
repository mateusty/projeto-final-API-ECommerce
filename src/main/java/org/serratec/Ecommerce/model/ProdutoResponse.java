package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Produto;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Classe model de PRODUTO responsável pela saída/respostas dos dados")

public class ProdutoResponse {

    @Schema(nullable = false, description = "Código identificador do produto", example = "6150098f-d90e-4ef1-800b-cb4e406618ee")
    private UUID id;

    @Schema(nullable = false, description = "Nome da categoria dos produtos", example = "Alimentação Pet")
    private String nomeCategoria;

    @Schema(nullable = false, description = "Nome da subcategoria dos produtos", example = "Cães")
    private String nomeSubcategoria;

    @Schema(nullable = false, description = "Nome do produto", example = "Ração Golden")
    private String nomeProduto;

    @Schema(nullable = false, description = "Preço dos produtos", example = "100.00")
    private Double preco;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nomeCategoria = produto.getCategoria().getNome();
        this.nomeSubcategoria = produto.getSubcategoria().getNome();
        this.nomeProduto = produto.getNomeProduto();
        this.preco = produto.getPreco();
    }
}
