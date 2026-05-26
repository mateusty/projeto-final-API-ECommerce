package org.serratec.Ecommerce.model;

import jakarta.mail.FetchProfile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class PedidoRequest {

    @NotNull(message = "O ID do cliente é obrigatório")
    private UUID clienteId;

    @NotNull
    private LocalDate data;

    @NotNull
    private StatusPedido status;

    @NotEmpty(message = "O pedido não pode estar vazio")
    @Valid
    private List<ItemPedidoRequest> itens;

}
