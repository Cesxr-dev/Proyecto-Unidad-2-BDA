
package dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author Equipo 2 - "Azul"
 */
@Entity
@Table(name = "matches")
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;
    
    @Column(name = "fecha_match", nullable = false)
    private LocalDate fechaMatch;
    
    //Relacion con 1:1 con Like
    @OneToOne
    @JoinColumn(name = "like_a", nullable = false)
    private Like likeA;
    @OneToOne
    @JoinColumn(name = "like_b", nullable = false)
    private Like likeB;

    // Relación 1:N con Mensaje
    @OneToMany(mappedBy = "match")
    private Set<Mensaje> mensajes;

    public Match() {
    }

    public Match(Long id, LocalDate fechaMatch, Like likeA, Like likeB, Set<Mensaje> mensajes) {
        this.id = id;
        this.fechaMatch = fechaMatch;
        this.likeA = likeA;
        this.likeB = likeB;
        this.mensajes = mensajes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaMatch() {
        return fechaMatch;
    }
    public void setFechaMatch(LocalDate fechaMatch) {
        this.fechaMatch = fechaMatch;
    }

    public Like getLikeA() {
        return likeA;
    }
    public void setLikeA(Like likeA) {
        this.likeA = likeA;
    }

    public Like getLikeB() {
        return likeB;
    }
    public void setLikeB(Like likeB) {
        this.likeB = likeB;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }
    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

}
