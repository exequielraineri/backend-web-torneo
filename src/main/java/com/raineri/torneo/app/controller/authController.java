/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.controller;

import com.raineri.torneo.app.model.Jugador;
import com.raineri.torneo.app.servicio.IEquipoService;
import com.raineri.torneo.app.servicio.IJugadorService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
public class authController {

    @Autowired
    private IJugadorService jugDao;

    @Autowired
    private IEquipoService equipoDao;

    boolean verLogin = true;
    boolean verRegister = false;

    String mensaje = "";

    @GetMapping("/acceder")
    public String acceder(Model model) {
        
        model.addAttribute("login", verLogin);
        model.addAttribute("register", verRegister);
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("mensaje_error", mensaje);
        return "acceso";
    }

    
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession sesion){
        sesion.invalidate();
        return "redirect:/acceder";
    }
    
    
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute(name = "jugador") Jugador jugador, Model model) {

        try {
            
            jugDao.guardar(jugador);
            verLogin = true;
            verRegister = false;
            model.addAttribute("login", verLogin);
            model.addAttribute("register", verRegister);
            model.addAttribute("jugador", new Jugador());
            mensaje="Registro exitoso!";
        } catch (Exception e) {
            mensaje = "Ya existe jugador con ese DNI";
            verRegister = true;

            model.addAttribute("jugador", jugador);
            
            return "redirect:/acceder";
        } finally {
            model.addAttribute("mensaje_error", mensaje);
        }
        return "acceso";
    }

    @GetMapping("/login")
    public String cambiarLoginRegister(Model model) {
        if (verLogin) {
            mensaje="";
            verLogin = false;
        } else {
            mensaje="";
            verLogin = true;
        }

        if (verRegister) {
            verRegister = false;
        } else {
            verRegister = true;
        }

        model.addAttribute("login", verLogin);
        model.addAttribute("register", verRegister);
        return "redirect:/acceder";
    }

    @PostMapping("/loguear")
    public String loguearse(@ModelAttribute(name = "jugador") Jugador jugador, HttpSession session,Model model) {
        Optional<Jugador> jugadorExistente = jugDao.obtenerPorUsuario(jugador.getUsuario());
        if (jugadorExistente.isPresent()) {
            session.setAttribute("jugador", jugadorExistente.get());
            model.addAttribute("jugador", jugadorExistente.get());
            return "redirect:/perfil/".concat(jugadorExistente.get().getId().toString());
        } else {
            return "redirect:/acceder";
        }

    }

    /*
    @GetMapping("/jugadores")
    public List<Jugador> listar() {
        return jugDao.listar();
    }

    @GetMapping("/jugador/{id}")
    public Jugador obtener(@PathVariable Long id) {
        return jugDao.obtener(id).get();
    }

    @PutMapping("/guardar")
    public ResponseEntity<Jugador> guardar(@RequestBody Jugador jugador) {
        Jugador j = jugDao.guardar(jugador);
        try {

            return new ResponseEntity<Jugador>(j, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Jugador>(j, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/equipos")
    public List<Equipo> listarE() {
        return equipoDao.listar();
    }

    @PutMapping("/guardarEquipo")
    public ResponseEntity<Equipo> guardarEquipo(@RequestBody Equipo equipo) {
        Equipo j = equipoDao.guardar(equipo);
        try {
            return new ResponseEntity<Equipo>(j, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Equipo>(j, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/guardar/equipo/{idEquipo}/jugador/{idJugador}")
    public Equipo agregarJugadorAlquipo(@PathVariable(name = "idEquipo") Long idEquipo,@PathVariable("idJugador") Long idJugador) {
        
        return equipoDao.asignarEquipoJugador(idEquipo,idJugador);

    }
     */
}
