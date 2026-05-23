package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.model.ClienteRequest;
import org.serratec.Ecommerce.model.ClienteResponse;
import org.serratec.Ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

//    public ClienteResponse inserirCliente(ClienteRequest cliente) {
//    }
}
