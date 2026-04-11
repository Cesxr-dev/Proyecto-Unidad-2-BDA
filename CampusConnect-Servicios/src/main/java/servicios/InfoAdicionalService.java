
package servicios;

import dominio.InfoAdicional;
import jakarta.persistence.EntityManager;
import java.util.List;
import persistencia.IInfoAdicionalDAO;
import persistencia.InfoAdicionalDAO;
import utils.JPAUtil;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class InfoAdicionalService implements IInfoAdicionalService {
    
    private IInfoAdicionalDAO infoAdicionalDao;
    
    public InfoAdicionalService() {
        this.infoAdicionalDao = new InfoAdicionalDAO();
    }

    @Override
    public void guardar(InfoAdicional infoAdicional) {
        validarInfoAdicional(infoAdicional);
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            infoAdicionalDao.guardar(infoAdicional, em);
            em.getTransaction().commit();   
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.print("Error al guardar la informacion adicional.");
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(InfoAdicional infoAdicional) {
        validarInfoAdicional(infoAdicional);
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            infoAdicionalDao.actualizar(infoAdicional, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.print("Error al actualizar la información adicional.");
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(InfoAdicional entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InfoAdicional buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InfoAdicional> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void validarInfoAdicional(InfoAdicional infoAdicional) {
        if (infoAdicional == null) {
            throw new IllegalArgumentException("La informacion adicional no puede ser nula.");
        }
        
        if (infoAdicional.getNombre() == null || infoAdicional.getNombre().trim().isBlank()) {
            throw new IllegalArgumentException("El nombre del interes/hobbie/gusto es obligatorio.");
        }
    }    
    
}
