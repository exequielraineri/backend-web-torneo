/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Torneo;
import com.raineri.torneo.app.repository.ITorneo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class TorneoService implements ITorneoService{

    @Autowired
    private ITorneo repo;
    
    @Override
    public Torneo guardar(Torneo torneo) {
        return repo.save(torneo);
    }

    @Override
    public List<Torneo> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Torneo> obtener(Long id) {
        return repo.findById(id);
    }
}
