package com.hipposupermecado;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hipposupermecado.Adapter.CategoriaAdapter;
import com.hipposupermecado.Model.Categoria;

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
public class CategoriasFragment extends Fragment {


    private CategoriaAdapter adapter;
    private ListView listView;
    private FrameLayout frag_container;
    private ProgressBar loadProgress;

    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        listView = view.findViewById(R.id.listView);
        loadProgress = (ProgressBar) view.findViewById(R.id.progressBar);

        if(isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
            ApiCategoria apiCategoria = retrofit.create(ApiCategoria.class);
            Call<List<Categoria>> call = apiCategoria.getCategorias();

            call.enqueue(new Callback<List<Categoria>>() {
                @Override
                public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                    final List<Categoria> categoria = response.body();

                    adapter = new CategoriaAdapter(getContext(), categoria);
                    listView.setAdapter(adapter);
                    listView.setDivider(null);

                    loadProgress.setVisibility(View.GONE);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                            ProdutosFragment fragment = new ProdutosFragment();

                            int idProd = Integer.parseInt(view.getTag().toString());

                            Bundle args = new Bundle();
                            args.putInt("id", idProd);
                            args.putString("nome", categoria.get(position).getNome());
                            fragment.setArguments(args);


                            getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                        }
                    });
                }

                @Override
                public void onFailure(Call<List<Categoria>> call, Throwable t) {

                    if(t instanceof SocketTimeoutException) {
                        //Toast.makeText(getContext(), "Deu ruim!", Toast.LENGTH_SHORT).show();
                        Erro fragment = new Erro();
                        getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                    }
                }
            });
        }
        else {
            NoInternet fragment = new NoInternet();
            getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
        }

        return view;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
