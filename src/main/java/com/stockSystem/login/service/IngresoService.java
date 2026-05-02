package com.stockSystem.login.service;


import com.stockSystem.login.dto.MovimientoRequestDTO;
import com.stockSystem.login.entity.Ingreso;
import com.stockSystem.login.entity.Producto;
import com.stockSystem.login.entity.Stock;
import com.stockSystem.login.repository.IngresoRepository;
import com.stockSystem.login.repository.ProductoRepository;
import com.stockSystem.login.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IngresoService {
   @Autowired
    private IngresoRepository ingresoRepository;

   @Autowired
    private ProductoRepository productoRepository;

   @Autowired
    private StockRepository stockRepository;

   public Ingreso crearIngreso(MovimientoRequestDTO dto) {

       Producto producto = productoRepository.findById(dto.getProductoId())
               .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

       Stock stock = producto.getStock();

       //Logica de negocio:
       stock.setCantidad(stock.getCantidad() + dto.getCantidad());

       stockRepository.save(stock);

       Ingreso ingreso = new Ingreso();
       ingreso.setProducto(producto);
       ingreso.setCantidad(dto.getCantidad());
       ingreso.setFechaIngreso(LocalDate.now());

       return ingresoRepository.save(ingreso);
   }
}
