package org.serratec.Ecommerce.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequest {

    @NotBlank(message = " O nome não pode estar vazio")
    @Size( min = 2 , max = 50)
    private String nome;

    @Size( max = 200,message = " Descreva em até 200 caracteres")
    private String descricao;
}