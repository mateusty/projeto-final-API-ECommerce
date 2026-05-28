package org.serratec.Ecommerce.service;

import jakarta.transaction.Transactional;
import org.serratec.Ecommerce.entity.Endereco;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.*;
import org.serratec.Ecommerce.repository.EnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final RestClient restClient;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.restClient = RestClient.builder().baseUrl("https://viacep.com.br").build();
    }

    public Endereco buscarViaCep(EnderecoRequest enderecoRequest) {
        try {
            ResponseEntity<ViaCepResponse> viaCepResponse = this.restClient
                    .get()
                    .uri("/ws/" + enderecoRequest.getCep() + "/json")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(ViaCepResponse.class);
            if(viaCepResponse.getBody() == null) {
                throw new NotFoundException("Não foi encontrado o cep informado");
            }
            Endereco endereco = new Endereco(enderecoRequest, viaCepResponse.getBody());
            return endereco;
        } catch(HttpClientErrorException ex) {
            if(ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new InvalidDataException("O cep informado é inválido");
            }
            throw ex;
        }
    }

    public EnderecoResponse atualizarEndereco(EnderecoUpdateRequest endereco, UUID id) {
        boolean temCep = endereco.getCep() != null && !endereco.getCep().isBlank();
        boolean temComplemento = endereco.getComplemento() != null && !endereco.getComplemento().isBlank();

        List<Endereco> enderecos = new ArrayList<>();

        Endereco enderecoDB = this.enderecoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não há um cliente com o id: " + id));
        if(temCep) {
            enderecoDB.setCep(endereco.getCep());
        }
        if(temComplemento) {
            enderecoDB.setComplemento(endereco.getComplemento());
        }

        this.enderecoRepository.save(enderecoDB);
        return new EnderecoResponse(enderecoDB);
    }

    @Transactional
    public void deletarEndereco(UUID id) {
        Endereco endereco = this.enderecoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um endereco com o id: " + id));
        endereco.getClientes().forEach(cliente -> cliente.getEnderecos().remove(endereco));

        this.enderecoRepository.delete(endereco);
    }

    // Funções auxiliares para o Service do cliente
    public List<EnderecoResponse> buscarEndereco(String cep) {
        if(!(cep.isBlank())) {
            return List.of(new EnderecoResponse(this.enderecoRepository.findByCep(cep)));
        }
        return this.enderecoRepository.findAll().stream().map(EnderecoResponse::new).toList();
    }

    public boolean doesCepExists(String cep) {
        return this.enderecoRepository.findByCep(cep) != null;
    }

    public List<Endereco> listarEnderecosPorID(UUID id) {
        return this.enderecoRepository.findByClienteId(id);
    }
}
