package pelucanina.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Clase que representa al dueño de una mascota en el sistema.
 */
@Entity
public class Duenio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int idAmo;
    private String nombre;
    private String celAmo;
    
    @OneToMany(mappedBy = "duenio")  // Relación de uno a muchos con la entidad Mascota
    private List<Mascota> mascotas;
    
    // Constructor por defecto
    public Duenio() {
    }

    // Constructor con parámetros
    public Duenio(String nombre, String celAmo) {
        this.nombre = nombre;
        this.celAmo = celAmo;
    }

    // Métodos getter y setter

    public int getIdAmo() {
        return idAmo;
    }

    public void setIdAmo(int idAmo) {
        this.idAmo = idAmo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelAmo() {
        return celAmo;
    }

    public void setCelAmo(String celAmo) {
        this.celAmo = celAmo;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
