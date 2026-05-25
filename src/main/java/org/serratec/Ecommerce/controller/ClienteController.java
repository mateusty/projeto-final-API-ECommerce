package org.serratec.Ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.model.ClienteRequest;
import org.serratec.Ecommerce.model.ClienteResponse;
import org.serratec.Ecommerce.model.ClienteUpdateRequest;
import org.serratec.Ecommerce.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody ClienteRequest cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body((this.clienteService.inserirCliente(cliente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@Valid @RequestBody ClienteUpdateRequest cliente,
                                                            @PathVariable UUID id) {
        return ResponseEntity.ok(this.clienteService.atualizarCliente(cliente, id));
    }
}
