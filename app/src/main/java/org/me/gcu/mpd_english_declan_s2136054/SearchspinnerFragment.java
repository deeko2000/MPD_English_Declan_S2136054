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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class SearchspinnerFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, MyAdapter.OnItemClickListener, AdapterView.OnItemSelectedListener{
//USING THIS ONE
    String[] options = {"currency name (i.e. dollar)", "3-letter currency code (i.e. CHF)", "country (i.e. China)"};

    TextView convertingTo;
    SharedViewModel viewModel;
    SearchActivity searchActivity;
    private Spinner spinner;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View v = inflater.inflate(R.layout.frag_searchspinnerfragment, container, false);
        searchActivity = (SearchActivity) getActivity();
        convertingTo = v.findViewById(R.id.myHeading);
        convertingTo.setText("Search by: ");

        // Find the spinner and set its adapter
        spinner = v.findViewById(R.id.search_spinner);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        return v;
    }

    public void frags(String oof) {
        searchActivity = (SearchActivity) getActivity();
        SearchingFragment newFragment = new SearchingFragment();

        // Pass the variable 'oof' to the new fragment
        Bundle args = new Bundle();
        args.putString("selected_option", oof);
        newFragment.setArguments(args);

        // Use the FragmentManager to add or replace the fragment
        FragmentTransaction transaction3 = searchActivity.getSupportFragmentManager().beginTransaction();
        transaction3.replace(R.id.searchingFragment, newFragment);
        transaction3.commit();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String oof = adapterView.getItemAtPosition(i).toString();
        frags(oof); // Pass the 'oof' variable to the frags() method
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






    public void sendDataToReceiver(String variable) {
        viewModel.setSharedData(variable);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }













}
