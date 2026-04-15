/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Match;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author demib
 */
public class MatchDAO implements IMatchDAO {

    @Override
    public void guardar(Match match, EntityManager em) {
        em.persist(match);
    }

    @Override
    public void actualizar(Match match, EntityManager em) {
        em.merge(match);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Match match = buscarPorId(id, em);
        if (match != null) {
            em.remove(match);
        }
    }

    @Override
    public Match buscarPorId(Long id, EntityManager em) {
        return em.find(Match.class, id);
    }

    @Override
    public List<Match> listar(EntityManager em) {
        return em.createQuery("SELECT m FROM Match m ORDER BY m.fechaMatch DESC", Match.class)
                .getResultList();
    }

    @Override
    public Match buscarMatchEntre(Perfil perfilA, Perfil perfilB, EntityManager em) {
        try {
            return em.createQuery(
                    "SELECT m FROM Match m WHERE (m.likeA.perfilOrigen = :pA AND m.likeB.perfilOrigen = :pB) OR (m.likeA.perfilOrigen = :pB AND m.likeB.perfilOrigen = :pA)",
                    Match.class)
                    .setParameter("pA", perfilA)
                    .setParameter("pB", perfilB)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Match> buscarMatchesPorPerfil(Perfil perfil, EntityManager em) {
        return em.createQuery(
                "SELECT m FROM Match m WHERE m.likeA.perfilOrigen = :perfil OR m.likeB.perfilOrigen = :perfil ORDER BY m.fechaMatch DESC",
                Match.class)
                .setParameter("perfil", perfil)
                .getResultList();
    }
    
}
