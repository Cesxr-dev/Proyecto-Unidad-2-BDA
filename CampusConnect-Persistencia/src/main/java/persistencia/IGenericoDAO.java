
package persistencia;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public interface IGenericoDAO<T, ID> {
    
    void guardar(T entidad, EntityManager em);
    
    void actualizar(T entidad, EntityManager em);
    
    void eliminar(T entidad, EntityManager em);
    
    T buscarPorId(ID id, EntityManager em);
    
    List<T> listar(EntityManager em);
    
}
