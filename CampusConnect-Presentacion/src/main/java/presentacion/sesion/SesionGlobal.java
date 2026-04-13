package presentacion.sesion;

import dominio.Perfil;
import dominio.SesionActiva;

import dominio.Perfil;
import dominio.SesionActiva;

/**
 * @author Equipo 2 - "Azul"
 */
public class SesionGlobal {
    
    private static SesionActiva sesionActual = null;
    private static Perfil perfilActual = null;
    
 
    public static void iniciarSesion(SesionActiva sesion) {
        if (sesion != null) {
            sesionActual = sesion;
            perfilActual = sesion.getPerfil();
            System.out.println("✓ Sesión iniciada para: " + perfilActual.getNombre());
            System.out.println("  Token: " + sesionActual.getTokenSesion().substring(0, 20) + "...");
        }
    }
    

    public static SesionActiva obtenerSesion() {
        return sesionActual;
    }
    

    public static Perfil obtenerPerfil() {
        return perfilActual;
    }
    

    public static String obtenerToken() {
        return sesionActual != null ? sesionActual.getTokenSesion() : null;
    }
    

    public static Long obtenerPerfilId() {
        return perfilActual != null ? perfilActual.getId() : null;
    }
    
  
    public static String obtenerNombreUsuario() {
        return perfilActual != null ? perfilActual.getNombre() : null;
    }
    
  
    public static String obtenerCorreo() {
        return perfilActual != null ? perfilActual.getCorreoInstitucional() : null;
    }
    

    public static boolean haySesionActiva() {
        return sesionActual != null && 
               sesionActual.getEstado() == SesionActiva.EstadoSesion.ACTIVA;
    }
    

    public static void cerrarSesion() {
        if (sesionActual != null) {
            System.out.println("✓ Sesión cerrada para: " + perfilActual.getNombre());
        }
        sesionActual = null;
        perfilActual = null;
    }

    public static boolean estaAutenticado() {
        return haySesionActiva();
    }
}