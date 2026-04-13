
package servicios;

import dominio.Perfil;
import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public interface IPerfilService extends IGenericoService<Perfil, Long> {
       Perfil autenticar(String correo, String contrasena);
    
    
    
}
