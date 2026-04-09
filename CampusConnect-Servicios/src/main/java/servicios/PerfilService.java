
package servicios;

import dominio.Perfil;
import java.time.LocalDate;
import java.util.List;
import persistencia.IPerfilDAO;
import persistencia.PerfilDAO;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class PerfilService implements IPerfilService {
    
    private IPerfilDAO perfilDao;
    
    public PerfilService() {
        this.perfilDao = new PerfilDAO();
    }

    @Override
    public void guardar(Perfil perfil) {
        
    }

    @Override
    public void actualizar(Perfil entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Perfil entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Perfil buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Perfil> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void validarPerfil(Perfil perfil) {
        //  Validacion de objeto
        if (perfil == null) {
            throw new IllegalArgumentException("El perfil no puede ser nulo.");
        }
        
        //  Validacion de nombre
        if (perfil.getNombre() == null || perfil.getNombre().trim().isBlank()) {
            throw new IllegalArgumentException("El nombre del perfil es obligatorio.");
        }
        
        //  Validaciones de fecha de nacimiento
        if (perfil.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede ser despues del dia de hoy");
        }
        if (perfil.getFechaNacimiento().isAfter(LocalDate.now().minusYears(16))) {
            throw new IllegalArgumentException("La edad minima de registro es de 16 años.");
        }
        
        // Validación de carrera
        if (perfil.getCarrera() == null || perfil.getCarrera().getNombre().trim().isBlank()) {
            throw new IllegalArgumentException("La carrera del perfil es obligatoria");
        }
        
        
    }
    
}
