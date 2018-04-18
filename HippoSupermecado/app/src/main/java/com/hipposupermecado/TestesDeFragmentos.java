package com.hipposupermecado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestesDeFragmentos extends AppCompatActivity
{

    private Button btnCadastroUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testes_de_fragmentos);

        btnCadastroUsuario = (Button) findViewById(R.id.btnCadastroUsuario);

        View.OnClickListener listenerCadastroUsuario = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CadastroUsuario cadastro = new CadastroUsuario();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, cadastro).commit();
            }
        };
        btnCadastroUsuario.setOnClickListener(listenerCadastroUsuario);
    }
}
