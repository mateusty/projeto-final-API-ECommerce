package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.entity.Endereco;
import org.serratec.Ecommerce.model.ClienteRequest;
import org.serratec.Ecommerce.model.ClienteResponse;
import org.serratec.Ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    public ClienteResponse inserirCliente(ClienteRequest cliente) {
        List<Endereco> enderecos = new ArrayList<>();
        cliente.getEnderecos().forEach(endereco -> {
            enderecos.add(this.enderecoService.inserirEndereco(endereco));
        });
        this.clienteRepository.save(new Cliente(cliente));
        return new ClienteResponse(cliente, enderecos);
    }
}
