package atc.android.listapersonalizada;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jorgecasariego on 22/7/16.
 */
public class SerieAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Series> listaSeries;

    //1. Crear constructor el cual recibirá la actividad y la lista de items de la lista
    public SerieAdapter(Activity activity, ArrayList<Series> listaSeries) {
        this.activity = activity;
        this.listaSeries = listaSeries;
    }

    // Cantidad de series de nuestra lista
    @Override
    public int getCount() {
        return listaSeries.size();
    }

    // Obtiene el item de la lista de series
    @Override
    public Object getItem(int i) {
        return listaSeries.get(i);
    }

    // Obtiene el id de los items
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vista = view;

        // Creamos la vista si es null
        if(vista == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.list_item, null);
        }

        // Obtenemos la serie a partir de la posicion recibida como parametro
        Series serie = listaSeries.get(position);

        // Obtenemos las vistas del layout de nuestro item
        TextView nombre = (TextView) vista.findViewById(R.id.titulo);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imagen);

        // Seteamos los valores
        nombre.setText(serie.getNombre());
        imagen.setImageDrawable(ContextCompat.getDrawable(activity, serie.getImagen()));

        return vista;
    }
}
