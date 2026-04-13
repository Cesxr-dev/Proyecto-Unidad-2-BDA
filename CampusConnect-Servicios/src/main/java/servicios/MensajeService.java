/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Match;
import dominio.Mensaje;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import persistencia.IMensajeDAO;
import utils.JPAUtil;

public class MensajeService implements IMensajeService {

    private IMensajeDAO mensajeDAO;

    public MensajeService(IMensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    private void validarMensaje(Mensaje mensaje) {
        if (mensaje == null) {
            throw new IllegalArgumentException("El mensaje no puede ser nulo.");
        }
        if (mensaje.getMatch() == null) {
            throw new IllegalArgumentException("El match es obligatorio.");
        }
        if (mensaje.getPerfilEmisor() == null) {
            throw new IllegalArgumentException("El perfil emisor es obligatorio.");
        }
        if (mensaje.getContenido() == null || mensaje.getContenido().trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido del mensaje no puede estar vacio.");
        }
    }

    @Override
    public void guardar(Mensaje mensaje) {
        validarMensaje(mensaje);
        
        if (mensaje.getFechaHoraEnvio() == null) {
            mensaje.setFechaHoraEnvio(LocalDateTime.now());
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            mensajeDAO.guardar(mensaje, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Mensaje mensaje) {
        validarMensaje(mensaje);
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            mensajeDAO.actualizar(mensaje, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
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
            mensajeDAO.eliminar(id, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Mensaje buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return mensajeDAO.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Mensaje> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return mensajeDAO.listar(em);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Mensaje> obtenerMensajesPorMatch(Match match) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return mensajeDAO.buscarMensajesPorMatch(match, em);
        } finally {
            em.close();
        }
    }
}
