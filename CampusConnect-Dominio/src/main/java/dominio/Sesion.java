/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;


/**
 *
 * @author demib
 */
public class Sesion {
    private static Perfil perfilActivo;
    
    //Devuelve el perfil que está logueado actualmente. Su usara cuando se necesite saber quien es el usuario en cualquier pantalla.
    public static Perfil getPerfilActivo() {
        return perfilActivo;
    }

    // Guarda el perfil cuando inicia sesión exitosamente. Solo se llama una vez al autenticar.
    public static void setPerfilActivo(Perfil perfil) {
        perfilActivo = perfil;
    }

    //Borra el perfil guardado al cerrar sesion, dejandolo en null. Se usara en un btnn de logout para regresar al login limpio.
    public static void cerrarSesion() {
        perfilActivo = null;
    }
}
