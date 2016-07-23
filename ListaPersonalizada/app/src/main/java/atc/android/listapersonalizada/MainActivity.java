package atc.android.listapersonalizada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaSeries;
    private SerieAdapter adapter;
    private ArrayList<Series> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociamos lista con su layout
        listaSeries = (ListView) findViewById(R.id.lista);

        //Inicializamos el arraylist de series
        series = new ArrayList<Series>();

        // Creamos series al azar
        Series serie1 = new Series("El Patron del Mal", R.drawable.serie1);
        Series serie2 = new Series("The walking Dead", R.drawable.serie2);
        Series serie3 = new Series("Spartacus", R.drawable.serie3);
        Series serie4 = new Series("Flash", R.drawable.serie4);
        Series serie5 = new Series("Breaking Bad", R.drawable.serie5);
        Series serie6 = new Series("The Big Bang Theory ", R.drawable.serie6);
        Series serie7 = new Series("Suits ", R.drawable.serie7);
        Series serie8 = new Series("Marco Polo ", R.drawable.serie8);

        // Agregamos al arrayList de series
        series.add(serie1);
        series.add(serie2);
        series.add(serie3);
        series.add(serie4);
        series.add(serie5);
        series.add(serie6);
        series.add(serie7);
        series.add(serie8);

        // Inicializamos el adapter
        adapter = new SerieAdapter(this, series);

        // Seteamos el adapter
        listaSeries.setAdapter(adapter);
        listaSeries.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainActivity.this, series.get(i).getNombre() +" clickeada", Toast.LENGTH_SHORT).show();

        // Agregamos una pelicula mas a la lista
        Series serie4 = new Series("Flash", R.drawable.serie4);
        series.add(serie4);
        adapter.notifyDataSetChanged();
    }
}
