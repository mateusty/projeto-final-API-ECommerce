package org.serratec.Ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.StatusCategoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    private UUID id;
    private String nome;
    private String descricao;
    private StatusCategoria status;


    public CategoriaResponse (Categoria categoria) {

        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
        this.status = categoria.getStatusCategoria();
    }

}

