
package org.serratec.Ecommerce.repository;

import org.serratec.Ecommerce.entity.Categoria;
import org.serratec.Ecommerce.entity.StatusCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    boolean  existsByNomeIgnoreCase (String nome);

    Optional<Categoria> findByNomeIgnoreCase (String nome);

    List<Categoria>  findByStatusCategoria(StatusCategoria status);



}
