package atc.android.volleyproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    // Paso 1: Definición de vistas y asociar con sus layouts
    private EditText mUsuario;
    private EditText mEmail;
    private EditText mPassword;

    private Button registrarse;
    private Button login;

    // Paso 3: URLS utilizadas para el registro y login
    public static final String REGISTRO_URL = "http://android.enterprisesolutions.com.py/volleyRegister.php";
    public static final String LOGIN_URL = "http://android.enterprisesolutions.com.py/login.php";

    // Paso 4: Claves que espera el servidor
    public static final String  KEY_USERNAME = "username";
    public static final String  KEY_PASSWORD = "password";
    public static final String  KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsuario = (EditText) findViewById(R.id.usuario);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        registrarse = (Button) findViewById(R.id.registrarse);
        login = (Button) findViewById(R.id.iniciar_sesion);

        // Paso 2: asociar listener a los botones y crear metodos que manejan el click
        registrarse.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrarse:
                registrarse();
                break;
            case R.id.iniciar_sesion:
                iniciarSesion();
                break;

        }
    }

    private void iniciarSesion() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

    }

    //Paso 5: Obtenemos los datos a enviar al servidor
    private void registrarse() {
        final String usuario = mUsuario.getText().toString();
        final String password = mPassword.getText().toString();
        final String email = mEmail.getText().toString();

        Log.d("Datos a enviar", "Datos a enviar: " + usuario + " - " + password + " - " + email);

        //Paso 6: Creamos un request del tipo String el cual recibe 4 parametros
        StringRequest stringRequest = new StringRequest(
                // Parametro 1
                Request.Method.POST,

                //Parametro 2
                REGISTRO_URL,

                //Parametro 3
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Respuesta", "respuesta positiva: " + response);
                        Toast.makeText(RegistroActivity.this, "Registro: " + response, Toast.LENGTH_SHORT).show();

                    }
                },

                // Parametro 4
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Respuesta", "respuesta error!!: ");
                        Toast.makeText(RegistroActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        ){
            //Paso 7: Enviamos los parametros

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //En un objeto del tipo Map almacenamos los valores ingresados y como clave lo que espera el servidor
                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put(KEY_USERNAME, usuario);
                parametros.put(KEY_PASSWORD, password);
                parametros.put(KEY_EMAIL, email);

                return parametros;
            }
        };

        //Paso 8: Añadimos la solicitud a la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

        //Paso 9: limpiar el edittext
        mUsuario.setText("");
        mPassword.setText("");
        mEmail.setText("");
    }
}
