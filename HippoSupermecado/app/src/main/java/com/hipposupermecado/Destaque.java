package com.hipposupermecado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Destaque extends Fragment {

    private FrameLayout layoutContainer;

    public Destaque() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destaque, container, false);

        layoutContainer = (FrameLayout) view.findViewById(R.id.layoutContainer);

        CategoriasFragment fragment = new CategoriasFragment();
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, fragment).commit();

        return view;
    }

}
