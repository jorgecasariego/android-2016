package atc.android.dialogs.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import atc.android.dialogs.R;


/**
 * Para que esta actividad sea un dialogo debemos agregar al manifest la siguiente linea:
 *
 * <activity android:theme="@android:style/Theme.Holo.DialogWhenLarge" >
 */
public class SoyUnDialogoActivity extends Activity implements View.OnClickListener {

    private Button cerrarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soy_un_dialogo);

        cerrarView = (Button) findViewById(R.id.cerrar);
        cerrarView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
