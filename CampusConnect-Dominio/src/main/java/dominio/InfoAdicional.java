
package dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Equipo 2 - "Azul"
 */
@Entity
@Table(name = "info_adicional")
public class InfoAdicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInfo tipo;

    @Column(nullable = false, length = 50)
    private String nombre;
    
    // Relacion M:N con perfiles
    @ManyToMany(mappedBy = "info_adicional")
    private Set<Perfil> perfiles;

    public InfoAdicional() {
    }

    public InfoAdicional(Long id, TipoInfo tipo, String nombre, Set<Perfil> perfiles) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.perfiles = perfiles;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public TipoInfo getTipo() {
        return tipo;
    }
    public void setTipo(TipoInfo tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Perfil> getPerfiles() {
        return perfiles;
    }
    public void setPerfiles(Set<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}
