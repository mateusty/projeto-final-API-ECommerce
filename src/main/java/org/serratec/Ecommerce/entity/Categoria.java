package org.serratec.Ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank (message = " A categoria precisa de um nome ")
    private String nome;
    
    @Size(max = 200 , message = " A descrição deve ter no maximo 200 caracteres")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusCategoria statusCategoria;









}
