package com.hipposupermecado;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hipposupermecado.Model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


        // Botões do Menu Hamburguer.
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

                // Endereço
                if(menuItem.getItemId() == R.id.action_endereco){
                    Endereco newEnd = new Endereco();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, newEnd).commit();
                    return true;
                }

                return false;
            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer){};
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    // -> Trata o botão voltar do celular.
    // -> Redireciona para a home do app (Destaque e Categorias).
    @Override
    public void onBackPressed (){
        Destaque fragment = new Destaque();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
    }

    // QR CODE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){

                final String id = data.getStringExtra("id");
                if(id != null) {

                   final int idConvertido = Integer.parseInt(id);

                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                    final ApiProduto apiProduto = retrofit.create(ApiProduto.class);
                    Call<List<Produto>> call = apiProduto.getDetalhe(idConvertido);

                    call.enqueue(new Callback<List<Produto>>() {
                        @Override
                        public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                            List<Produto> produto = response.body();
                                // abrir o fragmento do produto e setar
                            Detalhes detalhe = new Detalhes();


                            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, detalhe).commit();

                        }

                        @Override
                        public void onFailure(Call<List<Produto>> call, Throwable t) {

                        }
                    });
                } else {
                    // fazer verificação se não encontrar o id, mandando uma menssagem
                   /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("QR Inválido,Tente Novamente");
                    builder.setCancelable(false);

                    DialogInterface.OnClickListener listener =  new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int i){
                            scannerView.resumeCameraPreview(rh); // quando voltar a escanner e se achar o qrCode volta a executar

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getText()));
                            startActivity(intent);
                        }
                    };
                    builder.setPositiveButton("OK",listener);

                    AlertDialog dialog =  builder.create(); // cria o dialogo
                    dialog.show(); // aparece*/
                }

            }
        }


    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        // QR Code
        if(id == R.id.action_qrCode){
            Intent intent = new Intent(MainActivity.this, QRCode.class);
            startActivityForResult(intent,0);
            return true;
        }

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.mini_menu, menu);
        return true;
    }
}
