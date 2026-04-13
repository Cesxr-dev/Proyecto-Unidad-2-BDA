package dominio;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Equipo 2 - "Azul"
 */
@Entity
@Table(name = "sesiones_activas",
        indexes = {
            @Index(name = "IDX_perfil_estado", columnList = "perfil_id, estado"),
            @Index(name = "IDX_sesiones_activas_perfil", columnList = "perfil_id, estado, fecha_inicio")
        })
public class SesionActiva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesion_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @Column(name = "token_sesion", nullable = false, unique = true, length = 500)
    private String tokenSesion;

    @Column(name = "direccion_ip", length = 45)
    private String direccionIp;

    @Column(name = "tipo_dispositivo", length = 50)
    private String tipoDispositivo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_ultima_actividad", nullable = false)
    private LocalDateTime fechaUltimaActividad;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoSesion estado;

    public enum EstadoSesion {
        ACTIVA, CERRADA, EXPIRADA
    }


    public SesionActiva() {
    }

    public SesionActiva(Perfil perfil, String tokenSesion, String direccionIp, 
                        String tipoDispositivo, LocalDateTime fechaInicio) {
        this.perfil = perfil;
        this.tokenSesion = tokenSesion;
        this.direccionIp = direccionIp;
        this.tipoDispositivo = tipoDispositivo;
        this.fechaInicio = fechaInicio;
        this.fechaUltimaActividad = fechaInicio;
        this.estado = EstadoSesion.ACTIVA;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getTokenSesion() {
        return tokenSesion;
    }

    public void setTokenSesion(String tokenSesion) {
        this.tokenSesion = tokenSesion;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaUltimaActividad() {
        return fechaUltimaActividad;
    }

    public void setFechaUltimaActividad(LocalDateTime fechaUltimaActividad) {
        this.fechaUltimaActividad = fechaUltimaActividad;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
    }
}