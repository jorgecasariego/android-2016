package atc.android.loginsharepreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Paso 1: Creamos las claves para guardar el usuario y el password del usuario
    public static final String ANDROID_LOGIN = "Login de Android";
    public static final String NOMBRE = "nombre";
    public static final String PASSWORD = "password";

    // Paso 2: Declaramos una variable del tipo SharedPreferences y un editor para manipular los datos
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;

    private EditText nombre;
    private EditText password;
    private Button registrarse;
    private Button iniciar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nombre);
        password = (EditText) findViewById(R.id.password);

        iniciar = (Button) findViewById(R.id.iniciar);
        iniciar.setOnClickListener(this);

        iniciar = (Button) findViewById(R.id.registrarse);
        iniciar.setOnClickListener(this);


        // Paso 3: Obtenemos una instancia  del objeto SharedPreferences pasandole la clave
        preferencias = getSharedPreferences(ANDROID_LOGIN, Context.MODE_PRIVATE);
        editor = preferencias.edit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrarse:
                guardarDatos();
                break;

            case R.id.iniciar:
                iniciarSesion();
                break;
        }
    }

    // Paso 4: Guardamos los datos
    private void guardarDatos() {
        editor.putString(NOMBRE, nombre.getText().toString().trim());
        editor.putString(PASSWORD, password.getText().toString().trim());
        editor.commit();

        limpiarFormulario();

        Toast.makeText(MainActivity.this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();

    }

    // Paso 5: Obtenemos los datos guardados y comparamos con lo que ingresa el usuario
    private void iniciarSesion() {
        String nombreGuardado = preferencias.getString(NOMBRE, "");
        String passwordGuardado = preferencias.getString(PASSWORD, "");

        if(nombreGuardado.equals(nombre.getText().toString()) && passwordGuardado.equals(password.getText().toString())){
            Intent i = new Intent(this, PanelControlActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(MainActivity.this, "Usuario y/o Password no coinciden", Toast.LENGTH_SHORT).show();
        }

        limpiarFormulario();

    }



    private void limpiarFormulario() {
        nombre.setText("");
        password.setText("");
    }
}
