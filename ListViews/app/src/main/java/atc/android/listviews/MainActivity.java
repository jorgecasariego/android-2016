package atc.android.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //1. Array que asociaremos al adaptador
    private String[] arrayElementos = new String[]{
            "Elemento 1",
            "Elemento 2",
            "Elemento 3",
            "Elemento 4",
            "Elemento 5",
            "Elemento 6",
            "Elemento 7"
    };

    private ListView listaElementosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Creamos un ArrayAdapter
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayElementos);

        //3. Creamos una lista en el layout (XML) y asociamos al adapter
        listaElementosView = (ListView) findViewById(R.id.lista_elementos);
        listaElementosView.setAdapter(adapter);

    }
}
