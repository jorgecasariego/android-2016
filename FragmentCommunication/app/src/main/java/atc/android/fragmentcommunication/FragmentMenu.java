package atc.android.fragmentcommunication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment implements View.OnClickListener{

    private Button mMenu1View;
    private Button mMenu2View;
    private Button mMenu3View;
    private Communicator communicator;

    public FragmentMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_menu, container, false);

        mMenu1View = (Button) view.findViewById(R.id.menu1);
        mMenu2View = (Button) view.findViewById(R.id.menu2);
        mMenu3View = (Button) view.findViewById(R.id.menu3);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        communicator = (Communicator) getActivity();

        mMenu1View.setOnClickListener(this);
        mMenu2View.setOnClickListener(this);
        mMenu3View.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu1:
                //Enviar id del menu 1 a la actividad
                communicator.pedidoMenu(1);
                break;
            case R.id.menu2:
                //Enviar id del menu 2 a la actividad
                communicator.pedidoMenu(2);
                break;
            case R.id.menu3:
                //Enviar id del menu 3 a la actividad
                communicator.pedidoMenu(3);
                break;
        }
    }
}
