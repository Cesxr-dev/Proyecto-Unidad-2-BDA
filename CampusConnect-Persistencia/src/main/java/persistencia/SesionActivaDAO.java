package persistencia;

import dominio.SesionActiva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Equipo 2 - "Azul"
 */
public class SesionActivaDAO implements ISesionActivaDAO {

    @Override
    public void guardar(SesionActiva sesion, EntityManager em) {
        em.persist(sesion);
    }

    @Override
    public void actualizar(SesionActiva sesion, EntityManager em) {
        em.merge(sesion);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        SesionActiva sesion = em.find(SesionActiva.class, id);
        if (sesion != null) {
            em.remove(sesion);
        }
    }

    @Override
    public SesionActiva buscarPorToken(String token, EntityManager em) {
        try {
            return em.createQuery(
                "SELECT s FROM SesionActiva s WHERE s.tokenSesion = :token", 
                SesionActiva.class)
                .setParameter("token", token)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Error al buscar sesión por token: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<SesionActiva> obtenerSesionesActivas(Long perfilId, EntityManager em) {
        try {
            return em.createQuery(
                "SELECT s FROM SesionActiva s WHERE s.perfil.id = :perfilId " +
                "AND s.estado = 'ACTIVA' ORDER BY s.fechaInicio DESC", 
                SesionActiva.class)
                .setParameter("perfilId", perfilId)
                .getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener sesiones activas: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public long contarSesionesActivas(Long perfilId, EntityManager em) {
        try {
            return em.createQuery(
                "SELECT COUNT(s) FROM SesionActiva s WHERE s.perfil.id = :perfilId " +
                "AND s.estado = 'ACTIVA'", 
                Long.class)
                .setParameter("perfilId", perfilId)
                .getSingleResult();
        } catch (Exception e) {
            System.err.println("Error al contar sesiones activas: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public void cerrarSesion(Long sesionId, EntityManager em) {
        try {
            SesionActiva sesion = em.find(SesionActiva.class, sesionId);
            if (sesion != null) {
                sesion.setEstado(SesionActiva.EstadoSesion.CERRADA);
                sesion.setFechaCierre(LocalDateTime.now());
                em.merge(sesion);
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar sesión: " + e.getMessage());
        }
    }

    @Override
    public SesionActiva obtenerSesionMasAntigua(Long perfilId, EntityManager em) {
        try {
            List<SesionActiva> sesiones = em.createQuery(
                "SELECT s FROM SesionActiva s WHERE s.perfil.id = :perfilId " +
                "AND s.estado = 'ACTIVA' ORDER BY s.fechaInicio ASC", 
                SesionActiva.class)
                .setParameter("perfilId", perfilId)
                .setMaxResults(1)
                .getResultList();
            
            return sesiones.isEmpty() ? null : sesiones.get(0);
        } catch (Exception e) {
            System.err.println("Error al obtener sesión más antigua: " + e.getMessage());
            return null;
        }
    }

    @Override
    public SesionActiva buscarPorId(Long id, EntityManager em) {
        return em.find(SesionActiva.class, id);
    }

    @Override
    public List<SesionActiva> listar(EntityManager em) {
        try {
            return em.createQuery(
                "SELECT s FROM SesionActiva s ORDER BY s.fechaInicio DESC", 
                SesionActiva.class)
                .getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar sesiones: " + e.getMessage());
            return List.of();
        }
    }
}