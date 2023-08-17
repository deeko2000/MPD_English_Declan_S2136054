//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.LinkedList;

public class MainListviewFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    LinkedList<ItemClass> items;
    private ListView listView;
    MainActivity mainActivity;
    private Double conversionRate;
    private String selectedrate;
    private String selectedcode;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.frag_mainlistviewfragment, container, false);
        mainActivity = (MainActivity) getActivity();

        items = mainActivity.alist;
        if (mainActivity.alist == null) {
            Log.d("UI thread", "alist is null");
        }

        CustomListAdapter adapter = new CustomListAdapter(getActivity(), items);
        listView = v.findViewById(R.id.list);
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

        // Now you can access the properties of the selected item
        conversionRate = selectedItem.getrate();
        selectedrate = selectedItem.getconvertedCurrency();
        selectedcode = selectedItem.getcode();

        Log.d("Mytag", "SAVED RATE IS: " + conversionRate);
        Log.d("Mytag", "SELECTED RATE IS: " + selectedrate);
        Log.d("Mytag", "SELECTED CODE IS: " + selectedcode);
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

