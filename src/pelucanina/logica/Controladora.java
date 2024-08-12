package pelucanina.logica;

import pelucanina.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {

    private ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    // Método para guardar una nueva mascota y su dueño
    public void guardarMascota(String nombreMascota, String raza, String color, String alergico, String atencionEspecial, String observaciones, String nombreDuenio, String celDuenio) {
        Duenio duenio = new Duenio(nombreDuenio, celDuenio);
        Mascota mascota = new Mascota(nombreMascota, raza, color, alergico, atencionEspecial, observaciones, duenio);

        controlPersis.guardar(duenio, mascota);
    }

    // Método para traer todas las mascotas
    public List<Mascota> traerMascotas() {
        return controlPersis.traerMascotas();
    }

    // Método para eliminar una mascota y posiblemente su dueño
    public void eliminarMascota(int idMascota) throws Exception {
        try {
            controlPersis.eliminarMascota(idMascota);
        } catch (Exception e) {
            throw new Exception("Ocurrió un error al eliminar la mascota: " + e.getMessage(), e);
        }
    }
  public void actualizarMascota(int idMascota, String nombreMascota, String raza, String color, String alergico, String atencionEspecial, String observaciones, String nombreDuenio, String celDuenio) throws Exception {
    // Obtener la mascota existente desde la persistencia
    Mascota mascota = controlPersis.traerMascota(idMascota);
    if (mascota != null) {
        // Actualizar los campos de la mascota
        mascota.setNombre(nombreMascota);
        mascota.setRaza(raza);
        mascota.setColor(color);
        mascota.setAlergico(alergico);
        mascota.setAtencionEspecial(atencionEspecial);
        mascota.setObservaciones(observaciones);
        
        // Actualizar los datos del dueño
        Duenio duenio = mascota.getDuenio();
        duenio.setNombre(nombreDuenio);
        duenio.setCelAmo(celDuenio); // Cambiar a setCelAmo

        // Guardar los cambios en la base de datos
        controlPersis.actualizarMascota(mascota);
        controlPersis.actualizarDuenio(duenio);
    } else {
        throw new Exception("Mascota con ID " + idMascota + " no encontrada.");
    }
}


}
