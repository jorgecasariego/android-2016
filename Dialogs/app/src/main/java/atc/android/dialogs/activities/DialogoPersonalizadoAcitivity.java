package atc.android.dialogs.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import atc.android.dialogs.R;


/**
 * 1. Primero es necesario crear el layout del dialogo
 */
public class DialogoPersonalizadoAcitivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDialogoPersonzalidoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogo_personalizado_acitivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDialogoPersonzalidoView = (Button) findViewById(R.id.personalizado);
        mDialogoPersonzalidoView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Obtenemos el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        final View layoutDialogo = inflater.inflate(R.layout.dialog_personalizado, null);

        builder.setView(layoutDialogo);

        builder.setPositiveButton("Iniciar Sesión", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText usuario  = (EditText) layoutDialogo.findViewById(R.id.usuario);
                EditText password = (EditText) layoutDialogo.findViewById(R.id.password);

                Toast.makeText(DialogoPersonalizadoAcitivity.this,
                                "Nombre: " + usuario.getText().toString() +
                                " - Password: " + password.getText().toString()
                        , Toast.LENGTH_SHORT).show();
            }
        });


        builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Cerrar ventana
            }
        });


        AlertDialog dialogo = builder.create();
        dialogo.show();
    }
}
