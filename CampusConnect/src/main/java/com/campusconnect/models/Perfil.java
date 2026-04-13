package com.campusconnect.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Carrera carrera;

    @Column(name = "fecha_nacimiento",nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "foto_perfil")
    private String fotoPerfil;
    
    //Relacion N:M sin atributos con infoAdicional
    
    @ManyToMany
    @JoinTable(
            
        name = "perfil_info_adicional",
        joinColumns = @JoinColumn(name = "perfil_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "info_adicional_id", nullable = false)
    )
    private List<InfoAdicional> perfilInfoAdicional;
    
    //Relacion 1:n con Like
    
    @OneToMany(mappedBy = "perfilOrigen")
    private Set<Like> likesEnviados;
    
    @OneToMany(mappedBy = "perfilDestino")
    private Set<Like> likesRecibidos;
    
    

    //Relacion 1:n con Mensajes
    
    @OneToMany(mappedBy = "perfilEmisor")
    private Set<Mensaje> mensajes;

    public Perfil() {
    }

    public Perfil(Long id, String nombre, Carrera carrera, LocalDate fechaNacimiento, String correoInstitucional, String contrasena, String fotoPerfil, List<InfoAdicional> perfilInfoAdicional, Set<Like> likesEnviados, Set<Like> likesRecibidos, Set<Mensaje> mensajes) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.fechaNacimiento = fechaNacimiento;
        this.correoInstitucional = correoInstitucional;
        this.contrasena = contrasena;
        this.fotoPerfil = fotoPerfil;
        this.perfilInfoAdicional = perfilInfoAdicional;
        this.likesEnviados = likesEnviados;
        this.likesRecibidos = likesRecibidos;
        this.mensajes = mensajes;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public List<InfoAdicional> getPerfilInfoAdicional() {
        return perfilInfoAdicional;
    }

    public void setPerfilInfoAdicional(List<InfoAdicional> perfilInfoAdicional) {
        this.perfilInfoAdicional = perfilInfoAdicional;
    }

    public Set<Like> getLikesEnviados() {
        return likesEnviados;
    }

    public void setLikesEnviados(Set<Like> likesEnviados) {
        this.likesEnviados = likesEnviados;
    }

    public Set<Like> getLikesRecibidos() {
        return likesRecibidos;
    }

    public void setLikesRecibidos(Set<Like> likesRecibidos) {
        this.likesRecibidos = likesRecibidos;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    
    
    
    

    

    

    

    

    
}
