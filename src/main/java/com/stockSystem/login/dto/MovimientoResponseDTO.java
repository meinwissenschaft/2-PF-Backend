package com.stockSystem.login.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovimientoResponseDTO {

    private Long productoId;

    private String producto;

    private Integer cantidad;

    private LocalDate fecha;

    private String usuario;
}
