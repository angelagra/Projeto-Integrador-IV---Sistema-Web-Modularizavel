package com.hipposupermecado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestesFragmentos extends AppCompatActivity {

    private Button btnCadastroUsuario, btnLogin, btnDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testes_fragmentos);

        btnCadastroUsuario = (Button) findViewById(R.id.btnCadastroUsuario);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnDetalhes = (Button) findViewById(R.id.btnDetalhes);

        View.OnClickListener listenerDestaque = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Detalhes detalhes = new Detalhes();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, detalhes).commit();
            }
        };
        btnDetalhes.setOnClickListener(listenerDestaque);


        View.OnClickListener listenerCadastroUsuario = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CadastroUsuario cadastro = new CadastroUsuario();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, cadastro).commit();
            }
        };
        btnCadastroUsuario.setOnClickListener(listenerCadastroUsuario);


        View.OnClickListener listenerLogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login login = new Login();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, login).commit();
            }
        };
        btnLogin.setOnClickListener(listenerLogin);
    }
}
