/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.controller;

import com.raineri.torneo.app.model.Equipo;
import com.raineri.torneo.app.model.Inscripcion;
import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.model.Partido;
import com.raineri.torneo.app.model.SetPartido;
import com.raineri.torneo.app.model.Torneo;
import com.raineri.torneo.app.servicio.EquipoService;
import com.raineri.torneo.app.servicio.ITorneoService;
import com.raineri.torneo.app.servicio.InscripcionService;
import com.raineri.torneo.app.servicio.JugadorService;
import com.raineri.torneo.app.servicio.PartidoService;
import com.raineri.torneo.app.servicio.SetPartidoServ;
import com.raineri.torneo.app.servicio.TorneoService;
import jakarta.xml.soap.SAAJMetaFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("torneos")
public class torneoController {

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private SetPartidoServ SetService;

    @Autowired
    private PartidoService partidoService;

    List<Torneo> torneos = new ArrayList<>();
    List<Equipo> jugadoresInscriptos = new ArrayList<>();

    private Torneo torneo;

    boolean partidosHidden = true;

    private List<Partido> partidos = new ArrayList<>();

    @GetMapping("/")
    public String torneos(Model model) {
        torneos = torneoService.listar();

        model.addAttribute("torneos", torneos);

        return "torneos";
    }

    @GetMapping("/torneo/{id}")
    public String torneoID(@PathVariable Long id, Model model) {
        Torneo torneo_e = torneoService.obtener(id).get();
        torneo = torneo_e;
        jugadoresInscriptos = obtenerInscriptos();
        model.addAttribute("torneo", torneo_e);
        model.addAttribute("jugadoresInscriptos", jugadoresInscriptos);
        partidosHidden = verificarFechaParaMostrarPartidos();
        model.addAttribute("partidosHidden", partidosHidden);
        partidos = obtenerPartidos(torneo);
        model.addAttribute("partidos", partidos);

        return "paginaTorneo";
    }

    @GetMapping("/agregarTorneo")
    public String agrgarTorneo(Model model) {
        Torneo t = new Torneo();
        model.addAttribute("torneo", t);
        return "agregarTorneo";
    }

    @PostMapping("/guardarTorneo")
    public String guardarTorneo(@ModelAttribute(name = "torneo") Torneo torneo, @RequestParam(name = "fechaIncio") String fechaIncio, @RequestParam(name = "hora") String hora) {
        Calendar c = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaIncio);
            // Ahora puedes trabajar con la fecha en Java

            sdf = new SimpleDateFormat("HH:mm");
            Date horaD = sdf.parse(hora);

            c.setTime(fecha);
            c.set(Calendar.HOUR_OF_DAY, horaD.getHours());
            c.set(Calendar.MINUTE, horaD.getMinutes());
            c.set(Calendar.SECOND, horaD.getSeconds());

            torneo.setFechaInicio(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        torneoService.guardar(torneo);
        return "redirect:/";
    }

    private List<Equipo> obtenerInscriptos() {
        jugadoresInscriptos = new ArrayList<>();
        for (Inscripcion ins : torneo.getInscripciones()) {
            jugadoresInscriptos.add(ins.getEquipo());
        }
        return jugadoresInscriptos;
    }

    /*private boolean existeYa(Jugador jug) {
        for (Jugador ji : jugadoresInscriptos) {
            if (ji.getId() == jug.getId()) {
                return true;
            }
        }
        return false;
    }*/
    @PostMapping("inscribir/{id}")
    public String inscribir(@PathVariable Long id, @RequestParam(name = "dni_1") String dni_1, @RequestParam(name = "dni_2") String dni_2) {
        Torneo torneo = torneoService.obtener(id).get();
        Inscripcion inscripcion = new Inscripcion();
        Optional<Jugador> j1 = jugadorService.obtenerPorDNI(dni_1);
        Optional<Jugador> j2 = jugadorService.obtenerPorDNI(dni_2);

        StringBuilder msj_ins = new StringBuilder();

        if (j1.isPresent() && j2.isPresent()) {
            //correcto
            Jugador jugador_1 = j1.get();
            Jugador jugador_2 = j2.get();
            if (existeEnElTorneo(torneo, jugador_1, jugador_2)) {
                msj_ins.append("Los jugadores ya tienen un equipo registrado!");
            } else {
                Equipo equipo = existeEquipo(jugador_1, jugador_2);
                if (equipo != null) {
                    System.out.println("Existe");
                } else {
                    equipo = new Equipo();
                    Jugador[] jugadores = new Jugador[2];
                    jugadores[0] = jugador_1;
                    jugadores[1] = jugador_2;
                    equipo.setJugadores(jugadores);
                    equipo = equipoService.guardar(equipo);

                }

                inscripcion.setEquipo(equipo);
                inscripcion.setFechaInscripcion(new Date());

                inscripcion = inscripcionService.guardar(inscripcion);
                torneo.getInscripciones().add(inscripcion);
                torneoService.guardar(torneo);
                System.out.println("Inscripcion exitosa!");
            }

        } else {
            if (!j1.isPresent()) {
                msj_ins.append(String.format("El jugador con DNI %s no esta registrado!\n", dni_1));
            }

            if (!j2.isPresent()) {
                msj_ins.append(String.format("El jugador con DNI %s no esta registrado!", dni_2));
            }

        }

        System.out.println("Mensaje->" + msj_ins);
        return "redirect:/torneos/";
    }

