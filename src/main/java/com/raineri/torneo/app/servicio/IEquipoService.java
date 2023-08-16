/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Equipo;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN
 */
public interface IEquipoService{
    
    public Equipo guardar(Equipo equipo);
    
    public List<Equipo> listar();
    
    public Optional<Equipo> obtener(Long id);

    
    
}
