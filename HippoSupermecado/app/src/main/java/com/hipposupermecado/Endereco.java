package com.hipposupermecado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Endereco extends Fragment {

    private EditText etEndereco, etLogradouro, etNumero, etCep, etComplemento, etCidade, etUf, etPais;
    private Button btEnviar;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_endereco, container, false);

        etEndereco = (EditText) view.findViewById(R.id.etEndereco);
        etLogradouro = (EditText) view.findViewById(R.id.etLogradouro);
        etNumero = (EditText) view.findViewById(R.id.etNumero);
        etCep = (EditText) view.findViewById(R.id.etCep);
        etComplemento = (EditText) view.findViewById(R.id.etComplemento);
        etCidade = (EditText) view.findViewById(R.id.etCidade);
        etPais = (EditText) view.findViewById(R.id.etPais);
        etUf = (EditText) view.findViewById(R.id.etUf);
        btEnviar = (Button) view.findViewById(R.id.btEnviar);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inicio = "o campo";
                String fim = "deve ser preenchido";
                // Endereço
                String endereco = etEndereco.getText().toString();
                if(isEmpty(endereco)){
                    alerta(inicio + " Enderço " + fim);
                    return;
                }
                // Logradouro
                String logradouro = etLogradouro.getText().toString();
                if(isEmpty(logradouro)){
                    alerta(inicio + " Logradouro " + fim);
                    return;
                }
                // Número
                String numero = etNumero.getText().toString();
                if(isEmpty(numero)){
                    alerta(inicio + " Número " + fim);
                    return;
                }
                // CEP
                String cep = etCep.getText().toString();
                if(isEmpty(cep)){
                    alerta(inicio + " CEP " + fim);
                    return;
                }
                String complemento = etComplemento.getText().toString();
                // Cidade
                String cidade = etCidade.getText().toString();
                if(isEmpty(cidade)){
                    alerta(inicio + " Cidade " + fim);
                    return;
                }
                // Pais
                String pais = etPais.getText().toString();
                if(isEmpty(pais)){
                    alerta(inicio + " Pais " + fim);
                    return;
                }
                // UF
                String uf = etUf.getText().toString();
                if(isEmpty(uf)){
                    alerta(inicio + " UF " + fim);
                    return;
                }

                // Banco de Dados
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiEndereco apiEndereco  = retrofit.create(ApiEndereco.class);
                Call<String> call = apiEndereco.getObject(endereco,logradouro,numero,cep,complemento,cidade,pais,uf);

                Callback<String> callbackEndereco = new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String endereco = response.body();

                        if(response.isSuccessful()){

                        }else{
                            if (response.code()== 401) {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                };
                call.enqueue(callbackEndereco);
            }
        };
        btEnviar.setOnClickListener(listener);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_endereco, container, false);
    }

    private void alerta (String msg) {
        Toast toast = Toast.makeText(Endereco.super.getContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }

    private boolean isEmpty (String val) {
        if(val.trim().equals("")) { return true; }
        return false;
    }
}
