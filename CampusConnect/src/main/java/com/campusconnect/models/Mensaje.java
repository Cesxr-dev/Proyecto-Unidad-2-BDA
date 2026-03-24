/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campusconnect.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author demib
 */
@Entity
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Long id;

    @Column(name = "fecha_hora_envio", nullable = false)
    private LocalDateTime fechaHoraEnvio;
    
    //RELACION CON MATCH
    
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    //RELACION CON PERFIL
    
    @ManyToOne
    @JoinColumn(name = "perfil_emisor_id", nullable = false)
    private Perfil perfilEmisor;

    public Mensaje() {
    }

    public Mensaje(Long id, LocalDateTime fechaHoraEnvio, Match match, Perfil perfilEmisor) {
        this.id = id;
        this.fechaHoraEnvio = fechaHoraEnvio;
        this.match = match;
        this.perfilEmisor = perfilEmisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(LocalDateTime fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Perfil getPerfilEmisor() {
        return perfilEmisor;
    }

    public void setPerfilEmisor(Perfil perfilEmisor) {
        this.perfilEmisor = perfilEmisor;
    }
    
    
    
    
    
    
    
}
