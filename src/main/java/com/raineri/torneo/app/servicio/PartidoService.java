/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Partido;
import com.raineri.torneo.app.repository.IPartido;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class PartidoService implements IPartidoServ {

    @Autowired
    private IPartido repo;

    @Override
    public List<Partido> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Partido> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public Partido guardar(Partido partido) {
        return repo.save(partido);
    }

    @Override
    public List<Partido> listarPorTorneo(Long id_torneo) {
        return repo.listarPartidosPorTorneo(id_torneo);
    }

}
