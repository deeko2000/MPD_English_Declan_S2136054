//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class ViewmaincurrenciesActivity extends AppCompatActivity{

    private ViewListviewFragment viewlistviewfragment;
    private ViewspinnerFragment viewspinnerfragment;
    LinkedList<ItemClass> MaincurrList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmaincurrencies);


        viewlistviewfragment = new ViewListviewFragment();
        viewspinnerfragment = new ViewspinnerFragment();
        setTemplist();
        fragments();


    }

    public void fragments()
    {
            FragmentManager manager1 = getSupportFragmentManager();
            FragmentTransaction transaction1 = manager1.beginTransaction();
            transaction1.replace(R.id.viewlistviewFragment, viewlistviewfragment);
            transaction1.commit();

            FragmentManager manager2 = getSupportFragmentManager();
            FragmentTransaction transaction2 = manager2.beginTransaction();
            transaction2.replace(R.id.optionalspinerFragment, viewspinnerfragment);
            transaction2.commit();

        //fragments
    }

    public void setTemplist(){
        Intent intent = getIntent();

        MaincurrList = new LinkedList<>(
                (List<ItemClass>) intent.getSerializableExtra("MyListKey"));
    }










}