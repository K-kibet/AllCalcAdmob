package com.codespear.allcalc;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class BasicCalculatorFragment extends Fragment {
    private FrameLayout adViewContainer;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';
    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonPercent, buttonClear, buttonOFF, buttonEqual;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = view.findViewById(R.id.input);
        outputDisplay = view.findViewById(R.id.output);
        button0 = view.findViewById(R.id.btn0);
        button1 = view.findViewById(R.id.btn1);
        button2 = view.findViewById(R.id.btn2);
        button3 = view.findViewById(R.id.btn3);
        button4 = view.findViewById(R.id.btn4);
        button5 = view.findViewById(R.id.btn5);
        button6 = view.findViewById(R.id.btn6);
        button7 = view.findViewById(R.id.btn7);
        button8 = view.findViewById(R.id.btn8);
        button9 = view.findViewById(R.id.btn9);
        buttonAdd = view.findViewById(R.id.add);
        buttonSub = view.findViewById(R.id.subtract);
        buttonDivide = view.findViewById(R.id.division);
        buttonDot = view.findViewById(R.id.btnPoint);
        buttonMultiply = view.findViewById(R.id.multiply);
        buttonClear = view.findViewById(R.id.clear);
        buttonOFF = view.findViewById(R.id.off);
        buttonEqual = view.findViewById(R.id.equal);
        buttonPercent = view.findViewById(R.id.percent);
        button0.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "0"));
        button1.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "1"));
        button2.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "2"));
        button3.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "3"));
        button4.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "4"));
        button5.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "5"));
        button6.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "6"));
        button7.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "7"));
        button8.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "8"));
        button9.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "9"));
        buttonAdd.setOnClickListener(v -> {
            allCalculations();
            currentSymbol = ADDITION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "+");
            inputDisplay.setText(null);
        });
        buttonSub.setOnClickListener(v -> {
            allCalculations();
            currentSymbol = SUBTRACTION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "-");
            inputDisplay.setText(null);
        });
        buttonMultiply.setOnClickListener(v -> {
            allCalculations();
            currentSymbol = MULTIPLICATION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "x");
            inputDisplay.setText(null);
        });
        buttonDivide.setOnClickListener(v -> {
            allCalculations();
            currentSymbol = DIVISION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "/");
            inputDisplay.setText(null);
        });
        buttonPercent.setOnClickListener(v -> {
            allCalculations();
            currentSymbol = PERCENT;
            outputDisplay.setText(decimalFormat.format(firstValue) + "%");
            inputDisplay.setText(null);
        });
        buttonDot.setOnClickListener(v -> inputDisplay.setText(inputDisplay.getText() + "."));
        buttonClear.setOnClickListener(v -> {
            if (inputDisplay.getText().length() > 0) {
                CharSequence currentText = inputDisplay.getText();
                inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
            } else {
                firstValue = Double.NaN;
                secondValue = Double.NaN;
                inputDisplay.setText("");
                outputDisplay.setText("");
            }
        });
        buttonOFF.setOnClickListener(v -> {
            //finish();
        });
        buttonEqual.setOnClickListener(view1 -> {
            allCalculations();
            outputDisplay.setText(decimalFormat.format(firstValue));
            firstValue = Double.NaN;
            currentSymbol = '0';
        });
        adViewContainer = view.findViewById(R.id.adViewContainer);
        adViewContainer.post(this::LoadBanner);
    }
    private void allCalculations(){
        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(inputDisplay.getText().toString());
            inputDisplay.setText(null);
            if (currentSymbol == ADDITION)
                firstValue = this.firstValue + secondValue;
            else if (currentSymbol == SUBTRACTION)
                firstValue = this.firstValue - secondValue;
            else if (currentSymbol == MULTIPLICATION)
                firstValue = this.firstValue * secondValue;
            else if (currentSymbol == DIVISION)
                firstValue = this.firstValue / secondValue;
            else if (currentSymbol == PERCENT)
                firstValue = this.firstValue % secondValue;
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e) {
            }
        }
    }

    private void LoadBanner() {
        AdView adView = new AdView(requireContext());
        adView.setAdUnitId(getString(R.string.admob_banner_ad_unit));
        adViewContainer.removeAllViews();
        adViewContainer.addView(adView);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(requireContext(), adWidth);
    }
}