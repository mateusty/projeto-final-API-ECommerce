package org.serratec.Ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//necessário alterar idcategoria e subcategoria, estou apenas montando a estrutura inicial antes de juntar tudo

public class ProdutoRequest {

    @NotBlank
    private String idCategoria;

    @NotBlank
    private String idSubCategoria;

    @NotBlank
    private String nomeProduto;

    @NotNull
    private Double preco;

}
