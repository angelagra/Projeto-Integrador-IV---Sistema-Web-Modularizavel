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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hipposupermecado.Model.Carrinho;
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
    private Button btnSubQtd;
    private TextView tvQtd;
    private Button btnAddQtd;
    private TextView txtDesconto;
    private TextView tvPreco;
    private ProgressBar loadProgress;

    private FloatingActionButton fab;

    private FrameLayout frag_container;

    private int qtd = 1;
    private Carrinho prod;

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
        btnSubQtd = (Button) view.findViewById(R.id.btnSubQtd);
        tvQtd = (TextView) view.findViewById(R.id.tvQtdProdutos);
        btnAddQtd = (Button) view.findViewById(R.id.btnAddQtd);
        txtDesconto = (TextView) view.findViewById(R.id.txtDesconto);
        tvPreco = (TextView) view.findViewById(R.id.tvPreco);
        loadProgress = (ProgressBar) view.findViewById(R.id.progressBar);
        fab = view.findViewById(R.id.fabAddCarrinho);

        int id = 0;
        String nomeCat= null;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            nomeCat = bundle.getString("nomeCategoria");
        }

        if(nomeCat != null)
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

                prod = new Carrinho(produto.get(0).getId(),
                        produto.get(0).getNome(),
                        produto.get(0).getPreco(),
                        produto.get(0).getDesconto(),
                        qtd);
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

        View.OnClickListener add = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtd++;
                tvQtd.setText(String.valueOf(qtd));
            }
        };
        btnAddQtd.setOnClickListener(add);

        View.OnClickListener sub = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtd--;

                if (qtd < 1)
                    qtd = 1;

                tvQtd.setText(String.valueOf(qtd));
            }
        };
        btnSubQtd.setOnClickListener(sub);

        View.OnClickListener addCarrinho = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prod.setQtd(qtd);
                CarrinhoSingleton.getInstance().setProduto(prod);

                frag_container = (FrameLayout) view.findViewById(R.id.frag_container);
                CarrinhoFragment fragment = new CarrinhoFragment();
                getFragmentManager().beginTransaction().replace(R.id.frag_container, fragment).commit();
            }
        };
        fab.setOnClickListener(addCarrinho);

        return view;
    }

}