//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.support.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements OnClickListener
{

    private String result;
    private String lastbuilddate;

    private String url1="";
    private String urlSource="https://www.fx-exchange.com/gbp/rss.xml";
    LinkedList<ItemClass> alist;
    private MainListviewFragment mainlistviewfragment;
    private MaincurrenciesbtnFragment maincurrenciesfragment;
    private SearchcurrenciesbtnFragment searchcurrenciesbtnfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restoring the saved instance state if available
        if (savedInstanceState != null && savedInstanceState.containsKey("listData")) {
            alist = (LinkedList<ItemClass>) savedInstanceState.getSerializable("listData");
        } else {
            // If no saved instance state, initialize a new LinkedList
            alist = new LinkedList<>();
        }

        // Set up the raw links to the graphical components
        mainlistviewfragment = new MainListviewFragment();
        maincurrenciesfragment = new MaincurrenciesbtnFragment();
        searchcurrenciesbtnfragment = new SearchcurrenciesbtnFragment();
        startProgress();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            // Handle the action when the Settings menu item is clicked
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



    public void fragments()
    {

        //fragments

        FragmentManager manager1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = manager1.beginTransaction();
        transaction1.replace(R.id.mainlistviewFragment, mainlistviewfragment);
        transaction1.commit();

        FragmentManager manager2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = manager2.beginTransaction();
        transaction2.replace(R.id.btnsearchforcurrenciesFragment, searchcurrenciesbtnfragment);
        transaction2.commit();

        FragmentManager manager3 = getSupportFragmentManager();
        FragmentTransaction transaction3 = manager3.beginTransaction();
        transaction3.replace(R.id.btnmaincurrenciesFragment, maincurrenciesfragment);
        transaction3.commit();

    }

    public void onClick(View aview)
    {
        startProgress(); //starts accessing of feed and parsing
    }

    public void startProgress()
    {
        // Run network access on a separate thread;
        new Thread(new Task()).start();
    }


    private class Task implements Runnable
    {

        @Override
        public void run()
        {
            AccessWeb aw = new AccessWeb();//Create object to make use of class
            result = aw.getResult(urlSource);//Runs code within class to retrieve resulting string of data
            new Thread(new dataparsing()).start();//runs parsing of data on seperate thread
        }

    }


    public class dataparsing implements Runnable {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() { //creates new ParseData object and sets alist to returned templist.
            ParseData dp = new ParseData();
            alist = dp.parseData(result);
            Log.d("Size of list", String.valueOf(alist.size()));
            lastbuilddate = dp.getlastbuilddate();//retrieves lastbuilddate (not same as pub date)
            // Locate the TextView by its ID
            TextView myTextView = findViewById(R.id.lastbuildView);

            // Populate the TextView with some text
            myTextView.setText(lastbuilddate);
            Log.d("UI thread", "Fetched lastbuilddate is: " + lastbuilddate);//proof it is fetched
            fragments(); //creates fragments AFTER accessing and parsing of data

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listData", alist);
    }
}