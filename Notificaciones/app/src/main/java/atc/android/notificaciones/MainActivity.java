package atc.android.notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int idNotificacion = 0;
    private NotificationManager manager;

    private Button imagenGrandeButton;
    private Button textoGrandeButton;
    private Button inboxStyleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        imagenGrandeButton = (Button) findViewById(R.id.notificar2);
        imagenGrandeButton.setOnClickListener(this);

        textoGrandeButton = (Button) findViewById(R.id.notificar3);
        textoGrandeButton.setOnClickListener(this);

        inboxStyleButton = (Button) findViewById(R.id.notificar4);
        inboxStyleButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Notification notification =  null;

        switch (view.getId()){
            case R.id.notificar2:
                idNotificacion = 2;
                notification = bigPictureNotification();
                sendNotification(notification, idNotificacion);
                break;
            case R.id.notificar3:
                idNotificacion= 3;
                notification = bigTextNotification();
                sendNotification(notification, idNotificacion);
                break;
            case R.id.notificar4:
                idNotificacion = 4;
                //notification = inboxStyleNotification();
                sendNotification(notification, idNotificacion);
                break;
        }
    }

    private Notification bigPictureNotification() {
        // 1
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("Nueva foto subida al servidor");
        builder.setContentText("La foto estará disponible en segundos");

        // 2. Seteamos el icono de la notificación
        builder.setSmallIcon(R.mipmap.ic_launcher);

        // 3. seteamos la hora a mostrar en la notiticacion
        builder.setWhen(System.currentTimeMillis());

        // 4. Seteamos el icono que va a ser mostrado en la notificacion
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen);

        builder.setLargeIcon(icon);

        // 5. Creamos notificacion del tipo BigPicture
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(icon)
                .bigLargeIcon(icon)
                .setBigContentTitle("Titulo cuando esta expandido")
                .setSummaryText("Resumen total");

        // 6. Seteamos el estilo de la notificacion
        builder.setStyle(bigPictureStyle);

        // 7. Agregamos una accion a realizar una vez pulsada la notificacion
        PendingIntent pendingIntent = agregarAccion();

        //8. Asociar el pending Intent al builder
        builder.setContentIntent(pendingIntent);

        return builder.build();
    }

    private PendingIntent agregarAccion() {
        Intent resultado = new Intent(this, Resultado.class);

        PendingIntent intent = PendingIntent.getActivity(this, 0, resultado, PendingIntent.FLAG_UPDATE_CURRENT);

        return intent;
    }

    private Notification bigTextNotification(){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen);

        android.support.v7.app.NotificationCompat.Builder builder = new android.support.v7.app.NotificationCompat.Builder(this);

        builder.setContentTitle("Texto Reducido")
                .setContentText("Contexto Reducido")
                .setContentInfo("info")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon);

        return new NotificationCompat.BigTextStyle(builder)
                .bigText("Clase final de Android")
                .setBigContentTitle("Android ATC")
                .setSummaryText("Esta es la ultima clase de Android ATC!!")
                .build();
    }


    //Metodo para notificar
    // Pasos:
    // 1. Obtener una instancia de NotificationManager
    // 2. Usar el metodo notify para enviar la notificacion. Cuando se utiliza este metodo es
    //    necesario utilizar un notification ID. Podemos usar este notification ID para
    //    actualizar la notiticacion despues
    public void sendNotification(Notification notification, int idNotificacion){
        manager.notify(idNotificacion, notification);
    }


}
