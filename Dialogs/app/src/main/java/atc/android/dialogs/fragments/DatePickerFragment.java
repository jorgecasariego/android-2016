package atc.android.dialogs.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by jorgecasariego on 26/8/16.
 */
public class DatePickerFragment extends DialogFragment {

    private Context context;
    private DatePickerDialog.OnDateSetListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        try{
            listener = (DatePickerDialog.OnDateSetListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " necesita implementar OnDateSetListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Obtenemos la instancia del calendario
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        return new DatePickerDialog(context, listener, year, month, day);
    }
}
