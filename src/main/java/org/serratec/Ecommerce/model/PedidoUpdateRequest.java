package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.enums.StatusPedido;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para atualização do status do pedido")
public class PedidoUpdateRequest {

    @NotNull(message = "O status do pedido é obrigatório")
    @Schema(description = "Novo status do pedido",
            example = "ENTREGUE",
            requiredMode = Schema.RequiredMode.REQUIRED,
            allowableValues = {"PENDENTE", "PAGO", "SEPARANDO", "ENVIADO", "ENTREGUE", "CANCELADO"})
    private StatusPedido status;
}