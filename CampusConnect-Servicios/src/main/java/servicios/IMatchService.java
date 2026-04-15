/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Match;
import dominio.Perfil;
import java.util.List;

/**
 *
 * @author demib
 */
public interface IMatchService extends IGenericoService<Match, Long> {
    
    Match obtenerMatchEntre(Perfil perfilA, Perfil perfilB);
    
    List<Match> obtenerMatchesDePerfil(Perfil perfil);
    
}
