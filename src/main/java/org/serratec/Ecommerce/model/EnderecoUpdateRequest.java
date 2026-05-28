package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para atualização de endereço - CEP obrigatório, complemento opcional")
public class EnderecoUpdateRequest {

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos (sem traço)")
    @Schema(description = "CEP do endereço (8 dígitos numéricos, sem traço)",
            example = "01001000",
            pattern = "^\\d{8}$",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String cep;

    @Schema(description = "Informações complementares (número, apartamento, referência)",
            example = "Apto 123 Bloco B",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String complemento;
}