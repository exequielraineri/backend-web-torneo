/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.controller;

import com.raineri.torneo.app.model.Equipo;
import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.model.Partido;
import com.raineri.torneo.app.model.Torneo;
import com.raineri.torneo.app.servicio.IEquipoService;
import com.raineri.torneo.app.servicio.IJugadorService;
import com.raineri.torneo.app.servicio.PartidoService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    public IEquipoService equipoService;

    @Autowired
    public PartidoService partidoService;

    private Jugador jugadorActual = new Jugador();

    List<Torneo> torneosParticipados = new ArrayList<>();

    private Double ganados;
    private Double perdidos;
    private Double jugados;
    private Double efectividad;

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable Long id, Model model) {
        DecimalFormat df = new DecimalFormat("#.#");
        jugadorActual = jugadorService.obtener(id).get();
        obtenerEstadisticas(jugadorActual);

        model.addAttribute("jugador", jugadorActual);
        model.addAttribute("perdidos", perdidos.intValue());
        model.addAttribute("ganados", ganados.intValue());
        model.addAttribute("jugados", jugados.intValue());
        model.addAttribute("efectividad", df.format(efectividad.floatValue()));
        model.addAttribute("torneoParticipados", torneosParticipados);
        return "perfil";
    }

    public void obtenerEstadisticas(Jugador jugador) {
        List<Partido> partidos = partidoService.listar();
        torneosParticipados = new ArrayList<>();
        ganados = 0.0;
        perdidos = 0.0;
        jugados = 0.0;
        efectividad = 0.0;

        for (Partido p : partidos) {
            for (Jugador j : p.getEquipo_1().getJugadores()) {
                if (j.getId() == jugador.getId()) {
                    if (!existeEnTorneosParticipados(p.getTorneo())) {
                        torneosParticipados.add(p.getTorneo());
                    }
                    if (p.getRes1() != null && p.getRes2() != null) {
                        if (p.getRes1() > p.getRes2().intValue()) {
                            ganados++;
                        } else {
                            perdidos++;
                        }
                        jugados++;
                    }

                }
            }

            for (Jugador j : p.getEquipo_2().getJugadores()) {
                if (j.getId() == jugador.getId()) {
                    if (!existeEnTorneosParticipados(p.getTorneo())) {
                        torneosParticipados.add(p.getTorneo());
                    }
                    if (p.getRes1() != null && p.getRes2() != null) {
                        if (p.getRes2() > p.getRes1()) {
                            ganados++;
                        } else {
                            perdidos++;
                        }
                        jugados++;
                    }
                }
            }

        }

        //Efectividad = (Partidos Ganados / Partidos Jugados) * 100
        efectividad = (ganados / jugados) * 100;

        System.out.println(String.format("ganados %.2f div perdidos %.2f resultado %.2f%%", ganados.floatValue(), jugados.floatValue(), efectividad.floatValue()));
        System.out.println("fectividad: " + efectividad.floatValue());

    }

    private boolean existeEnTorneosParticipados(Torneo torneo) {
        for (Torneo t : torneosParticipados) {
            if (t.getId() == torneo.getId()) {
                return true;
            }
        }
        return false;
    }

}
