package org.serratec.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.model.ProdutoRequest;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", nullable = false)
    private Subcategoria subcategoria;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private Double preco;

    public Produto(ProdutoRequest dto) {
        this.categoria = dto.getCategoria();
        this.subcategoria = dto.getSubcategoria();
        this.nomeProduto = dto.getNomeProduto();
        this.preco = dto.getPreco();
    }
}
