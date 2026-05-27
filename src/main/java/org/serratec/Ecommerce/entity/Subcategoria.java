package org.serratec.Ecommerce.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.model.SubcategoriaRequest;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subcategoria")

public class Subcategoria {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = " A categoria precisa de um nome ")
    private String nome;

    @Size(max = 200 , message = " A descrição deve ter no maximo 200 caracteres")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


    public Subcategoria (SubcategoriaRequest request, Categoria categoria){
         this.nome = request.getNome();
         this.descricao = request.getDescricao();
         this.categoria = categoria;
    }

}
