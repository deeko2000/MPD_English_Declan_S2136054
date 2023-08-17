//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class ConversionActivity extends AppCompatActivity {
    private CurrencyconverterFragment currencyconversion;
    private MapFragment mapfrag;
    double receivedRate;
    String receivedCurrency;
    String receivedCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        // Retrieve the passed variables from the intent
        receivedRate = getIntent().getDoubleExtra("rate", 0.0); // 0.0 is the default value
        receivedCurrency = getIntent().getStringExtra("selectedcurrency");
        receivedCode = getIntent().getStringExtra("selectedcode");
        currencyconversion = new CurrencyconverterFragment();

        mapfrag = new MapFragment();
        fragments();//run fragments method

    }

    public void setTextview(String string)
    {
        TextView textView = findViewById(R.id.selectedCurrencyText);
        textView.setText("Selected currency to convert to: \n" + string); //sets textview
    }


    public void fragments()
    {
        //fragments
        FragmentManager manager1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = manager1.beginTransaction();
        transaction1.replace(R.id.currencyconvertFragment, currencyconversion);
        transaction1.commit();

        FragmentManager manager2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = manager2.beginTransaction();
        transaction2.replace(R.id.mapsFragment, mapfrag);
        transaction2.commit();//maps fragment

    }
}