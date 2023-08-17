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

public class ViewListviewFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    LinkedList<ItemClass> items;
    LinkedList<ItemClass> templist = new LinkedList<>(); // Initialize templist here
    private ListView listView;
    private String itemArray[];

    private String temp = " ";
    SharedViewModel viewModel;
    private Double conversionRate;
    private String selectedrate;
    private String selectedcode;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)  {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.frag_viewlistviewfragment, container, false);
            ViewmaincurrenciesActivity viewActivity = (ViewmaincurrenciesActivity) getActivity();

            items = viewActivity.MaincurrList;
            if (viewActivity.MaincurrList == null) {
                Log.d("UI thread", "alist is null");//checks if list from main activity is null
            }


            if (items == null) {
                Log.d("UI thread", "items list is null");//empty checker
            } else {
                new Thread(new ViewListviewFragment.SortTask()).start();
            }


            CustomListAdapter adapter = new CustomListAdapter(getActivity(), templist);//populates listview with strings using array adapter
            if (adapter == null) {
                Log.e("MyTag", "Adapter error"); //checks if adapter is empty
            }
            listView = (ListView) v.findViewById(R.id.list);
            listView.setOnItemClickListener(this);
            listView.setAdapter(adapter);//populates listview using adapter
            return v;
        }else{
            // Inflates the layout for this fragment
            View v = inflater.inflate(R.layout.frag_viewlistviewfragment, container, false);
            ViewmaincurrenciesActivity viewActivity = (ViewmaincurrenciesActivity) getActivity();

            items = viewActivity.MaincurrList;
            if (viewActivity.MaincurrList == null) {
                Log.d("UI thread", "alist is null");//checks if list from main activity is null
            }

            itemArray = new String[1];
            // Create a lock object for synchronization
            Object lock = new Object();

            // Observe the LiveData and wait for its value to be assigned
            synchronized (lock) {
                viewModel.getSharedData().observe(getViewLifecycleOwner(), variable -> {
                    // Handle the received variable
                    // Set the tempString to the received data
                    temp = variable;


                    findMatch(temp);

                    CustomListAdapter adapter = new CustomListAdapter(getActivity(), templist);//populates listview with array of item strings using array adapter
                    listView = (ListView) v.findViewById(R.id.list);
                    listView.setOnItemClickListener(this);
                    listView.setAdapter(adapter); // populates listview using adapter

                    // Notify waiting thread
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                });

                // Wait until LiveData and temp variable are assigned
                while (temp == null) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            return v;
        }

    }

    public void findMatch(String string) {

        for (ItemClass i : items) {
            if(i.toString().contains(string)){
                templist = new LinkedList<>();
                templist.add(i);

            }
        }

    }

    private class SortTask implements Runnable {

        @Override
        public void run() {

            for (ItemClass i : items) {

                if(i.getTitle().contains("GBP") && i.getTitle().contains("USD") ||
                        i.getTitle().contains("GBP") && i.getTitle().contains("EUR") ||
                        i.getTitle().contains("GBP") && i.getTitle().contains("JPY")){
                        templist.add(i);
                }

            }
        }

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

