/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.controller;

import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.servicio.IJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ADMIN
 */
@Controller
public class perfilController {

    @Autowired
    public IJugadorService jugadorService;

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable Long id, Model model) {
        Jugador j = jugadorService.obtener(id).get();
        model.addAttribute("jugador", j);
        return "perfil";
    }

}
