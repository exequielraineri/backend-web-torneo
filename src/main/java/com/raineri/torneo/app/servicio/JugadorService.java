/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.repository.IJugador;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class JugadorService implements IJugadorService {

    @Autowired
    private IJugador repo;

    @Override
    public List<Jugador> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Jugador> obtener(Long id) {
        return repo.findById(id);
    }

    @Override
    public Jugador guardar(Jugador jugador) {
        return repo.save(jugador);
    }

    @Override
    public Optional<Jugador> obtenerPorUsuario(String usuario) {
        return repo.obtenerPorUsuario(usuario);
    }

    @Override
    public Optional<Jugador> obtenerPorDNI(String dni) {
        return repo.obtenerPorDNI(dni);
    }

    @Override
    public List<Jugador> listarRanking() {
        return repo.listarRanking();
    }

}
