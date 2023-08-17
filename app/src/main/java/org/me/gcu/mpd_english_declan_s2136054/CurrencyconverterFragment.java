//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import androidx.fragment.app.Fragment;

public class CurrencyconverterFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView currencyView;
    private boolean isToggled = false;
    private ConversionActivity viewActivity;
    private TextView myTextView;
    private EditText myEditText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_currencyconverterfragment, container, false);
        viewActivity = (ConversionActivity) getActivity();

        //setup buttons
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button switchBTN = (Button) v.findViewById(R.id.switchButton);
        switchBTN.setOnClickListener(this);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button convertBTN = (Button) v.findViewById(R.id.convertButton);
        convertBTN.setOnClickListener(this);

        //links to xml
        myEditText = v.findViewById(R.id.currencyEntry);
        myTextView = v.findViewById(R.id.currencyView);
        currencyView = v.findViewById(R.id.currencyView);
        viewActivity.setTextview(viewActivity.receivedCurrency);

        return v;
    }

    @Override
    public void onClick(View view) {

        Double temp = viewActivity.receivedRate;

        EditText currencyEntry = getView().findViewById(R.id.currencyEntry);//gets entered text
        String enteredText = currencyEntry.getText().toString();

        double enteredValue; // Default value

        switch (view.getId()) {
            case R.id.switchButton://if switch button is clicked
                isToggled = !isToggled; // Toggle the boolean value
                if (isToggled) {
                    viewActivity.setTextview("Great British Pound(GBP)");
                    myEditText.setText(null);
                    myTextView.setText(null);
                } else {
                    viewActivity.setTextview(viewActivity.receivedCurrency);
                    myEditText.setText(null);
                    myTextView.setText(null);
                }


                break;
            case R.id.convertButton://if convert button is clicked
                // Perform the currency conversion
                if(isToggled){//to GBP
                    try {
                        enteredValue = Double.parseDouble(enteredText);
                        enteredValue = enteredValue / temp;

                        // Create a DecimalFormat instance with pattern "#.##" for two decimal places
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");

                        // Format the double value to a string with two decimal places
                        String formattedValue = decimalFormat.format(enteredValue);

                        currencyView.setText(formattedValue);//sets text
                    } catch (NumberFormatException e) {
                        // Handle invalid input
                        currencyView.setText("Invalid input");
                    }

                }else{//from GBP
                    try {

                        enteredValue = Double.parseDouble(enteredText);
                        enteredValue = enteredValue * temp;

                        // Create a DecimalFormat instance with pattern "#.##" for two decimal places
                        DecimalFormat decimalFormat = new DecimalFormat("#.00");

                        // Format the double value to a string with two decimal places
                        String formattedValue = decimalFormat.format(enteredValue);

                        currencyView.setText(formattedValue);
                    } catch (NumberFormatException e) {
                        // Handle invalid input
                        currencyView.setText("Invalid input");
                    }
                }

                break;
            default:
                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
