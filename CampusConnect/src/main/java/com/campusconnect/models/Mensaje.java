package com.campusconnect.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;

    @Column(name = "cuerpo", columnDefinition = "TEXT")
    private String cuerpo;

    @Column(name = "fecha_y_hora_de_envio")
    private LocalDateTime fechaHoraEnvio;

    @ManyToOne
    @JoinColumn(name = "id_match", nullable = false)
    private MatchEntity matchEntity;

    @ManyToOne
    @JoinColumn(name = "id_remitente", nullable = false)
    private Perfil remitente;

    @ManyToOne
    @JoinColumn(name = "id_receptor", nullable = false)
    private Perfil receptor;

    public Mensaje() {}

    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public LocalDateTime getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(LocalDateTime fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public MatchEntity getMatchEntity() {
        return matchEntity;
    }

    public void setMatchEntity(MatchEntity matchEntity) {
        this.matchEntity = matchEntity;
    }

    public Perfil getRemitente() {
        return remitente;
    }

    public void setRemitente(Perfil remitente) {
        this.remitente = remitente;
    }

    public Perfil getReceptor() {
        return receptor;
    }

    public void setReceptor(Perfil receptor) {
        this.receptor = receptor;
    }

    
}
