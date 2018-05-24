package com.hipposupermecado.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hipposupermecado.Model.Produto;
import com.hipposupermecado.R;

import java.util.List;

/**
 * Created by Noah on 03/05/2018.
 */

public class ProdutoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Produto> listaProduto;

    public ProdutoAdapter(Context mContext, List<Produto> listaProduto) {
        this.mContext = mContext;
        this.listaProduto = listaProduto;
    }

    @Override
    public int getCount() {
        return listaProduto.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProduto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_produto, null);
        TextView txtNome = (TextView) v.findViewById(R.id.txtNome);
        // TextView txtCategoria = (TextView) v.findViewById(R.id.txtCategoria);
        TextView txtPreco = (TextView) v.findViewById(R.id.txtPreco);
        TextView txtDesconto = (TextView) v.findViewById(R.id.txtDesconto);

        txtNome.setText(listaProduto.get(position).getNome());
        // txtCategoria.setText(listaProduto.get(position).getCategoria());

        if(listaProduto.get(position).getDesconto() != 0) {
            txtDesconto.setText(String.format("De: R$ %.2f", listaProduto.get(position).getPreco()));
            txtDesconto.setPaintFlags(txtDesconto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            double precoDesconto = listaProduto.get(position).getPreco() - listaProduto.get(position).getDesconto();
            txtPreco.setText(String.format("Por: R$ %.2f", precoDesconto));
        }
        else {
            txtDesconto.setText("");
            txtPreco.setText(String.format("Por: R$ %.2f", listaProduto.get(position).getPreco()));
        }

        v.setTag(listaProduto.get(position).getId());

        return v;
    }
}