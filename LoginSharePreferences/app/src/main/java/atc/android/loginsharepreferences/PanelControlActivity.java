package atc.android.loginsharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class PanelControlActivity extends AppCompatActivity implements View.OnClickListener {

    // Paso 1: Declaramos una variable del tipo SharedPreferences y un editor para manipular los datos
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;

    private TextView bienvenida;

    private Button mDeveloperView;
    private Button mDesignView;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_control);

        bienvenida = (TextView) findViewById(R.id.bienvenida);

        preferencias = getSharedPreferences(MainActivity.ANDROID_LOGIN, Context.MODE_PRIVATE);

        String nombre = preferencias.getString(MainActivity.NOMBRE, "");

        bienvenida.setText(" Bienvenido " + nombre);


        mDeveloperView = (Button) findViewById(R.id.developer);
        mDesignView = (Button) findViewById(R.id.design);

        mDeveloperView.setOnClickListener(this);
        mDesignView.setOnClickListener(this);

        // Paso 1: Inicialiamos el webview
        webView = (WebView) findViewById(R.id.webview);

        //Paso 2: Habilitar javascript
        webView.getSettings().setJavaScriptEnabled(true);

        //Paso 3:Si abrimos una página Web en una vista WebView y hacemos clic sobre alguno de sus enlaces,
        //éste se abrirá en el navegador Web de nuestro dispositivo en vez de abrir la página en el WebView.
        //Este comportamiento puede sobrescribirse, permitiendo al usuario moverse hacia adelante
        //y atrás a través del historial almacenado en el WebView.
        //Para sobrescribir dicho comportamiento debemos de definir un WebViewClient
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.developer:
                webView.loadUrl("https://developer.android.com/index.html");
                break;
            case R.id.design:
                webView.loadUrl("https://developer.android.com/design/index.html");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
