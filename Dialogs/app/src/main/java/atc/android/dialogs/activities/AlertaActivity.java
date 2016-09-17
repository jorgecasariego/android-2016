package atc.android.dialogs.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import atc.android.dialogs.R;

/**
 * Toda la información de dialogos se encuentra en esta direccion:
 * https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
 *
 */
public class AlertaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mIniciarAlertaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIniciarAlertaView = (Button) findViewById(R.id.iniciar_alerta);
        mIniciarAlertaView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //Inicializamos llamando al constructor de alertas: Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Cremos el mensaje y el titulo
        builder.setMessage("¿Fue posible crear la alerta?")
                .setTitle("Android ATC");
        // Ejecutar Aplicacion

        // Agregamos un boton
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AlertaActivity.this, "Alerta realizada con exito!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Volver a mirar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AlertaActivity.this, "Mirar la documentación de Android Developers", Toast.LENGTH_SHORT).show();
            }
        });

        //Ejecutamos aplicacion

        // Creamos la alerta con el builder creado con anterioridad
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
