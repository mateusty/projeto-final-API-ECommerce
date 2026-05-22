package org.serratec.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private LocalDateTime dateTime;

    public ErrorResponse(String error) {
        this.error = error;
        this.dateTime = LocalDateTime.now();
    }
}
