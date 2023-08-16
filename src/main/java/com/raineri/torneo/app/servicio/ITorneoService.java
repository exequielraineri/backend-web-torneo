/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Torneo;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN
 */
public interface ITorneoService {
    
    public Torneo guardar(Torneo torneo);
    
    public List<Torneo> listar();
    
    public Optional<Torneo> obtener(Long id);

    
    
}
