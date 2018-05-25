package com.hipposupermecado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class TermoDeServico extends AppCompatActivity {
    private RadioButton radioTermo;
    private Button btnTermo, btnFechar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_de_servico);

        radioTermo = findViewById(R.id.radioTermo);
        btnTermo = findViewById(R.id.btnTermo);
        btnFechar = findViewById(R.id.btnFechar);

        SharedPreferences sp = getSharedPreferences("radioTermo",MODE_PRIVATE);
        Boolean termo = sp.getBoolean("checked", false);
        final SharedPreferences.Editor editor =  sp.edit();

        if(termo){
            Intent intent = new Intent(TermoDeServico.this,MainActivity.class);
            startActivity(intent);
        }

        View.OnClickListener listener = new View.OnClickListener() {

            public void onClick(View v) {
            if (radioTermo.isChecked()) {
                editor.putBoolean("checked", true);
                editor.apply();

                Intent intent = new Intent(TermoDeServico.this,MainActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(TermoDeServico.this, "Para continuar a navegação, leia e confirme os termos.", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            }
        };
        btnTermo.setOnClickListener(listener);

        View.OnClickListener fechar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(TermoDeServico.this, "Infelizmente você não concorda com os nosso termos. Estamos fechando o Aplicativo.", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        };
        btnFechar.setOnClickListener(fechar);
    }
}
