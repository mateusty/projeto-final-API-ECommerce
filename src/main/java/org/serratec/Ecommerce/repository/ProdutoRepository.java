package org.serratec.Ecommerce.repository;

import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.Produto;
import org.serratec.Ecommerce.entity.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    boolean existsByNomeProduto(String nomeProduto);
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);
    List<Produto> findByCategoria(Categoria categoria);
    List<Produto> findBySubcategoria(Subcategoria subcategoria);
}
