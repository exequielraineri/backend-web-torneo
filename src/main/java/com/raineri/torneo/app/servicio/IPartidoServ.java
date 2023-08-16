/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Partido;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN
 */
public interface IPartidoServ {
    public Partido guardar(Partido partido);
    
    public List<Partido> listar();
    
    public Optional<Partido> obtener(Long id);

}
