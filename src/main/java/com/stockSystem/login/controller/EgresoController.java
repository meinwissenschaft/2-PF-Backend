package com.stockSystem.login.controller;

import com.stockSystem.login.dto.MovimientoRequestDTO;
import com.stockSystem.login.entity.Egreso;
import com.stockSystem.login.service.EgresoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/egresos")
public class EgresoController {

    @Autowired
    private EgresoService egresoService;

    @PostMapping
    public ResponseEntity<Egreso> crearEgreso(
            @Valid @RequestBody MovimientoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                egresoService.crearEgreso(dto)
        );
    }
}