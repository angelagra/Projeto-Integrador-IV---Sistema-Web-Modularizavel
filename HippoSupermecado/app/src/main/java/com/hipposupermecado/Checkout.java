package com.hipposupermecado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposupermecado.Model.CarrinhoSingleton;
import com.hipposupermecado.Model.UsuarioSingleton;

public class Checkout extends Fragment {

    private RadioButton rbCartao, rbBoleto, rbEndereco;
    private TextView tvTotal;
    private Button btnFinalizar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        // Binding...
        rbCartao = view.findViewById(R.id.rbCartao);
        rbBoleto = view.findViewById(R.id.rbBoleto);
        rbEndereco = view.findViewById(R.id.rbEndereco);
        tvTotal = view.findViewById(R.id.tvTotal);
        btnFinalizar = view.findViewById(R.id.btnFinalizar);

        String usuarioLogadoNome = UsuarioSingleton.getInstance().usuarioLogado.getNome();
        Long usuarioLogadoId = UsuarioSingleton.getInstance().usuarioLogado.getId();

        if(UsuarioSingleton.getInstance().usuarioLogado.getEstaLogado()){
            int idPagamento = 1, idUsuario;


            if(rbCartao.isChecked()) {
                idPagamento = 1;
            } else if (rbBoleto.isChecked()) {
              idPagamento = 2;
            }


        } else {
            Toast toast = Toast.makeText(Checkout.super.getContext(), "Para filaziar a compra, deve-se efetuar o login", Toast.LENGTH_LONG);
            toast.show();
            Login login = new Login();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, login).commit();
        }

        return view;
    }
}
