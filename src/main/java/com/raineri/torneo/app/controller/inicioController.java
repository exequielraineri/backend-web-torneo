/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ADMIN
 */
@Controller
public class inicioController {
    
    
    
    @GetMapping("/inicio")
    public String inicio(){
        return "index";
    }
    
    
    
    
    @GetMapping("/perfil")
    public String perfil(){
        return "perfil";
    }
    
    
    @GetMapping("/agregarTorneo")
    public String agregarTorneo(){
        return "agregarTorneo";
    }
    
    
}
