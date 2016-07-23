package atc.android.fragmentestatico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
        Pasos para ejemplo de fragment estatico
        1. Crear Fragment utilizando las opciones de Android Studio
        2. Asociamos el fragment con el layout en onCreateView
        3. Necesitamos insertar ahora ese fragment creado en la actividad  por XML
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
