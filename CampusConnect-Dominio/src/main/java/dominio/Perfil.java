package dominio;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Equipo 2 - "Azul"
 */
@Entity
@Table(name = "perfiles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"correo_institucional"})})
public class Perfil implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Carrera carrera;

    @Column(name = "correo_institucional", nullable = false, length = 50)
    private String correoInstitucional;

    @Column(name = "contrasena", nullable = false, length = 30)
    private String contrasena;

    //Relacion M:N sin atributos con infoAdicional
    @ManyToMany
    @JoinTable(
        name = "perfil_info_adicional",
        joinColumns = @JoinColumn(name = "perfil_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "info_adicional_id", nullable = false)
    )
    private List<InfoAdicional> perfilInfoAdicional;
    
    //Relacion 1:N con Like
    @OneToMany(mappedBy = "perfilOrigen")
    private Set<Like> likesEnviados;
    @OneToMany(mappedBy = "perfilDestino")
    private Set<Like> likesRecibidos;
    
    //Relacion 1:N con Mensajes
    @OneToMany(mappedBy = "perfilEmisor")
    private Set<Mensaje> mensajes;

    public Perfil() {
    }

    public Perfil(Long id, String nombre, LocalDate fechaNacimiento, 
            String fotoPerfil, Carrera carrera, String correoInstitucional, 
            String contrasena, List<InfoAdicional> perfilInfoAdicional, 
            Set<Like> likesEnviados, Set<Like> likesRecibidos, Set<Mensaje> mensajes) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fotoPerfil = fotoPerfil;
        this.carrera = carrera;
        this.correoInstitucional = correoInstitucional;
        this.contrasena = contrasena;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Carrera getCarrera() {
        return carrera;
    }
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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
