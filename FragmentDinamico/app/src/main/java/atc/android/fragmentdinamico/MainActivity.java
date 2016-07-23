package atc.android.fragmentdinamico;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Pasos para ejemplo de fragment dinamico
 * 1. Crear Fragment utilizando las opciones de Android Studio
 * 2. Asociamos el fragment con el layout en onCreateView
 * 3. Obtenemos una referencia al FragmentManager en la actividad
 * 4. Comenzamos una transaccion
 * 5. Creamos un fragment para insertar luego en la actividad
 * 6. Añadimos un fragment a traves del fragment transaction
 * 7. Hacemos el commit del fragment
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mFragment1View;
    private Button mFragment2View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment1View = (Button) findViewById(R.id.mostrar_fragment1);
        mFragment1View.setOnClickListener(this);

        mFragment2View = (Button) findViewById(R.id.mostrar_fragment2);
        mFragment2View.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()){
            case R.id.mostrar_fragment1:
                fragment = new Fragment1();
                break;
            case R.id.mostrar_fragment2:
                fragment = new Fragment2();
                break;
        }

        //Paso 1: Crear un FragmentManager
        FragmentManager manager = getSupportFragmentManager();

        //Paso 2: Crear un FragmentTransaction
        FragmentTransaction transaction = manager.beginTransaction();

        //Paso 3: Reemplazamos o añadimos un fragment
        //transaction.replace(R.id.contenedor_fragments, fragment);

        //Paso 3.1: Añadimos en vez de reemplazar y vemos que pasa
        transaction.add(R.id.contenedor_fragments, fragment);

        //Paso 4: Hacemos un commit
        transaction.commit();
    }
}
