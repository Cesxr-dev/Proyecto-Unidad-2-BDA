package com.campusconnect.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match")
    private Long idMatch;

    @ManyToOne
    @JoinColumn(name = "id_perfil_a", nullable = false)
    private Perfil perfilA;

    @ManyToOne
    @JoinColumn(name = "id_perfil_b", nullable = false)
    private Perfil perfilB;

    @Column(name = "reaccion_perfil_a")
    private String reaccionPerfilA;

    @Column(name = "reaccion_perfil_b")
    private String reaccionPerfilB;

    @OneToMany(mappedBy = "matchEntity", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    public Match() {}

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public Perfil getPerfilA() {
        return perfilA;
    }

    public void setPerfilA(Perfil perfilA) {
        this.perfilA = perfilA;
    }

    public Perfil getPerfilB() {
        return perfilB;
    }

    public void setPerfilB(Perfil perfilB) {
        this.perfilB = perfilB;
    }

    public String getReaccionPerfilA() {
        return reaccionPerfilA;
    }

    public void setReaccionPerfilA(String reaccionPerfilA) {
        this.reaccionPerfilA = reaccionPerfilA;
    }

    public String getReaccionPerfilB() {
        return reaccionPerfilB;
    }

    public void setReaccionPerfilB(String reaccionPerfilB) {
        this.reaccionPerfilB = reaccionPerfilB;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    
}
