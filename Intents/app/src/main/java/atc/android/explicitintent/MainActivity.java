package atc.android.explicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mElegirAmigoInvisibleView;
    private EditText mNombreView;
    private String miNombre = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mElegirAmigoInvisibleView = (ImageButton) findViewById(R.id.elegir_amigo);
        mElegirAmigoInvisibleView.setOnClickListener(this);

        mNombreView = (EditText) findViewById(R.id.nombre);
    }

    @Override
    public void onClick(View view) {

        // trim: Método que elimina los caracteres en blanco iniciales y finales de la cadena, devolviendo una copia de la misma.
        if(mNombreView.getText().toString().trim().length() > 0){
            miNombre = mNombreView.getText().toString();

            Intent i = null;
            i = new Intent(this, Activity2.class);
            i.putExtra("NOMBRE", miNombre);
            //startActivity(i);
            startActivityForResult(i, 1);

        } else {
            Toast.makeText(MainActivity.this, "Es necesario ingresar tu nombre!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){

                    mElegirAmigoInvisibleView.setBackgroundResource(R.drawable.fuegos);
                    mNombreView.setVisibility(View.GONE);

                    Bundle resultado = data.getExtras();
                    String nivelFelicidad = resultado.getString("NIVEL");
                    Toast.makeText(MainActivity.this, "Estoy " + nivelFelicidad, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
