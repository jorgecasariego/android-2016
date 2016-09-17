package atc.android.basedatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //1. Creamos las vistas
    private Button guardarUsuarios;
    private Button verUsuarios;
    private Button verPasswordUsuario;
    private Button borrarUsuario;
    private Button editarPassword;

    private EditText nombreUsuario;
    private EditText passwordUsuario;

    // 2. Creamos un objeto del tipo DatabaseAdapter
    private DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3. Asociamos las vistas
        nombreUsuario = (EditText) findViewById(R.id.nombre);
        passwordUsuario = (EditText) findViewById(R.id.password);

        guardarUsuarios = (Button) findViewById(R.id.guardar);
        guardarUsuarios.setOnClickListener(this);

        verUsuarios = (Button) findViewById(R.id.ver);
        verUsuarios.setOnClickListener(this);

        verPasswordUsuario = (Button) findViewById(R.id.verPassword);
        verPasswordUsuario.setOnClickListener(this);

        borrarUsuario = (Button) findViewById(R.id.borrarUsuario);
        borrarUsuario.setOnClickListener(this);

        editarPassword = (Button) findViewById(R.id.editarPassword);
        editarPassword.setOnClickListener(this);

        // 4. Inicializamos el adaptador
        adapter = new DatabaseAdapter(this);

        // 5. Ejecutamos y vemos que pasa

        //6. Que metodo fue llamado?
        //7. Porque no fue llamado onCreate ni onUpgrade?

        /*
         * 8. Recordar
         * SQLite es bastante eficiente ya que evita la creación de la base de datos a no ser que
         * alguien trate de acceder a la BD por 1ra vez. Al inicializar MiHelper no creamos una BD
         * para poder acceder.
         *
         *  Para poder acceder a la BD el cual representa el archivo de la base de datos
         *  fisica almacenado en nuestro dispositivo es necesario llamar al metodo getWritableDatabase().
         *  De esta manera se llevara a cabo el ciclo de vida del SQLOpenHelper
         */


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.guardar:
                guardarUsuario();
                break;
            case R.id.ver:
                verUsuariosBd();
                break;
            case R.id.verPassword:
                verContraseñaUsuario();
                break;
            case R.id.borrarUsuario:
                borrarUsuarioBd();
                break;
            case R.id.editarPassword:
                editarPasswordUsuarioBd();
                break;
        }
    }

    private void editarPasswordUsuarioBd() {
        //7. Llamamos al metodo para ver detalle de un usuario especifico
        int resultado = adapter.updateUserPassword(nombreUsuario.getText().toString(), passwordUsuario.getText().toString());

        if(resultado > 0){
            Toast.makeText(this, "Contraseña cambiada con exito", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Error al cambiar contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    private void borrarUsuarioBd() {
        //7. Llamamos al metodo para ver detalle de un usuario especifico
        int resultado = adapter.deleteUser(nombreUsuario.getText().toString());

        if(resultado > 0){
            Toast.makeText(this, "Usuario borrado con exito (" + resultado + ")", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Error al borrar usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void verContraseñaUsuario() {
        //12. Llamamos al metodo para ver detalle de un usuario especifico
        String passwordUsuario = adapter.getUserPassword(nombreUsuario.getText().toString());

        Toast.makeText(this, passwordUsuario, Toast.LENGTH_SHORT).show();
    }

    private void verUsuariosBd() {
        // 11.
        String usuarios = adapter.getUsers();

        Toast.makeText(MainActivity.this, "Usuarios: " + usuarios, Toast.LENGTH_SHORT).show();
    }

    private void guardarUsuario() {
        // 9. obtenemos los datos del usuario
        String nombre = nombreUsuario.getText().toString();
        String password = passwordUsuario.getText().toString();

        // 10. LLamamos al metodo para insertar el nombre de un usuario
        long id = adapter.insertUser(nombre, password);

        if(id < 0){
            Toast.makeText(MainActivity.this, "La carga no pudo ser realizada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Usuario insertado con exito", Toast.LENGTH_SHORT).show();
        }
    }
}
