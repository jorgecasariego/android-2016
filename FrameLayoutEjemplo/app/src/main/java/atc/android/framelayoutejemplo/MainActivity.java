package atc.android.framelayoutejemplo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imagen1View;
    private ImageView imagen2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen1View = (ImageView) findViewById(R.id.imagen1);
        imagen2View = (ImageView) findViewById(R.id.imagen2);

        imagen1View.setOnClickListener(this);
        imagen2View.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imagen1:
                imagen1View.setVisibility(View.GONE);
                imagen2View.setVisibility(View.VISIBLE);
                break;
            case R.id.imagen2:
                imagen2View.setVisibility(View.GONE);
                imagen1View.setVisibility(View.VISIBLE);
                break;
        }
    }
}
