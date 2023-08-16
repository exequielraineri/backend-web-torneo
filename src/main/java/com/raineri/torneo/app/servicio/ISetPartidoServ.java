/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.SetPartido;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN
 */
public interface ISetPartidoServ {
    public SetPartido guardar(SetPartido setPartido);
    
    public List<SetPartido> listar();
    
    public Optional<SetPartido> obtener(Long id);

}
