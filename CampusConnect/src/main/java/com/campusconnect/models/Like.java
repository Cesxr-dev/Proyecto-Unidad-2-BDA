/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campusconnect.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author demib
 */
@Entity
@Table(name = "Likes")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;
    
    @Column(name = "fecha_like",nullable = false)
    private LocalDate fecha;
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_like", nullable = false)
    private EstadoLike estadoLike;
    
    //RELACION CON PERFIL
    @ManyToOne
    @JoinColumn(name = "perfil_origen_id", nullable = false)
    private Perfil perfilOrigen;
    
    @ManyToOne
    @JoinColumn(name = "perfil_destino_id", nullable = false)
    private Perfil perfilDestino;

    //RELACION CON MATCH
    @OneToOne(mappedBy = "likeA")
    private Match matchComoA;

    @OneToOne(mappedBy = "likeB")
    private Match matchComoB;

    public Like() {
    }

    public Like(Long id, LocalDate fecha, EstadoLike estadoLike, Perfil perfilOrigen, Perfil perfilDestino, Match matchComoA, Match matchComoB) {
        this.id = id;
        this.fecha = fecha;
        this.estadoLike = estadoLike;
        this.perfilOrigen = perfilOrigen;
        this.perfilDestino = perfilDestino;
        this.matchComoA = matchComoA;
        this.matchComoB = matchComoB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoLike getEstadoLike() {
        return estadoLike;
    }

    public void setEstadoLike(EstadoLike estadoLike) {
        this.estadoLike = estadoLike;
    }

    public Perfil getPerfilOrigen() {
        return perfilOrigen;
    }

    public void setPerfilOrigen(Perfil perfilOrigen) {
        this.perfilOrigen = perfilOrigen;
    }

    public Perfil getPerfilDestino() {
        return perfilDestino;
    }

    public void setPerfilDestino(Perfil perfilDestino) {
        this.perfilDestino = perfilDestino;
    }

    public Match getMatchComoA() {
        return matchComoA;
    }

    public void setMatchComoA(Match matchComoA) {
        this.matchComoA = matchComoA;
    }

    public Match getMatchComoB() {
        return matchComoB;
    }

    public void setMatchComoB(Match matchComoB) {
        this.matchComoB = matchComoB;
    }

    

    
    

    
    
}
