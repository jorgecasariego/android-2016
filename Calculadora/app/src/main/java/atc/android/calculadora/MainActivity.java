package atc.android.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNumero1View;
    private EditText mNumero2View;
    private Button mSumarView;
    private Button mRestarView;
    private TextView mResultadoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asociamos vistas
        mNumero1View = (EditText) findViewById(R.id.numero1);
        mNumero2View = (EditText) findViewById(R.id.numero2);
        mSumarView = (Button) findViewById(R.id.sumar);
        mRestarView = (Button) findViewById(R.id.restar);
        mResultadoView = (TextView) findViewById(R.id.resultado);

        //Manejo de clicks a traves de un lsitener
        mSumarView.setOnClickListener(this);
        mRestarView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sumar:
                sumar();
                break;
            case R.id.restar:
                restar();
                break;
        }
    }

    private void sumar() {
        int numero1 = 0;
        int numero2 = 0;
        int resultado = 0;

        numero1 = Integer.parseInt(mNumero1View.getText().toString());
        numero2 = Integer.parseInt(mNumero2View.getText().toString());

        resultado = numero1 + numero2;

        mResultadoView.setText(String.valueOf(resultado));


    }

    private void restar() {
        int numero1 = 0;
        int numero2 = 0;
        Integer resultado = 0;

        numero1 = Integer.parseInt(mNumero1View.getText().toString());
        numero2 = Integer.parseInt(mNumero2View.getText().toString());

        resultado = numero1 - numero2;

        //Tenemos que pasar si o si un string
        mResultadoView.setText(resultado.toString());
    }
}
