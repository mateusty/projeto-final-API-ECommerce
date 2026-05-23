package org.serratec.Ecommerce.repository;

import org.serratec.Ecommerce.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    public Endereco findByCep(String cep);
}
