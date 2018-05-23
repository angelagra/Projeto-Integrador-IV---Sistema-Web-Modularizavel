package com.hipposupermecado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class TermoDeServico extends AppCompatActivity {
    private RadioButton radioTermo;
    private Button btnTermo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_de_servico);

        radioTermo = findViewById(R.id.radioTermo);
        btnTermo = findViewById(R.id.btnTermo);


        SharedPreferences sp1 = getSharedPreferences("radioTermo",MODE_PRIVATE);
        Boolean termo = sp1.getBoolean("checked",true);

        if(termo){
            Intent intent = new Intent(TermoDeServico.this,MainActivity.class);
            startActivity(intent);
        }
        SharedPreferences sp = getSharedPreferences("radioTermo",MODE_PRIVATE);
        SharedPreferences.Editor editor =  sp.edit();
        editor.putBoolean("checked", true);



        View.OnClickListener listener = new View.OnClickListener() {

            public void onClick(View v) {
                if (radioTermo.isChecked()) {
                    SharedPreferences sp = getSharedPreferences("radioTermo",MODE_PRIVATE);
                    SharedPreferences.Editor editor =  sp.edit();
                    editor.putBoolean("checked", true);
                    editor.apply();

                    Intent intent = new Intent(TermoDeServico.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        };

        btnTermo.setOnClickListener(listener);


    }
}
