package servicios;

import dominio.SesionActiva;
import java.util.List;

/**
 * @author Equipo 2 - "Azul"
 */
public interface ISesionActivaService extends IGenericoService<SesionActiva, Long> {
    
 
    SesionActiva crearSesion(Long perfilId, String tipoDispositivo, String direccionIp);
    

    SesionActiva validarSesion(String token);
    
  
    List<SesionActiva> obtenerSesionesActivas(Long perfilId);
    
    
    void cerrarSesion(Long sesionId);
  
    void cerrarOtrasSesiones(Long perfilId, String tokenActual);
    

    void manejarLimiteSesiones(Long perfilId);
}