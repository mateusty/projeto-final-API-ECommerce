package org.serratec.Ecommerce.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.enums.StatusPedido;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoUpdateRequest {

    @NotNull
    private StatusPedido status;

}
