package com.codespear.allcalc;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;

public class BMICalculatorFragment extends Fragment {
    TextView mcurrentheight,mcurrentweight,mcurrentage, mbmidisplay, mbmicategory, mgender;
    ImageView mincrementage,mdecrementage,mincrementweight,mdecrementweight;
    SeekBar mseekbarforheight;
    Button mcalculatebmi, mgotomain;
    RelativeLayout mmale,mfemale, mbackground;
    int intweight=55, intage=22,currentprogress;
    String mintprogress="170", typerofuser="0", weight2="55", age2="22";
    float intbmi, intheight, height;

    private MaxInterstitialAd mediationInterstitialAd;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_m_i_calculator, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mcurrentage=view.findViewById(R.id.currentage);
        mcurrentweight=view.findViewById(R.id.currentweight);
        mcurrentheight=view.findViewById(R.id.currentheight);
        mincrementage=view.findViewById(R.id.incrementage);
        mdecrementage=view.findViewById(R.id.decrementage);
        mincrementweight=view.findViewById(R.id.incremetweight);
        mdecrementweight=view.findViewById(R.id.decrementweight);
        mcalculatebmi=view.findViewById(R.id.calculatebmi);
        mseekbarforheight=view.findViewById(R.id.seekbarforheight);
        mmale=view.findViewById(R.id.male);
        mfemale=view.findViewById(R.id.female);

        mediationInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Interstitial_Ad_Unit), requireActivity());
        mediationInterstitialAd.loadAd();

        MaxAdView adView = view.findViewById(R.id.adView);
        adView.loadAd();
        adView.startAutoRefresh();

        mmale.setOnClickListener(v -> {
            mmale.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.malefemalefocus));
            mfemale.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.malefemalenotfocus));
            typerofuser="Male";

        });


        mfemale.setOnClickListener(v -> {
            mfemale.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.malefemalefocus));
            mmale.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.malefemalenotfocus));
            typerofuser="Female";
        });

        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentprogress=progress;
                mintprogress=String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mincrementweight.setOnClickListener(v -> {
            intweight=intweight+1;
            weight2=String.valueOf(intweight);
            mcurrentweight.setText(weight2);
        });

        mincrementage.setOnClickListener(v -> {
            intage=intage+1;
            age2=String.valueOf(intage);
            mcurrentage.setText(age2);
        });


        mdecrementage.setOnClickListener(v -> {
            intage=intage-1;
            age2=String.valueOf(intage);
            mcurrentage.setText(age2);
        });


        mdecrementweight.setOnClickListener(v -> {

            intweight=intweight-1;
            weight2=String.valueOf(intweight);
            mcurrentweight.setText(weight2);
        });



        mcalculatebmi.setOnClickListener(v -> {

            if(typerofuser.equals("0"))
            {
                Toast.makeText(requireContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
            }
            else if(mintprogress.equals("0"))
            {
                Toast.makeText(requireContext(),"Select Your Height First",Toast.LENGTH_SHORT).show();
            }
            else if(intage==0 || intage<0)
            {
                Toast.makeText(requireContext(),"Age is Incorrect",Toast.LENGTH_SHORT).show();
            }

            else if(intweight==0|| intweight<0)
            {
                Toast.makeText(requireContext(),"Weight Is Incorrect",Toast.LENGTH_SHORT).show();
            }
            else {


                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.show_bmi_dialog);

                mbmidisplay = dialog.findViewById(R.id.bmidisplay);
                mbmicategory = dialog.findViewById(R.id.bmicategorydispaly);
                mgotomain=dialog.findViewById(R.id.gotomain);
                mgender=dialog.findViewById(R.id.genderdisplay);
                mbackground=dialog.findViewById(R.id.contentlayout);


                intheight=Float.parseFloat(mintprogress);
                height=Float.parseFloat(mintprogress);

                intheight=intheight/100;
                intbmi=intweight/(intheight*intheight);

                if(intbmi<16)
                {
                    mbmicategory.setText("Severe Thinness");
                    mbackground.setBackgroundColor(Color.RED);

                }
                else if(intbmi<16.9 && intbmi>16)
                {
                    mbmicategory.setText("Moderate Thinness");
                    mbackground.setBackgroundColor(R.color.dark_yellow);

                }
                else if(intbmi<18.4 && intbmi>17)
                {
                    mbmicategory.setText("Mild Thinness");
                    mbackground.setBackgroundColor(R.color.dark_yellow);
                }
                else if(intbmi<24.9 && intbmi>18.5 )
                {
                    mbmicategory.setText("Normal");
                    mbackground.setBackgroundColor(Color.YELLOW);
                }
                else if(intbmi <29.9 && intbmi>25)
                {
                    mbmicategory.setText("Overweight");
                    mbackground.setBackgroundColor(R.color.dark_yellow);
                }
                else if(intbmi<34.9 && intbmi>30)
                {
                    mbmicategory.setText("Obese Class I");
                    mbackground.setBackgroundColor(R.color.dark_yellow);
                }
                else
                {
                    mbmicategory.setText("Obese Class II");
                    mbackground.setBackgroundColor(Color.RED);
                }

                mgender.setText(typerofuser);
                mbmidisplay.setText(Float.toString(intbmi));
                mgotomain.setOnClickListener(v1 -> {
                    dialog.cancel();
                    showMediationInterstitialAd();
                });


                dialog.show();

                showMediationInterstitialAd();
            }
        });
    }

    private void showMediationInterstitialAd() {
        if (mediationInterstitialAd.isReady()) {
            mediationInterstitialAd.showAd();
        }
    }
}