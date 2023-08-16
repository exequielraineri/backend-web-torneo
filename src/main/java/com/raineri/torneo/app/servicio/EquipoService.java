/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Equipo;
import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.repository.IEquipo;
import com.raineri.torneo.app.repository.IJugador;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
public class EquipoService implements IEquipoService {

    @Autowired
    private IEquipo repo;

    @Autowired
    private IJugador jugador_repo;

    @Override
    public Equipo guardar(Equipo equipo) {
        return repo.save(equipo);
    }

    @Override
    public List<Equipo> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Equipo> obtener(Long id) {
        return repo.findById(id);
    }

    

}
