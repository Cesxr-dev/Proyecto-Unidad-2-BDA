
package persistencia;

import dominio.InfoAdicional;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public interface IInfoAdicionalDAO {
    
    boolean crear(InfoAdicional infoAdicional, EntityManager em);
    
    InfoAdicional buscarPorId(int idInfo, EntityManager em);
    
    List<InfoAdicional> listar(EntityManager em);
    
    boolean actualizar(InfoAdicional infoAdicional, EntityManager em);
    
    boolean eliminar(Long id, EntityManager em);
    
}
