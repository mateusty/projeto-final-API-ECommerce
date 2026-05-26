package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.entity.Endereco;
import org.serratec.Ecommerce.exception.InvalidDataException;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.EnderecoRequest;
import org.serratec.Ecommerce.model.EnderecoResponse;
import org.serratec.Ecommerce.model.ViaCepResponse;
import org.serratec.Ecommerce.repository.EnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
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

    // Funções auxiliares para o Service do cliente
    public EnderecoResponse buscarEndereco(String cep) {
        return new EnderecoResponse(this.enderecoRepository.findByCep(cep));
    }

    public boolean doesCepExists(String cep) {
        return this.enderecoRepository.findByCep(cep) != null;
    }

    public List<Endereco> listarEnderecosPorID(UUID id) {
        return this.enderecoRepository.findByClienteId(id);
    }
}
