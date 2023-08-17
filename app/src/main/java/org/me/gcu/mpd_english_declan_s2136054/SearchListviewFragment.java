//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.res.Configuration;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.LinkedList;

public class SearchListviewFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    LinkedList<ItemClass> templist = new LinkedList<>(); // Initialize templist here
    private ListView listView;
    private String itemArray[];

    private String temp = " ";
    SharedViewModel viewModel;
    SearchActivity searchActivity;
    private Double conversionRate;
    private String selectedrate;
    private String selectedcode;
    String receivedVariable;




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        Bundle args = getArguments();
        if (args != null) {
            receivedVariable = args.getString("arguement");
        }
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View v = inflater.inflate(R.layout.frag_searchlistviewfragment, container, false);
        searchActivity = (SearchActivity) getActivity();

        if(receivedVariable.length() == 3){
            String convertedVariable = "(" + receivedVariable + ")";

            for(ItemClass i : searchActivity.MaincurrList){
                if(i.getTitle().toLowerCase().contains(convertedVariable)){
                    templist.add(i);
                }
            }
        }else{
            for(ItemClass i : searchActivity.MaincurrList){
                if(i.getTitle().toLowerCase().contains(receivedVariable)){
                    templist.add(i);
                }
            }
        }

        CustomListAdapter adapter = new CustomListAdapter(getActivity(), templist);
        listView = (ListView) v.findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        return v;

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onClick(View view) {
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemClass selectedItem = (ItemClass) parent.getItemAtPosition(position);

        conversionRate = selectedItem.getrate();
        selectedrate = selectedItem.getconvertedCurrency();
        selectedcode = selectedItem.getcode();
        createActivity(); // Creates activity from click
    }

    public void createActivity()//Creates new activity using intent
    {
        Intent intent = new Intent(getActivity(), ConversionActivity.class);
        // Pass the selected item to the new activity
        intent.putExtra("rate", conversionRate);
        intent.putExtra("selectedcurrency", selectedrate);
        intent.putExtra("selectedcode", selectedcode);
        startActivity(intent);
    }


} // End of listview Fragment

