/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.EstadoLike;
import dominio.Like;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class LikeDAO implements ILikeDAO {

    @Override
    public void guardar(Like like, EntityManager em) {
        em.persist(like);

    }

    @Override
    public void actualizar(Like like, EntityManager em) {
        em.merge(like);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Like like = buscarPorId(id, em);
        if (like != null) {
            em.remove(like);
        }
    }

    @Override
    public Like buscarPorId(Long id, EntityManager em) {
        return em.find(Like.class, id);
    }

    @Override
    public List<Like> listar(EntityManager em) {
        return em.createQuery("SELECT l FROM Like l ORDER BY l.fecha DESC", Like.class)
                .getResultList();
    }

    /**
     * Metodo igual que listar pero con tope de resultados. Necesario para no
     * traer toda la tabla de golpe cuando hay muchos registros
     *
     * @param limite
     * @param em
     * @return
     */
    @Override
    public List<Like> listar(int limite, EntityManager em) {
        int max = (limite > 0 && limite <= 100) ? limite : 100;
        return em.createQuery("SELECT l FROM Like l ORDER BY l.fecha DESC", Like.class)
                .setMaxResults(max)
                .getResultList();
    }

    /**
     * Metodo que busca si ya existe un like donde exactamente perfil origen le
     * dio like a perfil destino. Sirve para antes de guardar un like nuevo para
     * evitar duplicados, y para detectar si hay match (revisar si el otro ya te
     * había dado like a ti).
     *
     * @param origen
     * @param destino
     * @param em
     * @return
     */
    @Override
    public Like buscarLikeEntre(Perfil origen, Perfil destino, EntityManager em) {
        try {
            return em.createQuery(
                    "SELECT l FROM Like l WHERE l.perfilOrigen = :origen AND l.perfilDestino = :destino",
                    Like.class)
                    .setParameter("origen", origen)
                    .setParameter("destino", destino)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Regresa todos los likes que recibió un perfil filtrados por estado.
     *
     */
    @Override
    public List<Like> buscarLikesRecibidos(Perfil destino, EstadoLike estado, EntityManager em) {
        return em.createQuery(
                "SELECT l FROM Like l WHERE l.perfilDestino = :destino AND l.estadoLike = :estado ORDER BY l.fecha DESC",
                Like.class)
                .setParameter("destino", destino)
                .setParameter("estado", estado)
                .getResultList();
    }

    /**
     * Regresa todos los likles que es perfil ha enviado, sin importar el
     * estado. Sirve para saber a quiénes ya evaluó ese Perfil.
     * 
     */
    @Override
    public List<Like> buscarLikesEnviados(Perfil origen, EntityManager em) {
        return em.createQuery(
                "SELECT l FROM Like l WHERE l.perfilOrigen = :origen ORDER BY l.fecha DESC",
                Like.class)
                .setParameter("origen", origen)
                .getResultList();
    }
    
    
    public void eliminarPorPerfil(Long idPerfil, EntityManager em) {
        em.createQuery(
                "DELETE FROM Like l WHERE l.perfilOrigen.id = :id OR l.perfilDestino.id = :id"
        )
                .setParameter("id", idPerfil)
                .executeUpdate();
    }

}
