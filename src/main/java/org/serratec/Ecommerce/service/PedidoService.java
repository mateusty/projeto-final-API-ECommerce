package org.serratec.Ecommerce.service;

import org.serratec.Ecommerce.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


}
