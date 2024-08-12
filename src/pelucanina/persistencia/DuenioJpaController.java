/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pelucanina.persistencia;

import pelucanina.logica.Duenio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Controlador JPA para gestionar la entidad Duenio.
 */
public class DuenioJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public DuenioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PelucaninaPU");  // Asegúrate de que "PelucaninaPU" sea el nombre correcto de tu unidad de persistencia
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear un nuevo Duenio
    public void create(Duenio duenio) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(duenio);
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

    // Método para encontrar un Duenio por ID
    public Duenio findDuenio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duenio.class, id);
        } finally {
            em.close();
        }
    }

    // Método para obtener todos los Duenios
    public List<Duenio> findAllDuenios() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT d FROM Duenio d");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Método para actualizar un Duenio existente
    public void edit(Duenio duenio) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(duenio);
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

    // Método para eliminar un Duenio por ID
    public void destroy(int id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Duenio duenio = em.find(Duenio.class, id);
            if (duenio != null) {
                em.remove(duenio);
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
}
