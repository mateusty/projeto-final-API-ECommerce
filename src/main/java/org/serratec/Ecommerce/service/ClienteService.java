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
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final MailService mailService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService, MailService mailService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
        this.mailService = mailService;
    }

    public ClienteResponse buscarPorId(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O Cliente com o ID: " + id + " não foi encontrado."));
        cliente.setEnderecos(this.enderecoService.listarEnderecosPorID(cliente.getId()));
        return new ClienteResponse(cliente);
    }


    public Cliente inserirCliente(ClienteRequest cliente) {
        List<Endereco> enderecos = new ArrayList<>();
        cliente.getEnderecos().forEach(endereco -> {
            enderecos.add(this.enderecoService.buscarViaCep(endereco));
        });

        this.mailService.enviarMensagem(cliente.getEmail(), "Criação de conta", "Olá, " +
                cliente.getNome() +
                " uma conta no PetShop foi criada em seu email " + cliente.getEmail());

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

        this.mailService.enviarMensagem(clienteDB.getEmail(), "Alteração de dados", "Olá, " + clienteDB.getNome() + ", sua conta com o email, " + clienteDB.getEmail() + " teve seus dados alterados.");

        this.clienteRepository.save(clienteDB);
        return clienteDB;
    }

    public List<ClienteResponse> buscarCliente(String cpf, String email) {
        boolean temCpf = cpf != null && !cpf.isBlank();
        boolean temEmail = email != null && !email.isBlank();
        List<Cliente> clientes = new ArrayList<>();

        if(temCpf) {
            clientes.add(this.clienteRepository.findByCpf(cpf));
        }
        if(temEmail && clientes.isEmpty()) {
            clientes.add(this.clienteRepository.findByEmailLikeIgnoreCase(email));
        }

        if(!(temCpf || temEmail)) {
            clientes.addAll(this.clienteRepository.findAll());
        }

        if(clientes.isEmpty()) {
            throw new NotFoundException("Não existe um cliente com o cpf: " + cpf + ", Ou com o email: " + email);
        }
        clientes.forEach(cliente -> {
            cliente.setEnderecos(this.enderecoService.listarEnderecosPorID(cliente.getId()));
        });

        return clientes.stream().map(ClienteResponse::new).toList();
    }

    public void deletarCliente(UUID id) {
        if(!this.clienteRepository.existsById(id)) {
            throw new NotFoundException("Não existe um cliente com o id: " + id);
        }

        this.clienteRepository.deleteById(id);
    }
}