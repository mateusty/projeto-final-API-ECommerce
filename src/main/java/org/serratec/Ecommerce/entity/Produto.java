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

//necessário alterar idcategoria e subcategoria, estou apenas montando a estrutura inicial antes de juntar tudo

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String idCategoria;

    @Column(nullable = false)
    private String idSubCategoria;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private Double preco;

    public Produto(ProdutoRequest dto) {
        this.idCategoria = dto.getIdCategoria();
        this.idSubCategoria = dto.getIdSubCategoria();
        this.nomeProduto = dto.getNomeProduto();
        this.preco = dto.getPreco();
    }
}
