package pelucanina.persistencia;

import java.util.List;
import pelucanina.logica.Duenio;
import pelucanina.logica.Mascota;

public class ControladoraPersistencia {

    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascotaJpa = new MascotaJpaController();

    // Método para guardar una nueva mascota y su dueño
    public void guardar(Duenio duenio, Mascota mascota) {
        duenioJpa.create(duenio);
        mascotaJpa.create(mascota);
    }

    // Método para obtener todas las mascotas
    public List<Mascota> traerMascotas() {
        try {
            return mascotaJpa.findMascotaEntities();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para eliminar una mascota
    public void eliminarMascota(int numCliente) throws Exception {
        try {
            System.out.println("Intentando eliminar mascota con NUMCLIENTE: " + numCliente);
            
            // Imprimir todos los IDs de mascotas en la base de datos
            List<Mascota> mascotas = mascotaJpa.findMascotaEntities();
            System.out.println("NUMCLIENTE actuales en la base de datos:");
            for (Mascota m : mascotas) {
                System.out.println("NUMCLIENTE: " + m.getNumCliente());
            }
            
            Mascota mascota = mascotaJpa.findMascota(numCliente);
            if (mascota != null) {
                // Eliminar la mascota
                mascotaJpa.destroy(numCliente);
                
                // Eliminar el dueño si es necesario
                if (mascota.getDuenio() != null) {
                    duenioJpa.destroy(mascota.getDuenio().getIdAmo());
                }
            } else {
                throw new Exception("Mascota con NUMCLIENTE " + numCliente + " no encontrada.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // Método para actualizar una mascota
    public void actualizarMascota(Mascota mascota) throws Exception {
        try {
            mascotaJpa.edit(mascota);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la mascota: " + e.getMessage(), e);
        }
    }

    // Método para actualizar un dueño
    public void actualizarDuenio(Duenio duenio) throws Exception {
        try {
            duenioJpa.edit(duenio);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el dueño: " + e.getMessage(), e);
        }
    }

    public Mascota traerMascota(int idMascota) {
            return mascotaJpa.findMascota(idMascota);
    }
}
