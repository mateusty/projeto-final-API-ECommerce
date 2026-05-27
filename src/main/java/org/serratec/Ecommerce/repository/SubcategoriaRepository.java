package org.serratec.Ecommerce.repository;


import org.serratec.Ecommerce.entity.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, UUID> {

    boolean  existsByNome (String nome);

    Optional<Subcategoria> findByNome (String nome);

    List<Subcategoria> findByCategoriaId ( UUID categoriaId);

    boolean existsByCategoriaId(UUID categoriaId);

}
