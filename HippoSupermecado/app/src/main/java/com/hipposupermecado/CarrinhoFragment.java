package com.hipposupermecado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hipposupermecado.Adapter.ProdutoAdapter;
import com.hipposupermecado.Model.CarrinhoSingleton;
import com.hipposupermecado.Model.Produto;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarrinhoFragment extends Fragment {

    private TextView tvValorTotal;
    private ListView listView;
    private FrameLayout frag_container;
    private ProdutoAdapter adapter;

    public CarrinhoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        tvValorTotal = (TextView) view.findViewById(R.id.tvValorTotal);
        double valorTotal = 0;

        if(valorTotal == 0){ // adicionando tela de carrinho vazio
            CarrinhoVazio vazio = new CarrinhoVazio();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, vazio).commit();
        }else{
            listView = view.findViewById(R.id.listView);
            List<Produto> produto = CarrinhoSingleton.getInstance().getProdutos();

            adapter = new ProdutoAdapter(getContext(), produto);
            listView.setAdapter(adapter);

            for(int i = 0; i < produto.size(); i++) {
                valorTotal += produto.get(i).getPreco() - produto.get(i).getDesconto();
            }
            tvValorTotal.setText(String.format("R$ %.2f", valorTotal));

        }

        return view;
    }

}
