package com.hipposupermecado;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;



public class TermoDeServicoFrag extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RadioButton radioTermo;
    private Button btnTermo;

//    // TODO: Rename and change types and number of parameters
//    public static TermoDeServicoFrag newInstance(String param1, String param2) {
//        TermoDeServicoFrag fragment = new TermoDeServicoFrag();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        radioTermo = findViewById(R.id.radioTermo);


        View.OnClickListener listener = new View.OnClickListener() {

            public void onClick(View v) {
                if (radioTermo.isChecked()) {
                    showMessage("BEM VINDO!", "Ola Sr. ");
                }

            }
        };

        btnTermo.setOnClickListener(listener);


    }

    private void showMessage(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TermoDeServicoFrag.this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setCancelable(false);// impede de fechar em qualquer outro lugar a não ser o OK
        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();//mostra o dialogo na tela

    }
}
