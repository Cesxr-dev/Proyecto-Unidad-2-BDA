/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.Match;
import dominio.Perfil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Jesus
 */
public interface IMatchDAO extends IGenericoDAO<Match, Long> {
    
    Match buscarMatchEntre(Perfil perfilA, Perfil perfilB, EntityManager em);
    
    List<Match> buscarMatchesPorPerfil(Perfil perfil, EntityManager em);
    
}
