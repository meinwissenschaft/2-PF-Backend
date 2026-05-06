package com.stockSystem.login.controller;

import com.stockSystem.login.dto.ProductoRequestDTO;
import com.stockSystem.login.dto.ProductoResponseDTO;
import com.stockSystem.login.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                productoService.crearProducto(dto)
        );
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")

    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductos() {

        return ResponseEntity.ok(
                productoService.obtenerProductos()
        );
    }
}
