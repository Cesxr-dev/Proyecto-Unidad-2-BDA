
package persistencia;

import dominio.Perfil;
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
    public Perfil buscarPorId(Long id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Perfil> listar(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
