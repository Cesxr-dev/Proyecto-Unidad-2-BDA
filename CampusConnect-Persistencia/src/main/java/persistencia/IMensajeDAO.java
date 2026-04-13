/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.Match;
import dominio.Mensaje;
import jakarta.persistence.EntityManager;
import java.util.List;

public interface IMensajeDAO extends IGenericoDAO<Mensaje, Long> {
    
    List<Mensaje> buscarMensajesPorMatch(Match match, EntityManager em);
    
}
