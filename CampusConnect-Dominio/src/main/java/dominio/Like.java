
package dominio;

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
 * @author Equipo 2 - "Azul"
 */
@Entity
@Table(name = "likes")
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
    
    // Relación N:1 con Perfil
    @ManyToOne
    @JoinColumn(name = "perfil_origen_id", nullable = false)
    private Perfil perfilOrigen;
    @ManyToOne
    @JoinColumn(name = "perfil_destino_id", nullable = false)
    private Perfil perfilDestino;

    // Relación 1:1 con Match
    @OneToOne(mappedBy = "likeA")
    private Match matchLikeA;
    @OneToOne(mappedBy = "likeB")
    private Match matchLikeB;

    public Like() {
    }

    public Like(Long id, LocalDate fecha, EstadoLike estadoLike, 
            Perfil perfilOrigen, Perfil perfilDestino, Match matchLikeA, Match matchLikeB) {
        this.id = id;
        this.fecha = fecha;
        this.estadoLike = estadoLike;
        this.perfilOrigen = perfilOrigen;
        this.perfilDestino = perfilDestino;
        this.matchLikeA = matchLikeA;
        this.matchLikeB = matchLikeB;
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

    public Match getMatchLikeA() {
        return matchLikeA;
    }
    public void setMatchLikeA(Match matchLikeA) {
        this.matchLikeA = matchLikeA;
    }

    public Match getMatchLikeB() {
        return matchLikeB;
    }
    public void setMatchLikeB(Match matchLikeB) {
        this.matchLikeB = matchLikeB;
    }

}
