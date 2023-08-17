//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ViewspinnerFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, MyAdapter.OnItemClickListener, AdapterView.OnItemSelectedListener{

    String[] exchRates = {"USD", "EUR", "JPY"};
    TextView convertingTo;
    SharedViewModel viewModel;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View v = inflater.inflate(R.layout.frag_viewspinnerfragment, container, false);
        ViewmaincurrenciesActivity viewActivity = (ViewmaincurrenciesActivity) getActivity();
        convertingTo = v.findViewById(R.id.myHeading);
        convertingTo.setText("Select Currency: GBP to ");

        Spinner spinner = (Spinner) v.findViewById(R.id.currency_spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the exchRates array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, exchRates);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        return v;
    }





    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save spinner state if needed
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // Restore spinner state if needed
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);

        String oof = adapterView.getItemAtPosition(i).toString();

        sendDataToReceiver(oof);


    }

    public void sendDataToReceiver(String variable) {
        viewModel.setSharedData(variable);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }













}
