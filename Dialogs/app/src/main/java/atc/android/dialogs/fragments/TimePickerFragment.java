package atc.android.dialogs.fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment{

    private Context context;
    private TimePickerDialog.OnTimeSetListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        try{
            listener = (TimePickerDialog.OnTimeSetListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " debe implementar OnTimeSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendario = java.util.Calendar.getInstance();
        int hora = calendario.get(java.util.Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(java.util.Calendar.MINUTE);

        // Creamos una nueva instancia de time picker y retornamos
        return new TimePickerDialog(context,
                listener,
                hora,
                minuto,
                DateFormat.is24HourFormat(context));
    }
}
