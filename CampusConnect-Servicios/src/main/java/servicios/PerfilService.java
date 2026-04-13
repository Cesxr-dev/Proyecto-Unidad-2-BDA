
package servicios;
import dominio.SesionActiva;

import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import persistencia.IPerfilDAO;
import persistencia.PerfilDAO;
import utils.JPAUtil;

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
        validarPerfil(perfil);
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            perfilDao.guardar(perfil, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.print("Error al guardar el perfil.");
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Perfil perfil) {
        validarPerfil(perfil);
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            perfilDao.actualizar(perfil, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.print("Error al actualizar el perfil.");
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            perfilDao.eliminar(id, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.print("Error al eliminar el perfil.");
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Perfil autenticar(String correo, String contrasena) {
        /*
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Perfil perfil = perfilDao.buscarPorCorreo(correo, em);
        
            if (perfil != null && perfil.getContrasena().equals(contrasena)) {
                return perfil;
            }
            return null;
        } finally {
            em.close();
        }
        */
            EntityManager em = JPAUtil.getEntityManager();
    try {
        Perfil perfil = perfilDao.buscarPorCorreo(correo, em);
        
        if (perfil != null && perfil.getContrasena().equals(contrasena)) {
            return perfil;
        }
        return null;
    } finally {
        em.close();
    }
    }

    @Override
    public Perfil buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Perfil> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return perfilDao.listar(em);
        } finally {
            em.close();
        }
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
        
        //  Validación de carrera
        if (perfil.getCarrera() == null || perfil.getCarrera().getNombre().trim().isBlank()) {
            throw new IllegalArgumentException("La carrera del perfil es obligatoria");
        }
        
        //  Validación de correo institucional
        if (perfil.getCorreoInstitucional() == null || perfil.getCorreoInstitucional()
                .trim().isBlank()) {
            throw new IllegalArgumentException("El correo institucional del "
                    + "perfil es obligatorio");
        }
        
        //  Validación de contraseña
        if (perfil.getContrasena() == null || perfil.getContrasena().trim().isBlank()) {
            throw new IllegalArgumentException("La contrasena del perfil es obligatoria");
        }
        
        //  Validación de información adicional
        if (perfil.getPerfilInfoAdicional() == null || perfil.getPerfilInfoAdicional().isEmpty()) {
            throw new IllegalArgumentException("La informacion adicional del perfil es obligatoria");
        }
        
    }
    
        public SesionActiva autenticarYCrearSesion(String correo, String contrasena,
            String tipoDispositivo, String direccionIp) {
        Perfil perfil = autenticar(correo, contrasena);

        if (perfil != null) {
            try {
           
                SesionActivaService sesionService = new SesionActivaService();
                SesionActiva sesion = sesionService.crearSesion(perfil.getId(),
                        tipoDispositivo,
                        direccionIp);
                return sesion;
            } catch (Exception e) {
                System.err.println("Error al crear sesión durante autenticación: " + e.getMessage());
                return null;
            }
        }

        return null;
    }
    
}
