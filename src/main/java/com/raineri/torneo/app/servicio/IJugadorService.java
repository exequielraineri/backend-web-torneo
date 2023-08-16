/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Jugador;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface IJugadorService {

    public List<Jugador> listar();

    public Optional<Jugador> obtener(Long id);

    public Jugador guardar(Jugador jugador);

    public Optional<Jugador> obtenerPorUsuario(String usuario);

    public Optional<Jugador> obtenerPorDNI(String dni);

}
