
package servicios;

import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public interface IGenericoService<T, ID> {
    
    void guardar(T entidad);
    
    void actualizar(T entidad);
    
    void eliminar(T entidad);
    
    T buscarPorId(ID id);
    
    List<T> listar();
    
}
