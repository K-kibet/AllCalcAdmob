package com.codespear.allcalc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applovin.mediation.ads.MaxAdView;

public class BMRCalculatorFragment extends Fragment {

    Button btn;

    EditText height, weight, age;
    TextView result;
    LinearLayout maleLayout, femaleLayout;
    ImageView mimg, fimg;


    double h =0, w =0, a = 0, bmr = 0;

    String user = "0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_m_r_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn = view.findViewById(R.id.btn_calculate);
        height = view.findViewById(R.id.heightxt);
        weight = view.findViewById(R.id.weighttxt);
        age = view.findViewById(R.id.agettxt);

        MaxAdView adView = view.findViewById(R.id.adView);
        adView.loadAd();
        adView.startAutoRefresh();
    }
}