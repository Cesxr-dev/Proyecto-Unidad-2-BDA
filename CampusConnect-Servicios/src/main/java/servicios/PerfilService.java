
package servicios;

import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import persistencia.IPerfilDAO;
import persistencia.LikeDAO;
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
    
    //metodoPrueba
    public boolean actualizar2(dominio.Perfil perfil) {
        try {
            jakarta.persistence.EntityManager em = utils.JPAUtil.getEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(perfil);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw e;
            } finally {
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //metodoPrueba
    
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

    //metodoPrueba
    public boolean eliminar2(Long perfilId) {
   EntityManager em = utils.JPAUtil.getEntityManager();

    try {
        em.getTransaction().begin();

        Perfil perfil = em.find(Perfil.class, perfilId);

        if (perfil != null) {

          
            em.createQuery(
                "DELETE FROM Match m WHERE m.likeA.id IN (" +
                "SELECT l.id FROM Like l WHERE l.perfilOrigen.id = :id OR l.perfilDestino.id = :id" +
                ") OR m.likeB.id IN (" +
                "SELECT l.id FROM Like l WHERE l.perfilOrigen.id = :id OR l.perfilDestino.id = :id" +
                ")"
            )
            .setParameter("id", perfilId)
            .executeUpdate();

            em.flush(); 

         
            em.createQuery(
                "DELETE FROM Like l WHERE l.perfilOrigen.id = :id OR l.perfilDestino.id = :id"
            )
            .setParameter("id", perfilId)
            .executeUpdate();

            em.flush(); 

          
            em.remove(perfil);

            em.getTransaction().commit();
            return true;

        } else {
            em.getTransaction().rollback();
            return false;
        }

    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        return false;

    } finally {
        em.close();
    }
    }
     //metodoPrueba
    
    @Override
    public Perfil autenticar(String correo, String contrasena) {
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
    
}
