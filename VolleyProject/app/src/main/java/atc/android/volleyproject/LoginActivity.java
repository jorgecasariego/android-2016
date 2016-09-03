package atc.android.volleyproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usuario;
    private EditText password;
    private Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);

        ingresar = (Button) findViewById(R.id.ingresar);
        ingresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ingresar();
    }

    private void ingresar() {
        final String usuario = this.usuario.getText().toString();
        final String password = this.password.getText().toString();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                RegistroActivity.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            abrirActividad(usuario);
                        } else {
                            Toast.makeText(LoginActivity.this, "Error: " + response, Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parametros = new HashMap<String, String>();
                parametros.put(RegistroActivity.KEY_USERNAME, usuario);
                parametros.put(RegistroActivity.KEY_PASSWORD, password);
                return parametros;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }

    private void abrirActividad(String usuario) {
        Intent i = new Intent(this, WelcomeActivity.class);
        i.putExtra(RegistroActivity.KEY_USERNAME, usuario);
        startActivity(i);

        this.usuario.setText("");
        this.password.setText("");
    }
}
