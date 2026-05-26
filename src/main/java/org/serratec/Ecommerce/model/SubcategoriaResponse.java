package org.serratec.Ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Categoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaResponse {
    private UUID id;
    private String nome;
    private String descricao;
    private Categoria categoria;
}
