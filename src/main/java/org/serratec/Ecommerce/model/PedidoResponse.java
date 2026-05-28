package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.enums.StatusPedido;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta com os dados completos de um pedido realizado no ecommerce")
public class PedidoResponse {

    @Schema(description = "Número do pedido (sequencial)",
            example = "12345")
    private Long id;

    @Schema(description = "Data em que o pedido foi realizado",
            example = "2024-01-15")
    private LocalDate data;

    @Schema(description = "Status atual do pedido",
            example = "PAGO",
            allowableValues = {"PENDENTE", "PAGO", "SEPARANDO", "ENVIADO", "ENTREGUE", "CANCELADO"})
    private StatusPedido status;

    @Schema(description = "Identificador único do cliente que realizou o pedido",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID idCliente;

    @Schema(description = "Nome do cliente que realizou o pedido",
            example = "João Silva")
    private String nomeCliente;

    @Schema(description = "Lista de itens do pedido")
    private List<ItemPedidoResponse> itens;

    @Schema(description = "Valor total do pedido (soma dos subtotais dos itens)",
            example = "299.90")
    private Double valorTotal;
}