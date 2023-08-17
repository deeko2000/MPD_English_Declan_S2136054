//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.LinkedList;



public class MaincurrenciesbtnFragment extends Fragment implements View.OnClickListener
{


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_maincurrenciesbtnfragment, container, false);

        Button b = (Button) v.findViewById(R.id.maincurrenciesButton);
        b.setOnClickListener(this);//sets button onclicklistener

        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onClick(View v) {//handles onclick
        MainActivity mainActivity = (MainActivity) getActivity();
        createActivity(mainActivity.alist);
    }

    public void createActivity(LinkedList<ItemClass> mylist)//Creates new activity using intent
    {
        Log.d("Mytag","Activity Created view method running");
        Intent intent = new Intent(getActivity(), ViewmaincurrenciesActivity.class);
        intent.putExtra("MyListKey", new LinkedList<ItemClass>(mylist));
        startActivity(intent);//passes variables to new activity and starts activity
    }


} // End of view Fragment

