/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

import dominio.Match;
import dominio.Mensaje;
import java.util.List;

public interface IMensajeService extends IGenericoService<Mensaje, Long> {
    
    List<Mensaje> obtenerMensajesPorMatch(Match match);
    
}
