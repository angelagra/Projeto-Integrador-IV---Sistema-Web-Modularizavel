package com.hipposupermecado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hipposupermecado.Model.Usuario;
import com.hipposupermecado.validate.PatternEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CadastroUsuario extends Fragment
{
    // Enviar se o usuário está ativo ou não.
    private EditText etNome, etEmail, etCpf, etCelular, etDD, etMM, etAAAA, etSenha, etConfirmarSenha;
    private Button btnSalvar;
    private CheckBox cbNewsLetter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);

        // Binding...
        etNome = (EditText) view.findViewById(R.id.etNome);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etCpf = (EditText) view.findViewById(R.id.etCpf);
        etCelular = (EditText) view.findViewById(R.id.etCelular);
        etDD = (EditText) view.findViewById(R.id.etDD);
        etMM = (EditText) view.findViewById(R.id.etMM);
        etAAAA = (EditText) view.findViewById(R.id.etAAAA);
        etSenha = (EditText) view.findViewById(R.id.etSenha);
        etConfirmarSenha = (EditText) view.findViewById(R.id.etConfirmarSenha);
        btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        cbNewsLetter = (CheckBox) view.findViewById(R.id.cbNewsLetter);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // VALIDAÇÕES DE ENTRADA DO APP -----------------------------------
                String inicio = "O campo ";
                String fim = "deve ser preenchido";
                // Nome
                final String nome = etNome.getText().toString();
                if(isEmptyForm(nome)){
                    alerta(inicio + "nome " + fim);
                    return;
                }
                // E-mail
                PatternEmail pattermEmail = new PatternEmail();
                final String email = etEmail.getText().toString();
                if(isEmptyForm(email)){
                    alerta(inicio + "Email " + fim);
                    return;
                }
                if(!pattermEmail.isEmail(email)){
                    alerta("Email inválido");
                    return;
                }
                // CPF
                String cpf = etCpf.getText().toString();
                if(isEmptyForm(cpf)){
                    alerta(inicio + "CPF " + fim);
                    return;
                }
                if(cpf.length() != 11){
                    alerta("O CPF deve conter 11 dígitos");
                    return;
                }
                // Celular
                String celular = etCelular.getText().toString();
                if(isEmptyForm(celular)){
                    alerta(inicio + "Celular " + fim);
                    return;
                }
                // Nascimento
                String data = "Data de Nascimento inválida";
                String dia = etDD.getText().toString();
                String mes = etMM.getText().toString();
                String ano = etAAAA.getText().toString();
                if((isEmptyForm(dia) || isEmptyForm(mes)) || isEmptyForm(ano)){
                    alerta(inicio + "Data de Nascimento " + fim);
                    return;
                }
                if(Integer.getInteger(dia) <= 0 || Integer.getInteger(dia) >= 31){
                    alerta(data);
                    return;
                }
                if(Integer.getInteger(mes) <= 0 || Integer.getInteger(mes) > 12){
                    alerta(data);
                    return;
                }
                if(ano.length() != 4){
                    alerta(data);
                    return;
                }
                // Senha
                final String senha = etSenha.getText().toString();
                final String confSenha = etConfirmarSenha.getText().toString();
                String validarSenha = "Os campos de senhas dever ser iguais";
                if( (isEmptyForm(senha) || isEmptyForm(confSenha))  || (!isEqualsPass(senha, confSenha))){
                    alerta(validarSenha);
                    return;
                }
                // ----------------------------------------------------------------

                // Conexão com o Banco --------------------------------------------
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiUsuario apiUsuario  = retrofit.create(ApiUsuario.class);
                Call<String> call = apiUsuario.getObject(nome,email,senha,confSenha);

                Callback<String> callbackUsuario = new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String usuario = response.body();

                        if(response.isSuccessful()){
                            // Direcionar para o cadastro de endereço.
                            Login login = new Login();
                            getFragmentManager().beginTransaction().replace(R.id.frag_container, login).commit();
                            return;
                        }else{
                            if (response.code()==401) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                };
                call.enqueue(callbackUsuario);
                // ----------------------------------------------------------------
            }
        };
        btnSalvar.setOnClickListener(listener);

        return view;
    }

    private void alerta (String msg) {
        Toast toast = Toast.makeText(CadastroUsuario.super.getContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }

    private boolean isEmptyForm (String val) {
        if(val.trim().equals("")) { return true; }
        return false;
    }

    private boolean isEqualsPass (String senha, String confSenha) {
        if(senha.equals(confSenha)){
            return true;
        }
        return false;
    }
}
