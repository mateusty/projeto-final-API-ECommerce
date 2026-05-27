package org.serratec.Ecommerce.model;

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
public class PedidoResponse {
    private Long id;
    private LocalDate data;
    private StatusPedido status;
    private UUID idCliente;
    private String nomeCliente;
    private List<ItemPedidoResponse> itens;
    private Double valorTotal;
}
