
package dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Equipo 2 - "Azul"
 */
@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Long id;

    @Column(name = "fecha_hora_envio", nullable = false)
    private LocalDateTime fechaHoraEnvio;
    
    // Relación N:1 con Match
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    // Relación N:1 con Perfil
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
