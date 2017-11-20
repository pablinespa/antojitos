package ucb.bo.edu.antojitosapp;

/**
 * Created by ASUS on 19/11/2017.
 */

public class MenuRestaurante {
    private String nombre;
    private String precio;

    public MenuRestaurante(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String precio) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
