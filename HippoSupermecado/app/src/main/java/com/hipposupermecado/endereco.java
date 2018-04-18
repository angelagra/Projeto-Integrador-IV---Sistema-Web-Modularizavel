package com.hipposupermecado;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Endereco extends Fragment {

    EditText etEndereco, etLogradouro, etNumero, etCep, etComplemento, etCidade, etUf, etPais;
    Button btEnviar;


    public Endereco() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_endereco,
                container, false);

        etEndereco = (EditText)view.findViewById(R.id.etEndereco);
        etLogradouro = (EditText)view.findViewById(R.id.etLogradouro);
        etNumero = (EditText)view.findViewById(R.id.etNumero);
        etCep = (EditText)view.findViewById(R.id.etCep);
        etComplemento = (EditText)view.findViewById(R.id.etComplemento);
        etCidade = (EditText)view.findViewById(R.id.etCidade);
        etUf = (EditText)view.findViewById(R.id.etUf);
        etPais = (EditText)view.findViewById(R.id.etPais);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Realizar a verificação e envio do endereço e setar a mensagem no show Dialog se necessário.
            }
        };

        btEnviar.setOnClickListener(listener);





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_endereco, container, false);
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Endereco.this.getContext());
        builder.setTitle("erro endereço");
        builder.setMessage("variável erro endereço");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
