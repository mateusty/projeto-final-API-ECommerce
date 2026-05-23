package org.serratec.Ecommerce.model;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        String estado
) {
}
