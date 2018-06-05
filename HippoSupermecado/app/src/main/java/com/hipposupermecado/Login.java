package com.hipposupermecado;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposupermecado.Model.LoginModel;
import com.hipposupermecado.Model.UsuarioSingleton;
import com.hipposupermecado.validate.PatternEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class Login extends Fragment {

    private EditText etEmail, etSenha;
    private Button btnEnviar, btnCadastrar;
    private CheckBox cbLembreDeMim;

    // Criando Shared Preferences.
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, container, false);

        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        btnCadastrar = (Button) view.findViewById(R.id.btnCadastrar);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etSenha = (EditText) view.findViewById(R.id.etSenha);
        cbLembreDeMim = (CheckBox) view.findViewById(R.id.cbLembreDeMim);

        // Criando Shared Preferences.
        sharedPreferences = getActivity().getSharedPreferences("hippoSave", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String nome = sharedPreferences.getString("nome", null);
        Long id = sharedPreferences.getLong("id", 0);

        if (id != null && nome != null) {
            UsuarioSingleton.getInstance().usuarioLogado.setNome(nome);
            UsuarioSingleton.getInstance().usuarioLogado.setId(id);
            UsuarioSingleton.getInstance().usuarioLogado.setEstaLogado(true);
            alerta("Usuário Logado com Sucesso");

            Destaque fragment = new Destaque();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
        } else {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //Realizar a verificação e envio do Login e setar a mensagem no show Dialog se necessário.
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                PatternEmail pattermEmail = new PatternEmail();

                if(isEmpty(email, senha)){ alerta("Preencher os campos"); return; }
                if(!pattermEmail.isEmail(email)){ alerta("E-mail inválido"); return; }

                // Chamada no Banco
                LoginModel login = new LoginModel(email,senha);
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiLogin apiLogin = retrofit.create(ApiLogin.class);
                Call<LoginModel> call = apiLogin.getLogin(login);

                Callback<LoginModel> callbackLogin = new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response){

                        if(response.isSuccessful()){
                            try{
                                LoginModel loginResponse = response.body();
                                if(loginResponse != null){
                                    UsuarioSingleton.getInstance().usuarioLogado.setNome(loginResponse.getNome());
                                    UsuarioSingleton.getInstance().usuarioLogado.setId(loginResponse.getId());
                                    UsuarioSingleton.getInstance().usuarioLogado.setEstaLogado(true);

                                    // Salvar as informações se o usuário clicar no lembre de mim.
                                    if(cbLembreDeMim.isChecked()){
                                        editor.putLong("id", loginResponse.getId());
                                        editor.putString("nome", loginResponse.getNome());
                                        editor.apply();
                                    }

                                    Destaque fragment = new Destaque();
                                    getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                                }
                            }catch (Exception e){
                                UsuarioSingleton.getInstance().usuarioLogado.setEstaLogado(false);
                                alerta("Tivemos algum problema ao realizar o logar, por favor tente mais tarde.");
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        t.printStackTrace();
                        UsuarioSingleton.getInstance().usuarioLogado.setEstaLogado(false);
                        alerta("Tivemos algum problema ao logar, por favor tente mais tarde.");
                    }
                };
                call.enqueue(callbackLogin);
                }
            };
            btnEnviar.setOnClickListener(listener);

            View.OnClickListener listenerCadastro = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CadastroUsuario cdUsuario = new CadastroUsuario();
                    getFragmentManager().beginTransaction().replace(R.id.frag_container, cdUsuario).commit();
                }
            };
            btnCadastrar.setOnClickListener(listenerCadastro);
        }

        return view;
    }

    private void alerta (String msg) {
        Toast toast = Toast.makeText(Login.super.getContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }

    private boolean isEmpty (String email,String senha) {
        if(email.trim().equals("") || senha.trim().equals("")){
            return true;
        }
        return false;
    }
}