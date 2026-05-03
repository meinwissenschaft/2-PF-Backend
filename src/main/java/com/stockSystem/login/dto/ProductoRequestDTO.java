package com.stockSystem.login.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La cantidad inicial es obligatoria")

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidadInicial;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;
}