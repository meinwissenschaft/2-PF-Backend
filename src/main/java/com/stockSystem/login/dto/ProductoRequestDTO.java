package com.stockSystem.login.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductoRequestDTO {

    private String nombre;

    private String descripcion;

    private Integer cantidadInicial;

    private Long categoriaId;
}