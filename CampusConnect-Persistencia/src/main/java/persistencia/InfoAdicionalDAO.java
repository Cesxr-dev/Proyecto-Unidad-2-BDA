
package persistencia;

import dominio.InfoAdicional;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class InfoAdicionalDAO implements IInfoAdicionalDAO {

    @Override
    public void guardar(InfoAdicional infoAdicional, EntityManager em) {
        em.persist(infoAdicional);
    }

    @Override
    public void actualizar(InfoAdicional infoAdicional, EntityManager em) {
        em.merge(infoAdicional);
    }

    @Override
    public void eliminar(InfoAdicional entidad, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InfoAdicional buscarPorId(Long id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InfoAdicional> listar(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
