package com.stockSystem.login.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovimientoRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull(
            message = "La cantidad es obligatoria"
    )

    @Min(
            value = 1,
            message = "La cantidad debe ser mayor a 0"
    )

    private Integer cantidad;
}
