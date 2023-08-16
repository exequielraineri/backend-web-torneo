/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.SetPartido;
import com.raineri.torneo.app.repository.ISetPartido;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */

@Service
public class SetPartidoServ implements ISetPartidoServ{

    @Autowired
    private ISetPartido repo;

    @Override
    public List<SetPartido> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<SetPartido> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public SetPartido guardar(SetPartido setPartido) {
        return repo.save(setPartido);
    }
    
}
