package com.hipposupermecado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hipposupermecado.Adapter.CarrinhoAdapter;
import com.hipposupermecado.Adapter.ProdutoAdapter;
import com.hipposupermecado.Model.Carrinho;
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
    private CarrinhoAdapter adapter;
    private Button btnFinalizarCompra, btnContinuarComprando;

    public CarrinhoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

        tvValorTotal = (TextView) view.findViewById(R.id.tvValorTotal);
        btnContinuarComprando = (Button) view.findViewById(R.id.btnContinuarComprando);
        btnFinalizarCompra = (Button) view.findViewById(R.id.btnFinalizarCompra);
        double valorTotal = 0;

        listView = view.findViewById(R.id.listView);
        List<Carrinho> produto = CarrinhoSingleton.getInstance().getProdutos();

        adapter = new CarrinhoAdapter(getContext(), produto);
        listView.setAdapter(adapter);
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                Detalhes fragment = new Detalhes();

                int idProd = Integer.parseInt(view.getTag().toString());

                Bundle args = new Bundle();
                args.putInt("id", idProd);
                fragment.setArguments(args);


                getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
            }
        });

        for(int i = 0; i < produto.size(); i++) {
            valorTotal += (produto.get(i).getPreco() - produto.get(i).getDesconto()) * produto.get(i).getQtd();
        }
        tvValorTotal.setText(String.format("R$ %.2f", valorTotal));

        if(valorTotal == 0){ // adicionando tela de carrinho vazio
            CarrinhoVazio vazio = new CarrinhoVazio();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, vazio).commit();
        }

        View.OnClickListener continuarComprando = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                Destaque fragment = new Destaque();
                getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
            }
        };
        btnContinuarComprando.setOnClickListener(continuarComprando);

        View.OnClickListener finalizarCompra = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                Checkout fragment = new Checkout();
                getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
            }
        };
        btnFinalizarCompra.setOnClickListener(finalizarCompra);

        return view;
    }

}
