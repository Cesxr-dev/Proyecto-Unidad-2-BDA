/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Like;
import dominio.Perfil;
import java.util.List;

/**
 *
 * @author Jesus
 */
public interface ILikeService extends IGenericoService <Like, Long> {
    
   boolean enviarLike(Perfil origen, Perfil destino, boolean esLike);

    List<Like> obtenerLikesPendientes(Perfil destino);

    List<Like> obtenerLikesEnviados(Perfil origen);

    boolean yaLeDioLike(Perfil origen, Perfil destino);
    
    
    
}
