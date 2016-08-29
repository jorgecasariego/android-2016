package atc.android.dialogs.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import atc.android.dialogs.R;
import atc.android.dialogs.fragments.DatePickerFragment;

public class DatePickerActiviy extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Button mDatePickerView;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_activiy);

        mDatePickerView = (Button) findViewById(R.id.fecha);
        mDatePickerView.setOnClickListener(this);

        resultado = (TextView) findViewById(R.id.fecha_elegida);
    }

    @Override
    public void onClick(View view) {
        mostrarDatePicker();
    }

    private void mostrarDatePicker() {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "FechaELegida");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        resultado.setText("Año: " + year + " Mes: " + (month + 1) + " Day: " + day);
    }
}
