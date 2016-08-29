package atc.android.dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import atc.android.dialogs.activities.AlertaActivity;
import atc.android.dialogs.activities.ContextMenuActivity;
import atc.android.dialogs.activities.DatePickerActiviy;
import atc.android.dialogs.activities.DialogoPersonalizadoAcitivity;
import atc.android.dialogs.activities.SettingsActivity;
import atc.android.dialogs.activities.SoyUnDialogoActivity;
import atc.android.dialogs.activities.TimePickerActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button mAbrirActividadDialogo;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        mAbrirActividadDialogo = (Button) findViewById(R.id.dialogo_activity);
        mAbrirActividadDialogo.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void setupViews() {
        // Inicializamos el Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Handler handler = new Handler();

        // Inicializamos el floating button y manejamos el evento de click
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                // Start lengthy operation in a background thread
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Now we use the Handler to post back to the main thread
                        handler.post(new Runnable() {
                            public void run() {
                                // Set the View's visibility back on the main UI Thread
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });

                    }
                }).start();


                Snackbar.make(view, "Utilizando un ProgressBar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Inicializamos el drawer y manejamos el evento de open y close
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Inicializamos el navigation view y manejamos los eventos de click
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        // SI el drawer esta abierto y pulsamos el boton de atras cerramos el drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent i = null;

        if (id == R.id.nav_alerta) {
            i = new Intent(this, AlertaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_dialog_personalizado) {
            i = new Intent(this, DialogoPersonalizadoAcitivity.class);
            startActivity(i);

        } else if (id == R.id.time_picker) {
            i = new Intent(this, TimePickerActivity.class);
            startActivity(i);

        } else if (id == R.id.date_picker) {
            i = new Intent(this, DatePickerActiviy.class);
            startActivity(i);

        } else if (id == R.id.nav_context_menu) {
            i = new Intent(this, ContextMenuActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, SoyUnDialogoActivity.class);
        startActivity(i);
    }
}
