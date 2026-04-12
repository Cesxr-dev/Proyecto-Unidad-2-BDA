/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.Like;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Jesus
 */
public interface ILikeDAO extends IGenericoDAO<Like, Long> {
    
    public List<Like> listar(int limite, EntityManager em);
    
}
