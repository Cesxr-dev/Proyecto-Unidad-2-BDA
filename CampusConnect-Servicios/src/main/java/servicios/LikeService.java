/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.EstadoLike;
import dominio.Like;
import dominio.Match;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import persistencia.ILikeDAO;
import persistencia.LikeDAO;
import utils.JPAUtil;

/**
 *
 * @author Jesus
 */
public class LikeService implements ILikeService {

    private ILikeDAO likeDAO;

    public LikeService() {
        this.likeDAO = new LikeDAO();
    }

    private void validarLike(Like like) {
        if (like == null) {
            throw new IllegalArgumentException("El like no puede ser nulo.");
        }
        if (like.getPerfilOrigen() == null) {
            throw new IllegalArgumentException("El perfil origen es obligatorio.");
        }
        if (like.getPerfilDestino() == null) {
            throw new IllegalArgumentException("El perfil destino es obligatorio.");
        }
        if (like.getPerfilOrigen().equals(like.getPerfilDestino())) {
            throw new IllegalArgumentException("Un perfil no puede darse like a sí mismo.");
        }
    }

    private void validarPerfiles(Perfil origen, Perfil destino) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Los perfiles no pueden ser nulos.");
        }
        if (origen.getId().equals(destino.getId())) {
            throw new IllegalArgumentException("Un perfil no puede interactuar consigo mismo.");
        }
    }

    @Override
    public void guardar(Like like) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            likeDAO.guardar(like, em);
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
    public void actualizar(Like like) {
        validarLike(like);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            likeDAO.actualizar(like, em);
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
            likeDAO.eliminar(id, em);
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
    public Like buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return likeDAO.buscarPorId(id, em);

        } finally {
            em.close();
        }
    }

    @Override
    public List<Like> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return likeDAO.listar(em);
        } finally {
            em.close();
        }

    }

    /*
    Metodo que recibe quien da el like, a quien se lo dio, y si es aceptado ("Hay Match"). Valida que el like no sea a si mismo, que no duplique. Regresa true si hubo match.
     */
    @Override
    public boolean enviarLike(Perfil origen, Perfil destino, boolean esLike) {
        if (origen.getId().equals(destino.getId())) {
            throw new IllegalArgumentException("Un perfil no puede darse like a sí mismo.");
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            if (likeDAO.buscarLikeEntre(origen, destino, em) != null) {
                throw new IllegalStateException("Ya existe un like de este perfil hacia el destino.");
            }

            Like nuevoLike = new Like();
            nuevoLike.setPerfilOrigen(origen);
            nuevoLike.setPerfilDestino(destino);
            nuevoLike.setFecha(LocalDate.now());
            nuevoLike.setEstadoLike(esLike ? EstadoLike.ACEPTADO : EstadoLike.RECHAZADO);
            likeDAO.guardar(nuevoLike, em);

            boolean hayMatch = false;
            if (esLike) {
                Like likeInverso = likeDAO.buscarLikeEntre(destino, origen, em);
                if (likeInverso != null && likeInverso.getEstadoLike() == EstadoLike.ACEPTADO) {
                    Match match = new Match();
                    match.setFechaMatch(LocalDate.now());
                    match.setLikeA(nuevoLike);
                    match.setLikeB(likeInverso);
                    em.persist(match);
                    hayMatch = true;
                }
            }

            em.getTransaction().commit();
            return hayMatch;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al enviar like: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    /*
    Metodo que consulta a likeDAO , los likes recibidos con estado pendiente
     */
    @Override
    public List<Like> obtenerLikesPendientes(Perfil destino) {
        if (destino == null) {
            throw new IllegalArgumentException("El perfil destino no puede ser nulo.");
        }
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return likeDAO.buscarLikesRecibidos(destino, EstadoLike.PENDIENTE, em);
        } finally {
            em.close();
        }
    }

    /*
    Metodo que trae todos los likes que ese perfil ya envió.
    */
    @Override
    public List<Like> obtenerLikesEnviados(Perfil origen) {
        if (origen == null) {
            throw new IllegalArgumentException("El perfil origen no puede ser nulo.");
        }
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return likeDAO.buscarLikesEnviados(origen, em);
        } finally {
            em.close();
        }
    }

    /*
    Metodo que regresa tru o false para verificar si ya dio like a ese perfil
     */
    @Override
    public boolean yaLeDioLike(Perfil origen, Perfil destino) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return likeDAO.buscarLikeEntre(origen, destino, em) != null;
        } finally {
            em.close();
        }
    }

}
