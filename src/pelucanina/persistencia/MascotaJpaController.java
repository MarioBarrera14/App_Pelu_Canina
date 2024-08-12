package pelucanina.persistencia;

import pelucanina.logica.Mascota;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Controlador JPA para gestionar la entidad Mascota.
 */
public class MascotaJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public MascotaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PelucaninaPU");  // Asegúrate de que "PelucaninaPU" sea el nombre correcto de tu unidad de persistencia
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear una nueva Mascota
    public void create(Mascota mascota) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(mascota);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para encontrar una Mascota por ID
    public Mascota findMascota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    // Método para actualizar una Mascota existente
    public void edit(Mascota mascota) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(mascota);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para eliminar una Mascota por ID
public void destroy(int id) {
    EntityManager em = null;
    EntityTransaction tx = null;
    try {
        em = getEntityManager();
        tx = em.getTransaction();
        tx.begin();
        Mascota mascota = em.find(Mascota.class, id);
        if (mascota != null) {
            em.remove(mascota);
        } else {
            throw new Exception("Mascota con ID " + id + " no encontrada.");
        }
        tx.commit();
    } catch (Exception ex) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        ex.printStackTrace();
    } finally {
        if (em != null) {
            em.close();
        }
    }
}

    public List<Mascota> findMascotaEntities() {
    EntityManager em = getEntityManager();
    try {
        TypedQuery<Mascota> query = em.createQuery("SELECT m FROM Mascota m", Mascota.class);
        return query.getResultList();
    } finally {
        em.close();
    }
    }
}
