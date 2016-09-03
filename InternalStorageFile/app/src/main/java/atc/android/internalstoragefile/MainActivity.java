package atc.android.internalstoragefile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_MIS_ITEMS = "mis_items.txt";
    private static final int READ_BLOCK_SIZE = 100;
    private EditText item;
    private Button guardar;
    private Button leer;
    private Button leerAssets;
    private TextView misItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.item);
        guardar = (Button) findViewById(R.id.guardar);
        leer = (Button) findViewById(R.id.leer);
        leerAssets = (Button) findViewById(R.id.leer_assets);
        misItems = (TextView) findViewById(R.id.mis_items);

        guardar.setOnClickListener(this);
        leer.setOnClickListener(this);
        leerAssets.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.guardar:
                guardarItem();
                break;
            case R.id.leer:
                leerItems();
                break;
            case R.id.leer_assets:
                leerAssets();
                break;
        }
    }

    private void leerAssets() {
        try {
            InputStreamReader isr =  new InputStreamReader(getAssets().open("super.txt"));

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = isr.read(inputBuffer)) > 0){
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }

            isr.close();
            misItems.setText(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerItems() {
        try {
            FileInputStream fis = openFileInput(URL_MIS_ITEMS);
            InputStreamReader isr =  new InputStreamReader(fis);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = isr.read(inputBuffer)) > 0){
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }

            isr.close();
            misItems.setText(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarItem() {
        
        try {
            FileOutputStream fos = openFileOutput(URL_MIS_ITEMS, Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(item.getText().toString() + "\n");
            osw.close();

            Toast.makeText(MainActivity.this, "Item guardado exitosamente", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
