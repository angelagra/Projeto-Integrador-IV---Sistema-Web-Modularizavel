package com.hipposupermecado;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hipposupermecado.validate.PatternEmail;

import java.util.regex.Pattern;


public class CadastroUsuario extends Fragment
{
    // Enviar se o usuário está ativo ou não.
    private EditText etNome, etEmail, etSenha, etConfirmarSenha;
    private Button btnSalvar;
    private TextView tvMsg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);

        etNome = (EditText) view.findViewById(R.id.etNome);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etSenha = (EditText) view.findViewById(R.id.etSenha);
        etConfirmarSenha = (EditText) view.findViewById(R.id.etConfirmarSenha);
        btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        tvMsg = (TextView) view.findViewById(R.id.tvMsg);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String confSenha = etConfirmarSenha.getText().toString();

                PatternEmail pattermEmail = new PatternEmail();

                // VALIDAÇÕES NO APP ----------------------------------------------
                if(isEmptyForm(nome, email, senha, confSenha)){
                    // Se algum campo estiver vazio!
                    tvMsg.setText("Preencher todos os campos");
                    return;
                }
                if(!isEqualsPass(senha, confSenha)){
                    // Se os valores da senha não são iguais
                    tvMsg.setText("Os campos de senhas contem valores diferentes");
                    return;
                }
                if(!pattermEmail.isEmail(email)){
                    // Se o e-mail não seguir o padrão de e-mail.
                    tvMsg.setText("E-mail inválido");
                    return;
                }
                // ----------------------------------------------------------------
                tvMsg.setText("Acesso ao banco OK");

            }
        };
        btnSalvar.setOnClickListener(listener);

        return view;
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

}