    private Equipo existeEquipo(Jugador jugador_1, Jugador jugador_2) {
        boolean existe = false;
        Equipo equipo = null;
        List<Equipo> equipos = equipoService.listar();
        for (Equipo e : equipos) {
            if ((e.getJugadores()[0].getId() == jugador_1.getId() && e.getJugadores()[1].getId() == jugador_2.getId()) || (e.getJugadores()[0].getId() == jugador_2.getId() && e.getJugadores()[1].getId() == jugador_1.getId())) {
                existe = true;
                equipo = e;
                break;
            }
        }

        return equipo;

    }

    private boolean existeEnElTorneo(Torneo torneo, Jugador jugador_1, Jugador jugador_2) {
        boolean existe = false;
        for (Inscripcion ins : torneo.getInscripciones()) {
            for (Jugador jug : ins.getEquipo().getJugadores()) {
                if (jug.getId() == jugador_1.getId() || jug.getId() == jugador_2.getId()) {
                    System.out.println("Exist");
                    existe = true;
                    break;
                }
            }

        }
        return existe;
    }

    private boolean verificarFechaParaMostrarPartidos() {
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date fechaActual = new Date();
        if (fechaActual.before(torneo.getFechaInicio())) {
            System.out.println("--------------------------------------------------\n\n");
            System.out.println(String.format("%s es after que %s --> %b", sf.format(fechaActual), sf.format(torneo.getFechaInicio()), fechaActual.after(torneo.getFechaInicio())));
            return true;
        }
        return false;

    }

    @PostMapping("/guardarPartido")
    public String guardarPartido(@RequestParam(name = "equipo_1") String equipo_1_id, @RequestParam(name = "equipo_2") String equipo_2_id, @RequestParam(name = "res_1") String res_1, @RequestParam(name = "res_2") String res_2, @RequestParam(name = "set_1_1") String set_1_1, @RequestParam(name = "set_2_1") String set_2_1, @RequestParam(name = "set_1_2") String set_1_2, @RequestParam(name = "set_2_2") String set_2_2, @RequestParam(name = "set_1_3") String set_1_3, @RequestParam(name = "set_2_3") String set_2_3) {

        Partido partido = new Partido();
        Equipo equipo1 = equipoService.obtener(Long.parseLong(equipo_1_id)).get();
        Equipo equipo2 = equipoService.obtener(Long.parseLong(equipo_2_id)).get();

        partido.setEquipo_1(equipo1);
        partido.setEquipo_2(equipo2);

        partido.setRes1(Integer.parseInt(res_1));
        partido.setRes2(Integer.parseInt(res_2));

        SetPartido set_1 = new SetPartido();
        SetPartido set_2 = new SetPartido();
        SetPartido set_3 = new SetPartido();

        set_1.setRes1(Integer.parseInt(set_1_1));
        set_2.setRes1(Integer.parseInt(set_1_2));
        set_3.setRes1(Integer.parseInt(set_1_3));

        set_1.setRes2(Integer.parseInt(set_2_1));
        set_2.setRes2(Integer.parseInt(set_2_2));
        set_3.setRes2(Integer.parseInt(set_2_3));

        set_1 = SetService.guardar(set_1);
        set_2 = SetService.guardar(set_2);
        set_3 = SetService.guardar(set_3);

        List<SetPartido> setPartido = new ArrayList<>();
        setPartido.add(set_1);
        setPartido.add(set_2);
        setPartido.add(set_3);

        partido.setSetPartido(setPartido);
        torneo = torneoService.obtener(torneo.getId()).get();
        partido.setTorneo(torneo);

        if (partido.getRes1() > partido.getRes2()) {
            partido.getEquipo_1().setPuntos(partido.getEquipo_1().getPuntos().intValue() + 3);

            partido.getEquipo_1().getJugadores()[0].setPuntos(partido.getEquipo_1().getJugadores()[0].getPuntos().intValue() + 3);
            partido.getEquipo_1().getJugadores()[1].setPuntos(partido.getEquipo_1().getJugadores()[1].getPuntos().intValue() + 3);
            jugadorService.guardar(partido.getEquipo_1().getJugadores()[0]);
            jugadorService.guardar(partido.getEquipo_1().getJugadores()[1]);
            equipoService.guardar(partido.getEquipo_1());

        } else {
            partido.getEquipo_2().setPuntos(partido.getEquipo_2().getPuntos().intValue() + 3);
            
            partido.getEquipo_2().getJugadores()[0].setPuntos(partido.getEquipo_2().getJugadores()[0].getPuntos().intValue() + 3);
            partido.getEquipo_2().getJugadores()[1].setPuntos(partido.getEquipo_2().getJugadores()[1].getPuntos().intValue() + 3);
            jugadorService.guardar(partido.getEquipo_2().getJugadores()[0]);
            jugadorService.guardar(partido.getEquipo_2().getJugadores()[1]);
            equipoService.guardar(partido.getEquipo_2());
        }

        partido = partidoService.guardar(partido);

        return "redirect:/torneos/";

    }

    private List<Partido> obtenerPartidos(Torneo t) {
        partidos = new ArrayList<>();
        partidos = partidoService.listar();
        List<Partido> aux = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getTorneo().getId() == t.getId()) {
                aux.add(p);
            }
        }

        return aux;
    }

}
