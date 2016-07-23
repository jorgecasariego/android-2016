package atc.android.fragmentcommunication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedidoMenu(1);
    }

    @Override
    public void pedidoMenu(int numeroPedido) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentPedido pedido = (FragmentPedido) manager.findFragmentById(R.id.fragment_pedido);
        pedido.mostrarPedido(numeroPedido);
    }
}
