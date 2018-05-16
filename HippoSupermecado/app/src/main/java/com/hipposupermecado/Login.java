package com.hipposupermecado;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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

import com.hipposupermecado.validate.PatternEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends Fragment {

    private EditText etEmail, etSenha;
    private Button btnEnviar, btnCadastrar;
    private CheckBox cbLembreDeMim;
    private boolean isOk = false;

    // verificar se já esta salvo os dados da cliente.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, container, false);

        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        btnCadastrar = (Button) view.findViewById(R.id.btnCadastrar);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etSenha = (EditText) view.findViewById(R.id.etSenha);
        cbLembreDeMim = (CheckBox) view.findViewById(R.id.cbLembreDeMim);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Realizar a verificação e envio do Login e setar a mensagem no show Dialog se necessário.
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();

            PatternEmail pattermEmail = new PatternEmail();

            if(isEmpty(email, senha)){
                alerta("Preencher os campos");
                return;
            }

            if(!pattermEmail.isEmail(email)){
                alerta("E-mail inválido");
                return;
            }

            if(cbLembreDeMim.isChecked()){
                isOk = true;
                alerta("Salvar info user");
            }

            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
            ApiLogin apiLogin = retrofit.create(ApiLogin.class);
            Call<Boolean> call = apiLogin.getObject(email,senha);

            Callback<Boolean> callbackLogin = new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Boolean login = response.body();

                    if(response.isSuccessful()){
                    }else{
                        if (response.code()==401) {
                        }
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    t.printStackTrace();
                }
            };
            call.enqueue(callbackLogin);
            }
        };
        btnEnviar.setOnClickListener(listener);

        View.OnClickListener listenerCadastro = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Passar para cadastro de usuário
            }
        };
        btnCadastrar.setOnClickListener(listenerCadastro);

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
