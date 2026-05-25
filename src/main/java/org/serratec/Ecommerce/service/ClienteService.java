package org.serratec.Ecommerce.service;

import org.hibernate.dialect.function.array.ArrayArgumentValidator;
import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.entity.Endereco;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.*;
import org.serratec.Ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    public Cliente inserirCliente(ClienteRequest cliente) {
        List<Endereco> enderecos = new ArrayList<>();
        cliente.getEnderecos().forEach(endereco -> {
            enderecos.add(this.enderecoService.buscarViaCep(endereco));
        });
        return this.clienteRepository.save(new Cliente(cliente, enderecos));
    }

    public Cliente atualizarCliente(ClienteUpdateRequest cliente, UUID id) {
        boolean temNome = cliente.getNome() != null && !cliente.getNome().isBlank();
        boolean temCpf = cliente.getCpf() != null && !cliente.getCpf().isBlank();
        boolean temEmail = cliente.getEmail() != null && !cliente.getEmail().isBlank();
        boolean temTelefone = cliente.getTelefone() != null && !cliente.getTelefone().isBlank();
        boolean temEndereco = cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty();

        List<Endereco> enderecos = new ArrayList<>();

        Cliente clienteDB = this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Não há um cliente com o id: " + id));
        if(temNome) {
            clienteDB.setNome(cliente.getNome());
        }
        if(temCpf) {
            clienteDB.setCpf(cliente.getCpf());
        }
        if(temEmail) {
            clienteDB.setEmail(cliente.getEmail());
        }
        if(temTelefone) {
            clienteDB.setTelefone(cliente.getTelefone());
        }
        if(temEndereco) {
            cliente.getEnderecos().forEach(endereco -> {
               if(this.enderecoService.doesCepExists(endereco.getCep())) {
                   enderecos.add(new Endereco(this.enderecoService.buscarEndereco(endereco.getCep())));
               }
               else {
                   enderecos.add(this.enderecoService.buscarViaCep(new EnderecoRequest(endereco)));
               }
            });
            clienteDB.setEnderecos(enderecos);
        }
        this.clienteRepository.save(clienteDB);
        return clienteDB;
    }

    public ClienteResponse buscarCliente(String cpf, String email) {
        boolean temCpf = cpf != null && !cpf.isBlank();
        boolean temEmail = email != null && !email.isBlank();
        Cliente cliente = null;

        if(temCpf) {
            cliente = this.clienteRepository.findByCpf(cpf);
        }
        if(temEmail && cliente == null) {
            cliente = this.clienteRepository.findByEmailLikeIgnoreCase(email);
        }

        if(cliente == null) {
            throw new NotFoundException("Não existe um cliente com o cpf: " + cpf + ", Ou com o email: " + email);
        }
        cliente.setEnderecos(this.enderecoService.listarEnderecosPorID(cliente.getId()));

        return new ClienteResponse(cliente);
    }
}