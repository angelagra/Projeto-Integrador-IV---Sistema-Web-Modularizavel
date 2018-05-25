package com.hipposupermecado;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposupermecado.Model.CarrinhoSingleton;
import com.hipposupermecado.Model.Produto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detalhes extends Fragment {

    private TextView tvNomeProduto;
    private TextView tvDescricao;
    private ImageView imgProduto;
    private TextView tvCategoria;
    private Button btnRetira;
    private TextView tvQtd;
    private Button btnAdicione;
    private TextView txtDesconto;
    private TextView tvPreco;
    private ProgressBar loadProgress;

    private FloatingActionButton fab;

    private FrameLayout frag_container;

    private int qtd = 0;

    public Detalhes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detalhes2, container, false);
        View view = inflater.inflate(R.layout.detalhes_layout, container, false);

        tvNomeProduto = (TextView) view.findViewById(R.id.tvNomeProduto);
        tvDescricao = (TextView) view.findViewById(R.id.tvDescricao);
        imgProduto = (ImageView) view.findViewById(R.id.imgProduto);
        tvCategoria = (TextView) view.findViewById(R.id.tvCategoria);
        //btnRetira = (Button) view.findViewById(R.id.btnRetira);
        //tvQtd = (TextView) view.findViewById(R.id.tvQtd);
        //btnAdicione = (Button) view.findViewById(R.id.btnAdicione);
        txtDesconto = (TextView) view.findViewById(R.id.txtDesconto);
        tvPreco = (TextView) view.findViewById(R.id.tvPreco);
        loadProgress = (ProgressBar) view.findViewById(R.id.progressBar);
        fab = view.findViewById(R.id.fabAddCarrinho);
        final Produto[] prod = new Produto[1];

        int id = 0;
        String nomeCat= null;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            nomeCat = bundle.getString("nomeCategoria");
        }

        tvCategoria.setText(nomeCat.toString());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hippo4sem.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiProduto apiProduto = retrofit.create(ApiProduto.class);
        Call<List<Produto>> call = apiProduto.getDetalhe(id);

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response2) {
                List<Produto> produto = response2.body();

                String valor = String.valueOf(produto.get(0).getId());
                String url = "https://hippo4sem.azurewebsites.net/4A/GetImagem?id="+valor+"&w=250";
                ImageLoader imageLoader = ImageLoader.getInstance();
                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        //Colocar imagem de loading .gif ou img -- .showImageOnLoading(R.drawable.loading)
                        .showImageForEmptyUri(R.drawable.no_image)
                        .showImageOnFail(R.drawable.no_image)
                        .cacheInMemory(true)
                        .build();


                imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));


                imageLoader.displayImage(url, imgProduto,options);


                tvNomeProduto.setText(produto.get(0).getNome());

                if(produto.get(0).getDesconto() != 0) {
                    txtDesconto.setText(String.format("R$ %.2f", produto.get(0).getPreco()));
                    txtDesconto.setPaintFlags(txtDesconto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                tvPreco.setText(String.format("R$ %.2f", produto.get(0).getPreco() - produto.get(0).getDesconto()));
                tvDescricao.setText(produto.get(0).getDescricao());

                loadProgress.setVisibility(View.GONE);

                prod[0] = new Produto(produto.get(0).getId(),
                        produto.get(0).getNome(),
                        produto.get(0).getDescricao(),
                        produto.get(0).getPreco(),
                        produto.get(0).getDesconto(),
                        produto.get(0).getQtdMinEstoque(),
                        produto.get(0).getCategoria());

            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    //Toast.makeText(getContext(), "Deu ruim!", Toast.LENGTH_SHORT).show();
                    Erro fragment = new Erro();
                    getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
                }
            }
        });

        /*View.OnClickListener add = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtd++;
                tvQtd.setText(String.valueOf(qtd));
            }
        };
        btnAdicione.setOnClickListener(add);

        View.OnClickListener sub = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtd--;

                if (qtd < 0)
                    qtd = 0;

                tvQtd.setText(String.valueOf(qtd));
            }
        };
        btnRetira.setOnClickListener(sub);*/

        View.OnClickListener addCarrinho = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarrinhoSingleton.getInstance().setProduto(prod[0]);

                frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                CarrinhoFragment fragment = new CarrinhoFragment();
                getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
            }
        };
        fab.setOnClickListener(addCarrinho);

        return view;
    }

}