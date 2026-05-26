package org.serratec.Ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Subcategoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaResponse {
    private UUID id;
    private String nome;
    private String descricao;
    private Categoria categoria;


    public SubcategoriaResponse(Subcategoria subcategoria){
        this.id =  subcategoria.getId();
        this.nome = subcategoria.getNome();
        this.descricao = subcategoria.getDescricao();
        this.categoria = subcategoria.getCategoria();
    }
}
