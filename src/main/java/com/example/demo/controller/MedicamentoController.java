
package com.example.demo.controller;

import com.example.demo.model.Medicamento;
import com.example.demo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoService.listar();
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(
            @PathVariable Long id) {

        Medicamento medicamento = medicamentoService.buscarPorId(id);

        if (medicamento != null) {
            return new ResponseEntity<>(medicamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Medicamento> registrarMedicamento(
            @RequestBody Medicamento medicamento) {

        Medicamento nuevoMedicamento =
                medicamentoService.guardar(medicamento);

        return new ResponseEntity<>(
                nuevoMedicamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizarMedicamento(
            @PathVariable Long id,
            @RequestBody Medicamento medicamento) {

        Medicamento existente = medicamentoService.buscarPorId(id);

        if (existente != null) {
            medicamento.setId(id);

            Medicamento actualizado =
                    medicamentoService.actualizar(medicamento);

            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedicamento(
            @PathVariable Long id) {

        Medicamento existente = medicamentoService.buscarPorId(id);

        if (existente != null) {
            medicamentoService.eliminar(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
