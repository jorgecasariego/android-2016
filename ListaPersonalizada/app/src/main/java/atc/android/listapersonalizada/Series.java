package atc.android.listapersonalizada;

import android.graphics.drawable.Drawable;

/**
 * Created by jorgecasariego on 22/7/16.
 */
public class Series {
    private String nombre;
    private int imagenId;

    public Series(String nombre, int imagenId) {
        this.nombre = nombre;
        this.imagenId = imagenId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagenId;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(int imagenId) {
        this.imagenId = imagenId;
    }
}
