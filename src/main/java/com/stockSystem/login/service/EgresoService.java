package com.stockSystem.login.service;

import com.stockSystem.login.dto.MovimientoRequestDTO;
import com.stockSystem.login.entity.Egreso;
import com.stockSystem.login.entity.Producto;
import com.stockSystem.login.entity.Stock;
import com.stockSystem.login.repository.EgresoRepository;
import com.stockSystem.login.repository.ProductoRepository;
import com.stockSystem.login.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EgresoService {
    @Autowired
    private EgresoRepository egresoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockRepository stockRepository;

    public Egreso crearEgreso(MovimientoRequestDTO dto) {

        Producto producto = productoRepository.findById(dto.productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Stock stock = producto.getStock();

        // 🔥 VALIDACIÓN CRÍTICA
        if (stock.getCantidad() < dto.cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        stock.setCantidad(stock.getCantidad() - dto.cantidad);

        stockRepository.save(stock);

        Egreso egreso = new Egreso();
        egreso.setProducto(producto);
        egreso.setCantidad(dto.cantidad);
        egreso.setFechaEgreso(LocalDate.now());

        return egresoRepository.save(egreso);
    }
}
