package com.hipposupermecado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposupermecado.Model.Carrinho;
import com.hipposupermecado.Model.CarrinhoSingleton;
import com.hipposupermecado.Model.CheckoutModel;
import com.hipposupermecado.Model.EnderecoModel;
import com.hipposupermecado.Model.UsuarioSingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class Checkout extends Fragment {

    private RadioButton rbCartao, rbBoleto, rbEndereco;
    private TextView tvTotal;
    private Button btnFinalizar;
    private String nomeEndereco;
    private Long idEndereco;
    private Long idUsuarioShared;
    private int idPagamento = 1;
    private Long idPedido;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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

        if (UsuarioSingleton.getInstance().usuarioLogado.getEstaLogado()) {

            //Retrofit obter Endereco
            sharedPreferences = getActivity().getSharedPreferences("hippoSave", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            idUsuarioShared = sharedPreferences.getLong("id",0);

            EnderecoModel enderecoModel = new EnderecoModel(idUsuarioShared);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
            ApiEndereco apiEndereco  = retrofit.create(ApiEndereco.class);
            Call<EnderecoModel> call = apiEndereco.getEndereco(enderecoModel);

            Callback<EnderecoModel> callbackEndereco = new Callback<EnderecoModel>() {
                @Override
                public void onResponse(Call<EnderecoModel> call, Response<EnderecoModel> response) {
                    EnderecoModel enderecoRes = response.body();

                    if(response.isSuccessful()){
                        if(enderecoRes.getAction()){
                            nomeEndereco = enderecoRes.getNomeEndereco();
                            idEndereco = enderecoRes.getId();
                            rbEndereco.setText(nomeEndereco);
                        }else{
                            Toast toast = Toast.makeText(Checkout.super.getContext(), "Não possui endereço cadastrado. Cadastre um endereço!", Toast.LENGTH_LONG);
                            toast.show();
                            Endereco frag = new Endereco();
                            getFragmentManager().beginTransaction().replace(R.id.frag_container, frag).commit();
                        }
                    }
                }

                @Override
                public void onFailure(Call<EnderecoModel> call, Throwable t) {
                    Toast toast = Toast.makeText(Checkout.super.getContext(), "Erro ao obter endereço!", Toast.LENGTH_LONG);
                    toast.show();
                }
            };
            call.enqueue(callbackEndereco);

            double valorTtal = 0;
            List<Carrinho> produto = CarrinhoSingleton.getInstance().getProdutos();

            for(int i = 0; i < produto.size(); i++) {
                valorTtal += (produto.get(i).getPreco() - produto.get(i).getDesconto()) * produto.get(i).getQtd();
            }

            if( valorTtal > 0) {
                tvTotal.setText(String.format("%.2f", valorTtal));

                //idUsuario;
                if (rbCartao.isChecked()) {
                    idPagamento = 1;
                } else if (rbBoleto.isChecked()) {
                    idPagamento = 2;
                }

                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<Carrinho> itemCarrinho = CarrinhoSingleton.getInstance().getProdutos();
                        int idAplicacao = 2;
                        int idStatus = 4;

                        CheckoutModel checkoutModel = new CheckoutModel(idUsuarioShared,idStatus,idPagamento,149,idAplicacao,itemCarrinho);
                        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://hippo.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                        ApiCheckout apiCheckout  = retrofit.create(ApiCheckout.class);
                        Call<CheckoutModel> call = apiCheckout.insertCheckout(checkoutModel);

                        Callback<CheckoutModel> callbackCadastro = new Callback<CheckoutModel>() {
                            @Override
                            public void onResponse(Call<CheckoutModel> call, Response<CheckoutModel> response) {
                                CheckoutModel checkoutRes = response.body();
                                if(response.isSuccessful()){
                                    if(checkoutRes.getAction()){
                                        idPedido = checkoutRes.getIdPedido();

                                        alerta("Pedido " + idPedido + " Finalizado com Sucesso!");

                                        Destaque fragment = new Destaque();
                                        getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                                    }else{
                                        alerta("Pedido não registrado");
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<CheckoutModel> call, Throwable t) {
                                alerta("Tente novamente mais tarde");
                            }
                        };
                        call.enqueue(callbackCadastro);
                    }
                };

                btnFinalizar.setOnClickListener(listener);
            } else {
                Toast toast = Toast.makeText(Checkout.super.getContext(), "Carrinho Vazio", Toast.LENGTH_SHORT);
                toast.show();

                Destaque frag = new Destaque();
                getFragmentManager().beginTransaction().replace(R.id.frag_container, frag).commit();
            }
        } else {
            Toast toast = Toast.makeText(Checkout.super.getContext(), "Para finalizar a compra, deve-se efetuar o login", Toast.LENGTH_LONG);
            toast.show();
            Login login = new Login();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, login).commit();
        }

        return view;
    }

    private void alerta (String msg) {
        Toast toast = Toast.makeText(Checkout.super.getContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
