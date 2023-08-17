//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SearchActivity extends AppCompatActivity {

    private SearchspinnerFragment searchspinnerfragment;

    LinkedList<ItemClass> MaincurrList;
    static String currencyCode = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchspinnerfragment = new SearchspinnerFragment();
        fragments();
        setTemplist();
    }

    public void fragments() {
        FragmentManager manager2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = manager2.beginTransaction();
        transaction2.replace(R.id.searchspinerFragment, searchspinnerfragment);
        transaction2.commit();
    }

    public void setTemplist(){
        Intent intent = getIntent();

        MaincurrList = new LinkedList<>(
                (List<ItemClass>) intent.getSerializableExtra("MyListKey"));
    }

    public static void findAndPrintCurrencyCode(String countryName, Resources resources) {
        try {
            InputStream inputStream = resources.openRawResource(R.raw.country_currencies);
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();

            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }

            scanner.close();

            String jsonString = builder.toString();

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String country = jsonObject.getString("country");

                if (country.equalsIgnoreCase(countryName)) {
                    currencyCode = jsonObject.getString("currency_code");
                    Log.d("Mytag", "Currency Code for " + countryName + " is " + currencyCode);
                    break; // Exit the loop if a match is found
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setCurrencyCode(){
        currencyCode = null;
    }


}
