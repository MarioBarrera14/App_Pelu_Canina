package pelucanina.logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numCliente;

    private String nombre;
    private String raza;
    private String color;
    private String alergico;
    private String atencionEspecial;
    private String observaciones;

    @OneToOne
    private Duenio duenio;

    // Constructor por defecto
    public Mascota() {
    }

    // Constructor con parámetros
    public Mascota(String nombre, String raza, String color, String alergico, String atencionEspecial, String observaciones, Duenio duenio) {
        this.nombre = nombre;
        this.raza = raza;
        this.color = color;
        this.alergico = alergico;
        this.atencionEspecial = atencionEspecial;
        this.observaciones = observaciones;
        this.duenio = duenio;
    }

    // Métodos getter y setter
    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getAtencionEspecial() {
        return atencionEspecial;
    }

    public void setAtencionEspecial(String atencionEspecial) {
        this.atencionEspecial = atencionEspecial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    // Implementación de los métodos getNombreDueño() y getCelDueño()
    public String getNombreDueño() {
        // Verifica si la mascota tiene un dueño asignado y devuelve su nombre
        if (this.duenio != null) {
            return this.duenio.getNombre();
        } else {
            return "Sin dueño"; // O cualquier valor por defecto que prefieras
        }
    }

    public String getCelDueño() {
        // Verifica si la mascota tiene un dueño asignado y devuelve su número de celular
        if (this.duenio != null) {
            return this.duenio.getCelAmo();
        } else {
            return "Sin celular"; // O cualquier valor por defecto que prefieras
        }
    }

    public Object getId() {
            return numCliente;

    }
}
