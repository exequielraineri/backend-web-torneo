/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raineri.torneo.app.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partido")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "res_1")
    private Integer res1;
    @Column(name = "res_2")
    private Integer res2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipo_1")
    private Equipo equipo_1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipo_2")
    private Equipo equipo_2;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "partido_id")
    private List<SetPartido> setPartido = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

    public int getJugados() {
        int cont = 0;
        for (SetPartido setP : setPartido) {
            if (setP.getRes1() != null && setP.getRes2() != null) {
                cont++;
            }
        }
        return cont;
    }

    public boolean verificarAumentoSet() {
        boolean puedeAgregarSet = true;
        if (getJugados() >= 5) {
            puedeAgregarSet = false;
        }

        res1 = res1 == null ? 0 : res1;
        res2 = res2 == null ? 0 : res2;
        if (res1 == 5 || res2 == 5) {
            puedeAgregarSet = false;
        }

        if (getJugados() >= 3) {

            if (res1 == (res2 + 2)) {
                puedeAgregarSet = false;
            }

            if (res2 == (res1 + 2)) {
                puedeAgregarSet = false;
            }
        }
        return !puedeAgregarSet;

    }
    
    
 

    

}
