package org.serratec.Ecommerce.repository;

import org.serratec.Ecommerce.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    public Endereco findByCep(String cep);

    @Query("SELECT e FROM Cliente c JOIN c.enderecos e WHERE c.id = :clienteId")
    public List<Endereco> findByClienteId(@Param("clienteId") UUID clienteId);
}
