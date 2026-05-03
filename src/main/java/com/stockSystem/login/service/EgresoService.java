package com.stockSystem.login.service;

import com.stockSystem.login.dto.MovimientoRequestDTO;
import com.stockSystem.login.entity.Egreso;
import com.stockSystem.login.entity.Producto;
import com.stockSystem.login.entity.Stock;
import com.stockSystem.login.repository.EgresoRepository;
import com.stockSystem.login.repository.ProductoRepository;
import com.stockSystem.login.repository.StockRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional

public class EgresoService {

    private final EgresoRepository egresoRepository;
    private final ProductoRepository productoRepository;
    private final StockRepository stockRepository;

    public Egreso crearEgreso(MovimientoRequestDTO dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Stock stock = producto.getStock();

        // 🔥 Validación crítica
        if (stock.getCantidad() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        // 🔥 Descontar stock
        stock.setCantidad(stock.getCantidad() - dto.getCantidad());

        stockRepository.save(stock);

        // 🔥 Registrar movimiento
        Egreso egreso = new Egreso();

        egreso.setProducto(producto);
        egreso.setCantidad(dto.getCantidad());
        egreso.setFechaEgreso(LocalDate.now());

        return egresoRepository.save(egreso);
    }
}
