package com.hipposupermecado;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hipposupermecado.validate.PatternEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends Fragment {

    private EditText etEmail, etSenha;
    private TextView tvMsg;
    private Button btnEnviar;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, container, false);

        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etSenha = (EditText) view.findViewById(R.id.etSenha);
        tvMsg = (TextView) view.findViewById(R.id.tvMsg);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Realizar a verificação e envio do Login e setar a mensagem no show Dialog se necessário.
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();

            PatternEmail pattermEmail = new PatternEmail();


            if(isEmpty(email, senha)){
                tvMsg.setText("Preencher os campos");
                return;
            }

            if(!pattermEmail.isEmail(email)){
                tvMsg.setText("E-mail inválido");
                return;
            }

                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiLogin apiLogin = retrofit.create(ApiLogin.class);
                Call<Boolean> call = apiLogin.getObject(email,senha);

                Callback<Boolean> callbackCalculadora = new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Boolean login = response.body();

                        if(response.isSuccessful()){
                            tvMsg.setText("OK");
                        }else{
                            if (response.code()==401) {
                                tvMsg.setText("false");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        t.printStackTrace();
                    }
                };
                call.enqueue(callbackCalculadora);
            }
        };
        btnEnviar.setOnClickListener(listener);

        return view;
    }

    private boolean isEmpty (String email,String senha) {
        if(email.trim().equals("") || senha.trim().equals("")){
            return true;
        }
        return false;
    }
}
