package com.hipposupermecado.Adapter;

import android.content.Context;
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
        TextView txtPreco = (TextView) v.findViewById(R.id.txtPreco);
        TextView txtDesc = (TextView) v.findViewById(R.id.txtDesc);

        txtNome.setText(listaProduto.get(position).getNome());
        txtPreco.setText(String.valueOf(listaProduto.get(position).getPreco()));
        txtDesc.setText(listaProduto.get(position).getDescricao());

        v.setTag(listaProduto.get(position).getId());

        return v;
    }
}
