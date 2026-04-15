
package persistencia;

import dominio.Perfil;
import dominio.Sesion;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class PerfilDAO implements IPerfilDAO {

    @Override
    public void guardar(Perfil perfil, EntityManager em) {
        em.persist(perfil);
    }

    @Override
    public void actualizar(Perfil perfil, EntityManager em) {
        em.merge(perfil);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Perfil perfil = em.find(Perfil.class, id);
        if (perfil != null) {
            em.remove(perfil);
        }
    }

    @Override
    public Perfil buscarPorCorreo(String correo, EntityManager em) {
        try {
            correo = correo.trim().toLowerCase();
        
            Perfil resultado = em.createQuery(
                "SELECT p FROM Perfil p WHERE LOWER(p.correoInstitucional) = :correo", 
                Perfil.class)
            .setParameter("correo", correo)
            .getSingleResult();
            
            return resultado;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Perfil buscarPorId(Long id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    //Excluir al perfil logueado de la lista de perfiles a explorar 
    @Override
    public List<Perfil> listar(EntityManager em) {
        Long miId = Sesion.getPerfilActivo().getId();
        return em.createQuery(
            "SELECT DISTINCT p FROM Perfil p LEFT JOIN FETCH p.perfilInfoAdicional " +
            "WHERE p.id <> :miId" +
            " AND p.id NOT IN (" +
            "   SELECT l.perfilDestino.id FROM Like l WHERE l.perfilOrigen.id = :miId" +
            ")",
            Perfil.class)
                 .setParameter("miId", miId)
                 .getResultList();
    }
    
    
    
}
