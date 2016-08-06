package atc.android.explicitintent;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/*
    Otros ejemplos: https://developer.android.com/guide/components/intents-common.html
 */
public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    private String[] amigos = new String[5];
    private TextView amigo;

    private ImageButton mComprarView;
    private ImageButton mLlamarView;
    private ImageButton mEmailView;
    private ImageButton mMapaView;


    private TextView mNombreView;

    private ImageButton mFelicidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        amigo = (TextView) findViewById(R.id.nombre_amigo);

        amigos[0] = "Maria";
        amigos[1] = "Juan";
        amigos[2] = "Lili";
        amigos[3] = "Carlos";
        amigos[4] = "Marta";

        Random randomNumero = new Random();
        int numeroElegido = randomNumero.nextInt(5);

        amigo.setText("tu amigo invisible es " + amigos[numeroElegido]);


        mComprarView = (ImageButton) findViewById(R.id.comprar);
        mLlamarView = (ImageButton) findViewById(R.id.llamar);
        mEmailView = (ImageButton) findViewById(R.id.email);
        mMapaView = (ImageButton) findViewById(R.id.mapa);

        mComprarView.setOnClickListener(this);
        mLlamarView.setOnClickListener(this);
        mEmailView.setOnClickListener(this);
        mMapaView.setOnClickListener(this);


        mNombreView = (TextView) findViewById(R.id.mi_nombre);

        //Obtengo mi nombre
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            Toast.makeText(Activity2.this, "No has ingresado tu nombre", Toast.LENGTH_SHORT).show();

            return;
        }

        String miNombre = extras.getString("NOMBRE");
        mNombreView.setText(miNombre);

        mFelicidad = (ImageButton) findViewById(R.id.feliz);
        mFelicidad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = null;

        switch (view.getId()) {
            case R.id.comprar:
                comprarRegalo();
                break;
            case R.id.llamar:
                // Para realizar llamadas es necesario pedir permiso para hacer llamadas
                // 1. Primero en el manifest
                // 2. Segundos para versiones Android 23+ tenemos que controlar el permiso en runtime
                realizarLlamada();
                break;
            case R.id.mapa:
                abrirGoogleMaps();
                break;
            case R.id.email:
                escribirMail();
                break;
            case R.id.feliz:
                finish();
                break;

        }
    }

    // https://developer.android.com/guide/components/intents-common.html#Email
    private void escribirMail() {
        String[] direccionesDeCorreo = {"arya@gmail.com", "cersei@hotmail.com", "sansa@altavista.com"};

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Dia de la amistad");
        i.putExtra(Intent.EXTRA_TEXT, "El festejo será el sabado a las 22 hs");
        i.putExtra(Intent.EXTRA_EMAIL, direccionesDeCorreo);

        // Es mejor siempre asegurar que tenemos isntalado alguna aplicación que pueda resolver el
        // intent antes de iniciar la actividad
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }

    }

    private void comprarRegalo() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"));
        startActivity(i);
    }

    // Mas info aqui: https://developers.google.com/maps/documentation/android-api/intents?hl=es
    private void abrirGoogleMaps() {

        //El mapa se centra en la long y lat pasadas
        //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284946, -57.564513"));

        //Muestra un cuadro de busqueda
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284946, -57.564513?q=Shopping"));

        //Muestra un marcador en la direccion exacta
        //Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284946, -57.564513?q=Palma y 15 de agosto"));
        startActivity(i);
    }

    private void realizarLlamada() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            checkPermisoLLamada();
            return;
        }
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0981443343"));
        startActivity(i);
    }

    // Comprobamos si tiene los permisos necesarios para llamar
    private void checkPermisoLLamada(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                /**
                 * Este es el caso en que ya se le mostro anteriormente el permiso y rechazo
                 */
                new AlertDialog.Builder(this)
                        .setTitle("Permiso Requerido")
                        .setMessage("El permiso para llamar fue rechazado anteriormente. Este permiso" +
                                "es requerido para hacer llamadas desde la app. Para continuar es " +
                                "necesario aceptar los permisos dando OK")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                                ActivityCompat.requestPermissions(Activity2.this,
                                        new String[]{
                                                Manifest.permission.CALL_PHONE
                                        },1);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else{
                /**
                 * Esta es la primera vez que se le muestra
                 */
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }

            return;
        } else {
            Toast.makeText(Activity2.this, "El permiso ya ha sido concedido", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * Este metodo sera invocado cuando el usuario acepta o rechaza un permiso desde el dialogo que
     * se le muestra
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            //Caso en dar o rechazar permiso para llamar desde la app
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // EL permiso fue concedido
                    realizarLlamada();
                } else {
                    String permiso = permissions[0];
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permiso);

                    if(!showRationale){
                        Toast.makeText(Activity2.this, "Permiso denegado con la opción de nunca mas llamar!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Activity2.this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void finish() {
        //Preparar el intent
        Intent datos = new Intent();
        //Ingresamos en el intent una clave y un valor. El valor en este caso es el nombre ingresado
        //en el formulario
        datos.putExtra("NIVEL", "Muy Feliz!!");

        //Enviamos a la actividad que nos llamo con resultado ok y retornamos el dato
        setResult(RESULT_OK, datos);


        super.finish();
    }
}
