/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.controller;

import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.servicio.IJugadorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ADMIN
 */
@Controller
public class jugadoresController {
    
    @Autowired
    public IJugadorService jugadorService;
    
    List<Jugador> jugadores = new ArrayList<>();
    
    @GetMapping("/jugadores")
    public String jugadores(Model model) {
        jugadores = obtenerJugadores();
        model.addAttribute("jugadores", jugadores);
        return "jugadores";
    }
    
    public List<Jugador> obtenerJugadores() {
        jugadores = jugadorService.listarRanking();
        
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).setPuesto((i+1));
            System.out.println("---\n\n\n\nI-->"+i);
            jugadorService.guardar(jugadores.get(i));
        }
        
        return jugadores;
    }
    
}
