package com.stockSystem.login.service;

import com.stockSystem.login.dto.ProductoRequestDTO;
import com.stockSystem.login.dto.ProductoResponseDTO;
import com.stockSystem.login.entity.*;
import com.stockSystem.login.mapper.ProductoMapper;
import com.stockSystem.login.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final StockRepository stockRepository;
    private final ProductoMapper productoMapper;

    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Stock stock = new Stock();
        stock.setCantidad(dto.getCantidadInicial());

        stockRepository.save(stock);

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria);
        producto.setStock(stock);

        Producto productoGuardado = productoRepository.save(producto);

        return productoMapper.toDTO(productoGuardado);
    }
}
