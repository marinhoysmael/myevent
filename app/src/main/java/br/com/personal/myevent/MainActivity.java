package br.com.personal.myevent;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.personal.myevent.Service.UsuarioService;
import br.com.personal.myevent.model.Usuario;
import br.com.personal.myevent.percistence.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private UsuarioService usuarioService;
    private EventoFragment eventoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usuarioService = UsuarioService.getInstance(getApplicationContext());


        try {
            Usuario usuario = usuarioService.buscarUltimoUsuario();
            if(usuario == null){
                Intent intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                finish();
            }else{
                if(!usuario.isLogado()){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                  }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventoFormDialog eventoFormDialog = new EventoFormDialog(false, null);

                FragmentManager fm = getSupportFragmentManager();
                eventoFormDialog.show(fm, "fragment_edit_name");

            }
        });

        eventoFragment = new EventoFragment();
        FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_content, eventoFragment, "mainfrag");

        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_logout){
            try {
                usuarioService.logoutUser();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Erro de logout", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public EventoFragment getEventoFragment() {
        return eventoFragment;
    }
}
