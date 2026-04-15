/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Match;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.util.List;
import persistencia.IMatchDAO;
import utils.JPAUtil;

/**
 *
 * @author demib
 */
public class MatchService implements IMatchService {

    private IMatchDAO matchDAO;

    public MatchService(IMatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    @Override
    public void guardar(Match match) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            matchDAO.guardar(match, em);
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
    public void actualizar(Match match) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            matchDAO.actualizar(match, em);
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
            matchDAO.eliminar(id, em);
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
    public Match buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Match> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.listar(em);
        } finally {
            em.close();
        }
    }

    @Override
    public Match obtenerMatchEntre(Perfil perfilA, Perfil perfilB) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.buscarMatchEntre(perfilA, perfilB, em);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Match> obtenerMatchesDePerfil(Perfil perfil) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.buscarMatchesPorPerfil(perfil, em);
        } finally {
            em.close();
        }
    }
}
