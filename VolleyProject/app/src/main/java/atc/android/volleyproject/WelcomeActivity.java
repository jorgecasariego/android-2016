package atc.android.volleyproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView mensajeBienvenida;
    private String nombreUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mensajeBienvenida = (TextView) findViewById(R.id.mensaje);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            nombreUsuario = extras.getString(RegistroActivity.KEY_USERNAME);
            mensajeBienvenida.setText("Bienvenido " + nombreUsuario);
        }
    }
}
