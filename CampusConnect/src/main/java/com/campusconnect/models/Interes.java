package com.campusconnect.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "intereses")
public class Interes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interes")
    private Long idInteres;

    @Column(name = "nombre_interes", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "intereses")
    private List<Perfil> perfiles;

    public Interes() {}

    public Long getIdInteres() {
        return idInteres;
    }

    public void setIdInteres(Long idInteres) {
        this.idInteres = idInteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    
}
