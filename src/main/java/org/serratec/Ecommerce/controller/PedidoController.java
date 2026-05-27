package org.serratec.Ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.Ecommerce.entity.Pedido;
import org.serratec.Ecommerce.model.PedidoRequest;
import org.serratec.Ecommerce.model.PedidoResponse;
import org.serratec.Ecommerce.model.PedidoUpdateRequest;
import org.serratec.Ecommerce.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping()
    public ResponseEntity<List<PedidoResponse>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    @PostMapping()
    public ResponseEntity<PedidoResponse> inserirPedido (@Valid @RequestBody PedidoRequest pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.inserirPedido(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(@Valid @RequestBody PedidoUpdateRequest pedido, @PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.atualizarPedido(id, pedido));
    }
}
