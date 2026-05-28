package org.serratec.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.Ecommerce.entity.Endereco;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta com os dados completos do endereço, incluindo informações enriquecidas pelo ViaCEP")
public class EnderecoResponse {

    @Schema(description = "ID único do endereco", example = "550e8400-e29b-41d4-a716-446655440000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "CEP do endereço (8 dígitos)",
            example = "01001000",
            pattern = "^\\d{8}$")
    private String cep;

    @Schema(description = "Nome da rua/avenida (preenchido automaticamente pelo ViaCEP)",
            example = "Praça da Sé")
    private String logradouro;

    @Schema(description = "Informações complementares (número, apartamento, referência)",
            example = "Apto 123 Bloco B")
    private String complemento;

    @Schema(description = "Bairro do endereço",
            example = "Sé")
    private String bairro;

    @Schema(description = "Cidade do endereço",
            example = "São Paulo")
    private String localidade;

    @Schema(description = "Estado - sigla de 2 letras",
            example = "SP",
            pattern = "^[A-Z]{2}$")
    private String uf;

    public EnderecoResponse(Endereco endereco) {
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
    }
}