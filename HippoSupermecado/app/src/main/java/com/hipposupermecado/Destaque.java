package com.hipposupermecado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Destaque extends Fragment {

    private FrameLayout layoutContainer;
    private ViewFlipper slider;

    public Destaque() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destaque, container, false);

        int imagens[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};

        layoutContainer = (FrameLayout) view.findViewById(R.id.layoutContainer);
        slider = view.findViewById(R.id.slider);

        CategoriasFragment fragment = new CategoriasFragment();
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, fragment).commit();

        for(int image: imagens){
            flipperImages(image);
        }

        return view;
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        slider.addView(imageView);
        slider.setFlipInterval(3000);
        slider.setAutoStart(true);

        slider.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        slider.setInAnimation(getActivity(), android.R.anim.slide_out_right);
    }

}
