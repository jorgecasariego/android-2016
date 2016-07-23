package atc.android.fragmentcommunication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPedido extends Fragment {
    private TextView mTituloView;
    private ImageView mImagenPedidoView;


    public FragmentPedido() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_pedido, container, false);

        mTituloView = (TextView) view.findViewById(R.id.titulo);
        mImagenPedidoView = (ImageView) view.findViewById(R.id.imagen);

        return view;
    }

    public void mostrarPedido(int numeroPedido){
        switch (numeroPedido){
            case 1:
                mTituloView.setText("Pedido 1");
                mImagenPedidoView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.menu1));
                break;
            case 2:
                mTituloView.setText("Pedido 2");
                mImagenPedidoView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.menu2));
                break;
            case 3:
                mTituloView.setText("Pedido 3");
                mImagenPedidoView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.menu3));
                break;
        }
    }

}
