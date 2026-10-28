
package com.example.demo.service;

import com.example.demo.model.Medicamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentoService {

    private final List<Medicamento> medicamentos = new ArrayList<>();

    private Long siguienteId = 1L;

    public List<Medicamento> listar() {
        return medicamentos;
    }

    public Medicamento buscarPorId(Long id) {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getId().equals(id)) {
                return medicamento;
            }
        }

        return null;
    }

    public Medicamento guardar(Medicamento medicamento) {
        medicamento.setId(siguienteId++);
        medicamentos.add(medicamento);
        return medicamento;
    }

    public Medicamento actualizar(Medicamento medicamento) {
        Medicamento existente = buscarPorId(medicamento.getId());

        if (existente != null) {
            existente.setNombre(medicamento.getNombre());
            existente.setDescripcion(medicamento.getDescripcion());
            existente.setPrecio(medicamento.getPrecio());
            existente.setCantidad(medicamento.getCantidad());
        }

        return existente;
    }

    public void eliminar(Long id) {
        medicamentos.removeIf(medicamento ->
                medicamento.getId().equals(id));
    }
}

