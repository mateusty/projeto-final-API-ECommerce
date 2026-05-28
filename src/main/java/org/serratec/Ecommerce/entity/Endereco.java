package org.serratec.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.model.EnderecoRequest;
import org.serratec.Ecommerce.model.EnderecoResponse;
import org.serratec.Ecommerce.model.ViaCepResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String cep;

    @Column
    private String logradouro;

    @Column
    private String bairro;

    @Column
    private String localidade;

    @Column
    private String uf;

    @Column
    private String complemento;

    @ManyToMany(mappedBy = "enderecos")
    private List<Cliente> clientes = new ArrayList<>();

    public Endereco(EnderecoRequest enderecoRequest, ViaCepResponse viaCepResponse) {
        this.cep = enderecoRequest.getCep();
        this.complemento = enderecoRequest.getComplemento();
        this.logradouro = viaCepResponse.logradouro();
        this.bairro = viaCepResponse.bairro();
        this.localidade = viaCepResponse.localidade();
        this.uf = viaCepResponse.uf();
    }

    public Endereco(EnderecoResponse endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
    }
}
