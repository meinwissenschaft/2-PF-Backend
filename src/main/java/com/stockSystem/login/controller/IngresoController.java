package com.stockSystem.login.controller;

import com.stockSystem.login.dto.MovimientoRequestDTO;
import com.stockSystem.login.entity.Ingreso;
import com.stockSystem.login.service.IngresoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingresos")
    public class IngresoController {

        @Autowired
        private IngresoService ingresoService;

        @PostMapping
        public ResponseEntity<Ingreso> crearIngreso(
            @Valid @RequestBody MovimientoRequestDTO dto
        ) {

            return ResponseEntity.ok(
                ingresoService.crearIngreso(dto)
            );
        }
    }

