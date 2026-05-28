package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Padrão de resposta para erros da API")
public class ErrorResponse {

    @Schema(description = "Mensagem descritiva do erro ocorrido",
            example = "Cliente não encontrado com o ID informado")
    private String error;

    @Schema(description = "Data e hora do erro (formato ISO)",
            example = "2024-01-15T10:30:00")
    private LocalDateTime dateTime;

    public ErrorResponse(String error) {
        this.error = error;
        this.dateTime = LocalDateTime.now();
    }
}