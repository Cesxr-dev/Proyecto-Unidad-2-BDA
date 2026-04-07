
package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT = "CampusConnectPU";
    private static EntityManagerFactory emf;

    private JPAUtil() {
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emf.createEntityManager();
    }
    
}
