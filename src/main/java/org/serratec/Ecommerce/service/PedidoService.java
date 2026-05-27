package org.serratec.Ecommerce.service;

import jakarta.transaction.Transactional;
import org.serratec.Ecommerce.entity.Cliente;
import org.serratec.Ecommerce.entity.ItemPedido;
import org.serratec.Ecommerce.entity.Pedido;
import org.serratec.Ecommerce.entity.Produto;
import org.serratec.Ecommerce.enums.StatusPedido;
import org.serratec.Ecommerce.exception.NotFoundException;
import org.serratec.Ecommerce.model.*;
import org.serratec.Ecommerce.repository.ClienteRepository;
import org.serratec.Ecommerce.repository.PedidoRepository;
import org.serratec.Ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public PedidoResponse inserirPedido(PedidoRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado com o ID: " + request.getClienteId()));

        Pedido pedido = new Pedido();
        pedido.setData(request.getData());
        pedido.setStatus(request.getStatus());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedidoEntities = new ArrayList<>();

        for (ItemPedidoRequest itemReq : request.getItens()) {

            Produto produto = produtoRepository.findById(itemReq.getProdutoId())
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado com ID: " + itemReq.getProdutoId()));


            ItemPedido itemPedidoEntity = new ItemPedido();
            itemPedidoEntity.setProduto(produto);
            itemPedidoEntity.setQuantidade(itemReq.getQuantidade());
            itemPedidoEntity.setDesconto(itemReq.getDesconto() != null ? itemReq.getDesconto() : 0.0);
            itemPedidoEntity.setValorVenda(produto.getPreco());
            itemPedidoEntity.setPedido(pedido);

            itensPedidoEntities.add(itemPedidoEntity);
        }

        pedido.setItens(itensPedidoEntities);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return transformarEntidadeParaResponse(pedidoSalvo);
    }

    @Transactional
    public PedidoResponse atualizarPedido(Long id, PedidoUpdateRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com o id " + id));

        pedido.setStatus(request.getStatus());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return transformarEntidadeParaResponse(pedidoAtualizado);
    }

    public PedidoResponse buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com o ID: " + id));

        return transformarEntidadeParaResponse(pedido);
    }

    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::transformarEntidadeParaResponse)
                .toList();
    }

// Mét'odo auxiliar para converter entidade pedido em pedidoresponse
    private PedidoResponse transformarEntidadeParaResponse(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());
        response.setData(pedido.getData());
        response.setStatus((pedido.getStatus()));
        response.setIdCliente(pedido.getCliente().getId());
        response.setNomeCliente(pedido.getCliente().getNome());

        double totalGeral = 0.0;
        List<ItemPedidoResponse> itensResponse = new ArrayList<>();

        for (ItemPedido item : pedido.getItens()) {
            ItemPedidoResponse itemResponse = new ItemPedidoResponse();
            itemResponse.setIdItemPedido(item.getId());
            itemResponse.setIdProduto(item.getProduto().getId());
            itemResponse.setNomeProduto(item.getProduto().getNomeProduto());
            itemResponse.setQuantidade(item.getQuantidade());
            itemResponse.setValorVenda(item.getValorVenda());
            itemResponse.setDesconto(item.getDesconto());

            double subtotal = (item.getValorVenda() * item.getQuantidade()) - item.getDesconto();
            itemResponse.setSubTotal(subtotal);

            itensResponse.add(itemResponse);
            totalGeral += subtotal;
        }

        response.setItens(itensResponse);
        response.setValorTotal(totalGeral);

        return response;
    }
}
