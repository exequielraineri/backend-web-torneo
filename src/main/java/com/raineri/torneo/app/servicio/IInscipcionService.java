/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raineri.torneo.app.servicio;

import com.raineri.torneo.app.model.Inscripcion;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ADMIN
 */
public interface IInscipcionService {

    public List<Inscripcion> listar();

    public Optional<Inscripcion> obtener(Long id);

    public Inscripcion guardar(Inscripcion inscripcion);

}
