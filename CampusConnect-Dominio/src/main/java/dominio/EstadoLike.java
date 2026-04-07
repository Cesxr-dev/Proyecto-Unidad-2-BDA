
package dominio;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public enum EstadoLike {
    PENDIENTE,   // ← el like fue enviado pero no respondido
    ACEPTADO,    // ← generará un match
    RECHAZADO    // ← ignorado o rechazado
}
