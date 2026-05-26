package org.serratec.Ecommerce.repository;


import org.serratec.Ecommerce.entity.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, UUID> {
}
