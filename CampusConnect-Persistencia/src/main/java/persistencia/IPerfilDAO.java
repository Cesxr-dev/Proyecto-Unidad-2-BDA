
package persistencia;

import dominio.Perfil;
import jakarta.persistence.EntityManager;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public interface IPerfilDAO extends IGenericoDAO<Perfil, Long> {
    
        Perfil buscarPorCorreo(String correo, EntityManager em); 
}
