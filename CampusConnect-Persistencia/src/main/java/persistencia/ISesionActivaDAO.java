
package persistencia;

import dominio.SesionActiva;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * @author Equipo 2 - "Azul"
 */
public interface ISesionActivaDAO extends IGenericoDAO<SesionActiva, Long> {
    

    SesionActiva buscarPorToken(String token, EntityManager em);
    
   
    List<SesionActiva> obtenerSesionesActivas(Long perfilId, EntityManager em);
    
  
    long contarSesionesActivas(Long perfilId, EntityManager em);

    void cerrarSesion(Long sesionId, EntityManager em);
    
 
    SesionActiva obtenerSesionMasAntigua(Long perfilId, EntityManager em);
}