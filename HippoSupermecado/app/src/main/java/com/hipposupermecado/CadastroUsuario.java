package com.hipposupermecado;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;


public class CadastroUsuario extends Fragment
{
    // Enviar se o usuário está ativo ou não.
    private EditText etNome, etEmail, etSenha, etConfirmarSenha;
    private Button btnSalvar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);

        etNome = (EditText) view.findViewById(R.id.etNome);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etSenha = (EditText) view.findViewById(R.id.etSenha);
        etConfirmarSenha = (EditText) view.findViewById(R.id.etConfirmarSenha);
        btnSalvar = (Button) view.findViewById(R.id.btnSalvar);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String confSenha = etConfirmarSenha.getText().toString();


                if(isEmptyForm(nome, email, senha, confSenha)){
                    if(patternEmail(email)){
                        showDialog("Erro", "Preencher todos os campos");
                    }
                }else if(!isEqualsPass(senha, confSenha)){
                    showDialog("Erro", "Os campos de senhas contem valores diferentes");
                }else{
                    showDialog("Sucesso", "Acesso ao banco OK");
                }

            }
        };
        btnSalvar.setOnClickListener(listener);

        return view;
    }

    private void showDialog (String titulo, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastroUsuario.this.getContext());
        builder.setMessage(msg);
        builder.setTitle(titulo);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isEmptyForm (String nome, String email, String senha, String confSenha) {
        if((nome.trim().equals("") || email.trim().equals("")) || (senha.trim().equals("") || confSenha.trim().equals("")))
        {
            return true;
        }
        return false;
    }

    private boolean isEqualsPass (String senha, String confSenha) {
        if(senha.equals(confSenha)){
            return true;
        }
        return false;
    }

    private boolean patternEmail (String email){
        Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );

        if(EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
            return true;
        }else{
            return false;
        }
    }

}
