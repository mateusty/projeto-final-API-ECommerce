package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponse {

    private UUID idItemPedido;
    private UUID idProduto;
    private String nomeProduto;
    private Integer quantidade;
    private Double valorVenda;
    private Double desconto;
    private Double subTotal;
}
