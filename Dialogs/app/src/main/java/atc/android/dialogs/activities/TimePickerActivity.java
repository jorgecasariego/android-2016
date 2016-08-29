package atc.android.dialogs.activities;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import atc.android.dialogs.R;
import atc.android.dialogs.fragments.TimePickerFragment;

public class TimePickerActivity extends AppCompatActivity
        implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private Button mElegirHoraView;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        mElegirHoraView = (Button) findViewById(R.id.hora);
        mElegirHoraView.setOnClickListener(this);

        resultado = (TextView) findViewById(R.id.resultado);
    }

    @Override
    public void onClick(View view) {
        mostrarPickerDialog();
    }

    private void mostrarPickerDialog() {
        android.support.v4.app.DialogFragment hora = new TimePickerFragment();
        hora.show(getSupportFragmentManager(), "timePicker");
    }


    /*
    Esta funcion es llamada una vez elegida una hora
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        resultado.setText(hourOfDay + " : " + minute);
    }
}
