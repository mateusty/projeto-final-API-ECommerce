package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta com os dados de um item do pedido")
public class ItemPedidoResponse {

    @Schema(description = "Identificador único do item do pedido",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID idItemPedido;

    @Schema(description = "Identificador único do produto",
            example = "660e8400-e29b-41d4-a716-446655440001")
    private UUID idProduto;

    @Schema(description = "Nome do produto",
            example = "Ração Premium para Cães 10kg")
    private String nomeProduto;

    @Schema(description = "Quantidade do produto no pedido",
            example = "2",
            minimum = "1")
    private Integer quantidade;

    @Schema(description = "Valor unitário de venda do produto no momento do pedido",
            example = "89.90")
    private Double valorVenda;

    @Schema(description = "Valor do desconto aplicado ao item",
            example = "5.00")
    private Double desconto;

    @Schema(description = "Subtotal do item (quantidade * valorVenda - desconto)",
            example = "174.80")
    private Double subTotal;
}