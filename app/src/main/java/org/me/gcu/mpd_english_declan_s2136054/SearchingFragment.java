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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class SearchingFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, MyAdapter.OnItemClickListener, AdapterView.OnItemSelectedListener{
    SharedViewModel viewModel;
    SearchActivity searchActivity;
    private Button searchBTN;
    private EditText editText;
    String selection = "";
    String searchedText = "";



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View v = inflater.inflate(R.layout.frag_searchingfragment, container, false);
        searchBTN = v.findViewById(R.id.searchButton);
        searchBTN.setOnClickListener(this);
        editText = v.findViewById(R.id.searchEntry);
        searchActivity = (SearchActivity) getActivity();
        Bundle args = getArguments();
        if (args != null) {
            selection = args.getString("selected_option");
        }

        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private void openFragment(String string) {
        // Create and show the fragment
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle args = new Bundle();
        args.putString("arguement", string);
        SearchListviewFragment fragment = new SearchListviewFragment();
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.searchinglistviewFragment, fragment);
        fragmentTransaction.commit();
    }




    @Override
    public void onClick(View view) {
        boolean tempbool = false;
        searchedText = editText.getText().toString().toLowerCase();

        String code;
        if (selection.contains("currency name")) {

            for (ItemClass i : searchActivity.MaincurrList) {
                String convertedCurrency = i.getconvertedCurrency();

                // Remove text within parentheses
                String cleanedCurrency = convertedCurrency.replaceAll("\\(.*\\)", "").trim();

                if (cleanedCurrency.equalsIgnoreCase(searchedText)) {
                    //CREATE AND POPULATE LISTVIEW
                    tempbool = true;
                    openFragment(cleanedCurrency.toLowerCase());
                }
            }
            if (tempbool == false) {
                Toast.makeText(getActivity(), "Error: Not available!", Toast.LENGTH_SHORT).show();
            }

        } else if (selection.contains("3")) {

            for (ItemClass i : searchActivity.MaincurrList) {
                if (i.getcode().equalsIgnoreCase(searchedText)) {

                    tempbool = true;
                    //CREATE AND POPULATE LISTVIEW AGAIN
                    openFragment(searchedText.toLowerCase());
                }

            }
            if (tempbool == false) {
                Toast.makeText(getActivity(), "Error: Not available!", Toast.LENGTH_SHORT).show();
            }

        } else if (selection.contains("country")) {
            searchActivity.findAndPrintCurrencyCode(searchedText, getResources());
            code = searchActivity.currencyCode;


            for (ItemClass i : searchActivity.MaincurrList) {
                if (i.getcode().equalsIgnoreCase(code)) {

                    tempbool = true;
                    openFragment(code.toLowerCase());
                    code="";
                }
            }

            if (tempbool == false) {

                Toast.makeText(getActivity(), "Error: Not available!", Toast.LENGTH_SHORT).show();
            }
            // Reset the 'searchedText' variable
            searchedText = "";
            searchActivity.setCurrencyCode();
        }

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
