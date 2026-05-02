package com.stockSystem.login.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovimientoRequestDTO {

    private Long productoId;

    private Integer cantidad;
}
