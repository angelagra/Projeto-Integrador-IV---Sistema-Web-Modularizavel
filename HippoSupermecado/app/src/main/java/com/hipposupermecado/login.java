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

public class Login extends Fragment {

    EditText etLogin;
    EditText etSenha;
    //Verificar como a mensagem será disponibilizada
    ImageView ivLogo;
    Button btContinuar;


    public Login() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_login,
                container, false);

        etLogin = (EditText)view.findViewById(R.id.etLogin);
        etSenha =(EditText)view.findViewById(R.id.etSenha);
        btContinuar =(Button)view.findViewById(R.id.btContinue);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Realizar a verificação e envio do Login e setar a mensagem no show Dialog se necessário.
            String login = etLogin.getText().toString();
            String senha = etSenha.getText().toString();

            if(login.trim().equals("") || senha.trim().equals("")){
                showDialog("Erro","Preencha todos os campos");
            }
            }
        };

        btContinuar.setOnClickListener(listener);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void showDialog(String título,String mensagem){
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this.getContext());
        builder.setTitle("erro Login");
        builder.setMessage("variável erro Login");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
