/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Match;
import dominio.Mensaje;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MensajeDAO implements IMensajeDAO {

    @Override
    public void guardar(Mensaje mensaje, EntityManager em) {
        em.persist(mensaje);
    }

    @Override
    public void actualizar(Mensaje mensaje, EntityManager em) {
        em.merge(mensaje);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Mensaje mensaje = buscarPorId(id, em);
        if (mensaje != null) {
            em.remove(mensaje);
        }
    }

    @Override
    public Mensaje buscarPorId(Long id, EntityManager em) {
        return em.find(Mensaje.class, id);
    }

    @Override
    public List<Mensaje> listar(EntityManager em) {
        return em.createQuery("SELECT m FROM Mensaje m ORDER BY m.fechaHoraEnvio ASC", Mensaje.class)
                .getResultList();
    }

    @Override
    public List<Mensaje> buscarMensajesPorMatch(Match match, EntityManager em) {
        return em.createQuery(
                "SELECT m FROM Mensaje m WHERE m.match = :match ORDER BY m.fechaHoraEnvio ASC",
                Mensaje.class)
                .setParameter("match", match)
                .getResultList();
    }
    
}
