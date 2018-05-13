package com.hipposupermecado;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // Globais para o Menu Hamburguer
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -> Iniciando o app com os fragmentos (Destaque & Categorias)
        Destaque fragment = new Destaque();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();

        // Habilitar os botões do menu.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Binding dos elementos do menu
        navigationView = (NavigationView) findViewById(R.id.navegation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()){ menuItem.setChecked(false); }
                else { menuItem.setChecked(true); }

                drawerLayout.closeDrawers();

                // Login
                if(menuItem.getItemId() == R.id.action_login){
                    Login login = new Login();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, login).commit();
                    return true;
                }

                // Cadastro de Usuário
                if(menuItem.getItemId() == R.id.action_newUser){
                    CadastroUsuario newUser = new CadastroUsuario();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, newUser).commit();
                    return true;
                }

                return false;
            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer){};
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public boolean onOptionsItemSelected (MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
