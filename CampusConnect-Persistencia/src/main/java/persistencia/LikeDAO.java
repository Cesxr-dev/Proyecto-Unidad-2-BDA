/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Like;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    public List<Like> listar(int limite, EntityManager em) {
       int resultadosMaximos =(limite> 0 && limite<= 100) ? limite : 100;
       TypedQuery<Like> query = em.createQuery("SELECT l FROM Like l ORDER BY l.fecha", Like.class);
       
       query.setMaxResults(resultadosMaximos);
       return query.getResultList();
       
    }

    @Override
    public List<Like> listar(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
 
    
  

   
    
}
