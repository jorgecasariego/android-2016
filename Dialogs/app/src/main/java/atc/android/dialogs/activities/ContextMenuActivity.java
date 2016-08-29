package atc.android.dialogs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import atc.android.dialogs.R;

//Vamos a realizar 3 tareas para crear el context menu
public class ContextMenuActivity extends AppCompatActivity {

    private TextView mCambiarDiarioView;
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCambiarDiarioView = (TextView) findViewById(R.id.cambiar_diario);

        //2. registramos el context menu
        registerForContextMenu(mCambiarDiarioView);


        // Ejercicio WebView
        mWebView = (WebView) findViewById(R.id.webview);

        mWebView.getSettings().setJavaScriptEnabled(true);

        //Si abrimos una página Web en una vista WebView y hacemos clic sobre alguno de sus enlaces,
        //éste se abrirá en el navegador Web de nuestro dispositivo en vez de abrir la página en el WebView.
        //Este comportamiento puede sobrescribirse, permitiendo al usuario moverse hacia adelante
        //y atrás a través del historial almacenado en el WebView.
        //Para sobrescribir dicho comportamiento debemos de definir un WebViewClient
        mWebView.setWebViewClient(new WebViewClient());



    }

    // 1. Crear el context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //1.1. Creamos el menu de colores que va a mostrar el menu
        getMenuInflater().inflate(R.menu.menu_context_menu, menu);
        menu.setHeaderTitle("Cambiar el diario a mostrar en pantalla");

    }

    //3. Manejar los eventos de click sobre el context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.abc:
                mWebView.loadUrl("http://www.abc.com.py");
                break;
            case R.id.ultimahora:
                mWebView.loadUrl("http://www.ultimahora.com");
                break;
            case R.id.lanacion:
                mWebView.loadUrl("http://www.lanacion.com.py");
                break;
        }
        return super.onContextItemSelected(item);
    }

    //Vamos a sobrescribir el método onBackPressed() de nuestra actividad principal para que al
    //pulsar el botón Atrás de nuestro dispositivo volvamos a la página anterior en el WebView:
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
