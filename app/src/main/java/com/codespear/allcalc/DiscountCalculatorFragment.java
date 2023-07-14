package com.codespear.allcalc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;

public class DiscountCalculatorFragment extends Fragment {

    EditText etAmount;
    TextView tvPercent;
    SeekBar sbPercent;
    TextView tvDiscount, tvTotal;
    Button btnCalculate;
    private MaxInterstitialAd mediationInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discount_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etAmount = view.findViewById(R.id.et_amount);
        tvPercent = view.findViewById(R.id.tv_percent);
        sbPercent = view.findViewById(R.id.sb_percent);
        tvDiscount = view.findViewById(R.id.tv_discount);
        tvTotal = view.findViewById(R.id.tv_total);
        btnCalculate = view.findViewById(R.id.btn_calculate);

        mediationInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Interstitial_Ad_Unit), requireActivity());
        mediationInterstitialAd.loadAd();


        sbPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int percent = progress;
                tvPercent.setText(String.valueOf(percent + "%"));
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();
            }
        });

        btnCalculate.setOnClickListener(v -> {
            calculate();
            showMediationInterstitialAd();
        });

        MaxAdView adView = view.findViewById(R.id.adView);
        adView.loadAd();
        adView.startAutoRefresh();
    }

    private void calculate() {

        if(etAmount.length() == 0) {
            etAmount.requestFocus();
            etAmount.setError("Enter Amount");
        } else {
            double amount = Double.parseDouble(etAmount.getText().toString());
            int percent = sbPercent.getProgress();
            double discount = amount * percent/100.0;
            double total = amount - discount;
            tvDiscount .setText(String.valueOf(discount));
            tvTotal.setText(String.valueOf(total));
        }
    }

    private void showMediationInterstitialAd() {
        if (mediationInterstitialAd.isReady()) {
            mediationInterstitialAd.showAd();
        }
    }
}