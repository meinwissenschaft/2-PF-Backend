package com.stockSystem.login.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductoResponseDTO {

    private Long codProd;

    private String nombre;

    private String descripcion;

    private Integer stock;

    private String categoria;
}
