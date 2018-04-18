package com.hipposupermecado;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
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

                Pattern p = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
                Matcher verificaEmail = p.matcher(email);

                if (verificaEmail.find())
                    System.out.println("email incorreto!");


                if((nome.equals("") || email.equals("")) || (senha.equals("") || confSenha.equals(""))){
                    showDialog("Erro", "Preencher todos os campos");
                }else if(!senha.equals(confSenha)){
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

}
