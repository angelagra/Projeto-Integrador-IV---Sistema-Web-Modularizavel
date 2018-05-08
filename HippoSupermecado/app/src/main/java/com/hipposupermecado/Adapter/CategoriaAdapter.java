package com.hipposupermecado.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hipposupermecado.Model.Categoria;
import com.hipposupermecado.R;

import java.util.List;

/**
 * Created by Noah on 03/05/2018.
 */

public class CategoriaAdapter extends BaseAdapter {
    private Context mContext;
    private List<Categoria> listaCategoria;

    public CategoriaAdapter(Context mContext, List<Categoria> listaCategoria) {
        this.mContext = mContext;
        this.listaCategoria = listaCategoria;
    }

    @Override
    public int getCount() {
        return listaCategoria.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCategoria.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_categoria, null);
        TextView txtNome = (TextView) v.findViewById(R.id.txtNome);

        txtNome.setText(listaCategoria.get(position).getNome());

        v.setTag(listaCategoria.get(position).getId());

        return v;
    }
}
