/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author ADMIN
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "torneo")
public class Torneo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "cupos", nullable = false)
    private Integer cupos;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    
    @Column(name = "valor")
    private BigDecimal valor;
    
    @Column(name = "lugar")
    private String lugar;
 
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "torneo_id")
    private List<Inscripcion> inscripciones=new ArrayList<>();
    
    public String mostrarFecha(){
        SimpleDateFormat sf=new SimpleDateFormat("dd-MM");
        return sf.format(fechaInicio);
    }
    
    
    public String mostrarMes(){
        SimpleDateFormat sf=new SimpleDateFormat("MMMM");
        return sf.format(fechaInicio);
    }
    
    
    public String mostrarHora(){
        SimpleDateFormat sf=new SimpleDateFormat("HH:mm");
        return sf.format(fechaInicio);
    }
}
