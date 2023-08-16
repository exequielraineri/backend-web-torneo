/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Inscripcion;
import com.raineri.torneo.app.repository.IInscripcion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class InscripcionService implements IInscipcionService {

    @Autowired
    private IInscripcion repo;

    @Override
    public List<Inscripcion> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Inscripcion> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public Inscripcion guardar(Inscripcion inscripcion) {
        return repo.save(inscripcion);
    }

}
