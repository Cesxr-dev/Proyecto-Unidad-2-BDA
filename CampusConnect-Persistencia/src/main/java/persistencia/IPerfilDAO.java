
package persistencia;

import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public interface IPerfilDAO {
    
    boolean crear(Perfil pefil, EntityManager em);
    
    Perfil buscarPorId(int idPerfil, EntityManager em);
    
    List<Perfil> listar(EntityManager em);
    
    boolean actualizar(Perfil perfil, EntityManager em);
    
    boolean eliminar(Long id, EntityManager em);
    
}
