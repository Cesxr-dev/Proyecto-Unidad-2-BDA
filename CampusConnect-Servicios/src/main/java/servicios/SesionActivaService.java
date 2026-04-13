package servicios;

import dominio.Perfil;
import dominio.SesionActiva;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import persistencia.ISesionActivaDAO;
import persistencia.SesionActivaDAO;
import persistencia.IPerfilDAO;
import persistencia.PerfilDAO;
import utils.JPAUtil;

/**
 * @author Equipo 2 - "Azul"
 * Servicio para gestionar sesiones activas
 */
public class SesionActivaService implements ISesionActivaService {
    
    private ISesionActivaDAO sesionDAO;
    private IPerfilDAO perfilDAO;
    private static final int MAX_SESIONES_POR_USUARIO = 1;
    
    public SesionActivaService() {
        this.sesionDAO = new SesionActivaDAO();
        this.perfilDAO = new PerfilDAO();
    }
    
    @Override
    public SesionActiva crearSesion(Long perfilId, String tipoDispositivo, String direccionIp) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            
            // Obtener perfil
            Perfil perfil = perfilDAO.buscarPorId(perfilId, em);
            if (perfil == null) {
                throw new IllegalArgumentException("Perfil no encontrado");
            }
            
            // Verificar límite de sesiones
            manejarLimiteSesionesBD(perfilId, em);
            
            // Generar token único
            String token = generarToken();
            
            // Crear sesión
            SesionActiva sesion = new SesionActiva();
            sesion.setPerfil(perfil);
            if (perfil.getSesionesActivas() != null) {
                perfil.getSesionesActivas().add(sesion);
            }
            sesion.setTokenSesion(token);
            sesion.setTipoDispositivo(tipoDispositivo);
            sesion.setDireccionIp(direccionIp);
            sesion.setFechaInicio(LocalDateTime.now());
            sesion.setFechaUltimaActividad(LocalDateTime.now());
            sesion.setEstado(SesionActiva.EstadoSesion.ACTIVA);
            
            sesionDAO.guardar(sesion, em);
            em.getTransaction().commit();
            
            System.out.println(" Sesión creada exitosamente para perfil: " + perfilId);
            return sesion;
        } catch (Exception e) {
    if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
    }
    e.printStackTrace(); 
    throw new RuntimeException("No se pudo crear la sesión", e);
}finally {
            em.close();
        }
    }
    
@Override
public SesionActiva validarSesion(String token) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
        SesionActiva sesion = sesionDAO.buscarPorToken(token, em);
        
        if (sesion == null) return null;

        em.refresh(sesion);

        if (sesion.getEstado() != SesionActiva.EstadoSesion.ACTIVA) {
            return null;
        }
        
        em.getTransaction().begin();
        sesion.setFechaUltimaActividad(LocalDateTime.now());
        sesionDAO.actualizar(sesion, em);
        em.getTransaction().commit();
        
        return sesion;
    } catch (Exception e) {
        return null;
    } finally {
        em.close();
    }
}
    
    @Override
    public List<SesionActiva> obtenerSesionesActivas(Long perfilId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return sesionDAO.obtenerSesionesActivas(perfilId, em);
        } finally {
            em.close();
        }
    }
    
    @Override
    public void cerrarSesion(Long sesionId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            sesionDAO.cerrarSesion(sesionId, em);
            em.getTransaction().commit();
            System.out.println(" Sesión " + sesionId + " cerrada");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(" Error al cerrar sesión: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    @Override
    public void cerrarOtrasSesiones(Long perfilId, String tokenActual) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            
            List<SesionActiva> sesiones = sesionDAO.obtenerSesionesActivas(perfilId, em);
            
            for (SesionActiva sesion : sesiones) {
                if (!sesion.getTokenSesion().equals(tokenActual)) {
                    sesion.setEstado(SesionActiva.EstadoSesion.CERRADA);
                    sesion.setFechaCierre(LocalDateTime.now());
                    sesionDAO.actualizar(sesion, em);
                }
            }
            
            em.getTransaction().commit();
            System.out.println(" Otras sesiones cerradas para perfil: " + perfilId);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(" Error al cerrar otras sesiones: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    @Override
    public void manejarLimiteSesiones(Long perfilId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            manejarLimiteSesionesBD(perfilId, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(" Error al manejar límite de sesiones: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    /**
     * Método privado para manejar límite de sesiones (usa EM existente)
     */
    private void manejarLimiteSesionesBD(Long perfilId, EntityManager em) {
        long sesionesActivas = sesionDAO.contarSesionesActivas(perfilId, em);
        
        System.out.println("Sesiones activas actuales: " + sesionesActivas);
        
        if (sesionesActivas >= MAX_SESIONES_POR_USUARIO) {
            // Cerrar la sesión más antigua
            SesionActiva sesionAntigua = sesionDAO.obtenerSesionMasAntigua(perfilId, em);
            if (sesionAntigua != null) {
                sesionAntigua.setEstado(SesionActiva.EstadoSesion.CERRADA);
                sesionAntigua.setFechaCierre(LocalDateTime.now());
                sesionDAO.actualizar(sesionAntigua, em);
                System.out.println(" Sesión antigua cerrada automáticamente para perfil: " + perfilId);
            }
        }
    }
    
    /**
     * Genera un token único para la sesión
     */
    private String generarToken() {
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }
    
    // Métodos no implementados del interfaz genérico
    @Override
    public void guardar(SesionActiva sesion) {
        throw new UnsupportedOperationException("Usar crearSesion() en su lugar");
    }
    
    @Override
    public void actualizar(SesionActiva sesion) {
        throw new UnsupportedOperationException("No soportado directamente");
    }
    
    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Usar cerrarSesion() en su lugar");
    }
    
    @Override
    public SesionActiva buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<SesionActiva> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}