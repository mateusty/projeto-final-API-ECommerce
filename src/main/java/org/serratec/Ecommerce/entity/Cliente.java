package org.serratec.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.model.ClienteRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable = false)
    private String nome;

    @Column
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="cliente_endereco",
            joinColumns = @JoinColumn(name="id_cliente"),
            inverseJoinColumns = @JoinColumn(name="id_endereco")
    )
    private List<Endereco> enderecos;

    public Cliente(ClienteRequest cliente, List<Endereco> enderecos) {
        this.enderecos = enderecos;
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
    }
}
