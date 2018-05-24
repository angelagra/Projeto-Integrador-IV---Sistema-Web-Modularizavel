package com.hipposupermecado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposupermecado.Adapter.ProdutoAdapter;
import com.hipposupermecado.Model.Produto;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {

    private TextView tvTitle;
    private ProgressBar loadProgress;

    //private TextView txtId;
    private ListView listView;
    private FrameLayout frag_container;
    private ProdutoAdapter adapter;

    public ProdutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produtos, container, false);

        listView = view.findViewById(R.id.listView);
        tvTitle = view.findViewById(R.id.tvTitle);
        loadProgress = (ProgressBar) view.findViewById(R.id.progressBar);

        int id = 0;
        String nomeCat = null;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            nomeCat = bundle.getString("nome");
        }

        tvTitle.setText(nomeCat.toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiCategoria apiCategoria = retrofit.create(ApiCategoria.class);
        Call<List<Produto>> call = apiCategoria.getProdutos(id);

        final String finalNomeCat = nomeCat;
        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response2) {
                List<Produto> produto = response2.body();

                adapter = new ProdutoAdapter(getContext(), produto);
                listView.setAdapter(adapter);
                listView.setDivider(null);

                loadProgress.setVisibility(View.GONE);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                        Detalhes fragment = new Detalhes();

                        int idProd = Integer.parseInt(view.getTag().toString());

                        Bundle args = new Bundle();
                        args.putInt("id", idProd);
                        args.putString("nomeCategoria", finalNomeCat);
                        fragment.setArguments(args);


                        getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                if(t instanceof SocketTimeoutException) {
                    //Toast.makeText(getContext(), "Deu ruim!", Toast.LENGTH_SHORT).show();
                    Erro fragment = new Erro();
                    getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                }
            }
        });

        return view;
    }

}
