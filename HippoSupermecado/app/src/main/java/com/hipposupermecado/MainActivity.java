package com.hipposupermecado;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposupermecado.Model.Produto;
import com.hipposupermecado.Model.UsuarioSingleton;

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
    private TextView tvMenuNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criando Shared Preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("hippoSave", MODE_PRIVATE);
        if (sharedPreferences.getLong("id", 0) != 0){
            UsuarioSingleton.getInstance().usuarioLogado.setEstaLogado(true);
        }

        tvMenuNome = findViewById(R.id.tvMenuNome);

        // -> Iniciando o app com os fragmentos (Destaque & Categorias)
        if(isNetworkAvailable()) {
            Destaque fragment = new Destaque();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
        }
        else {
            NoInternet fragment = new NoInternet();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
        }

        if(UsuarioSingleton.getInstance().usuarioLogado.getNome() != null){
            tvMenuNome.setText("Olá " + UsuarioSingleton.getInstance().usuarioLogado.getNome());
        }


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

                // Finalizar Compra
                if(menuItem.getItemId() == R.id.action_finalizar){
                    Checkout newCheckout = new Checkout();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, newCheckout).commit();
                    return true;
                }

                if(menuItem.getItemId() == R.id.action_sobre){
                    QuemSomos quemSomos = new QuemSomos();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, quemSomos).commit();
                    return true;
                }

                // Sair
                if(menuItem.getItemId() == R.id.action_sair){
                    Toast toast = Toast.makeText(MainActivity.this, "Deslogado", Toast.LENGTH_LONG);
                    toast.show();

                    SharedPreferences sharedPreferences = getSharedPreferences("hippoSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.remove("id");
                    editor.remove("nome");
                    editor.apply();

                    UsuarioSingleton.getInstance().usuarioLogado.setId(null);
                    UsuarioSingleton.getInstance().usuarioLogado.setNome(null);
                    UsuarioSingleton.getInstance().usuarioLogado.setEstaLogado(false);
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

        if(requestCode == 0){
            if(resultCode == Activity.RESULT_OK){

                final String id = data.getStringExtra("id");
                if(!id.equals(null) && !id.equals("")) {

                    final int idConvertido = Integer.parseInt(id);

                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                    final ApiProduto apiProduto = retrofit.create(ApiProduto.class);
                    Call<List<Produto>> call = apiProduto.getDetalhe(idConvertido);

                    call.enqueue(new Callback<List<Produto>>() {
                        @Override
                        public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                            List<Produto> produto = response.body();
                            // abrir o fragmento do produto e setar
                            Detalhes fragment = new Detalhes();

                            int idProd = idConvertido;

                            Bundle args = new Bundle();
                            args.putInt("id", idProd);
                            args.putString("nomeCategoria", "");// colocar nome da categoria
                            fragment.setArguments(args);


                            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                        }

                        @Override
                        public void onFailure(Call<List<Produto>> call, Throwable t) {

                        }
                    });
                }else{
                    showMessage("ERRO", "QR-Code Invalido");
                }

            }
        }
    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        // Carrinho
        if(id == R.id.action_cart){
            CarrinhoFragment cart = new CarrinhoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, cart).commit();
            return true;
        }

        // QR Code
        if(id == R.id.action_qrCode){
            Intent intent = new Intent(MainActivity.this, QRCode.class);
            startActivityForResult(intent,0);
            return true;
        }

        // Endereço
        if(id == R.id.action_endereco) {
            Endereco newEnd = new Endereco();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, newEnd).commit();
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showMessage(String titulo, String mensagem){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setCancelable(false);// impede de fechar em qualquer outro lugar a não ser o OK
        builder.setPositiveButton("OK",null );

        android.support.v7.app.AlertDialog dialog =  builder.create();
        dialog.show();//mostra o dialogo na tela
    }
}
