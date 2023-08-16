/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.repository;

import com.raineri.torneo.app.model.Jugador;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface IJugador extends JpaRepository<Jugador, Long>{
    
    
    @Query("SELECT j FROM Jugador j WHERE j.usuario= :usuario")
    public Optional<Jugador> obtenerPorUsuario(String usuario);
    
    
     @Query("SELECT j FROM Jugador j WHERE j.dni= :dni")
    public Optional<Jugador> obtenerPorDNI(String dni);
    
}
