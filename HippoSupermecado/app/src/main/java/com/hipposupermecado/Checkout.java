package com.hipposupermecado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hipposupermecado.Model.UsuarioSingleton;

public class Checkout extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        String usuarioLogadoNome = UsuarioSingleton.getInstance().usuarioLogado.getNome();
        Long usuarioLogadoId = UsuarioSingleton.getInstance().usuarioLogado.getId();

        if(usuarioLogadoNome != null && usuarioLogadoId != null){
            // fazer algo...
        } else {
            Toast toast = Toast.makeText(Checkout.super.getContext(), "Para filaziar a compra, deve-se efetuar o login", Toast.LENGTH_LONG);
            toast.show();
            Login login = new Login();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, login).commit();
        }

        return view;
    }
}
