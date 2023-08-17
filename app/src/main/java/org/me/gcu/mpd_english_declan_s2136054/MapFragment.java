//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView notAvailableTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_maps_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        notAvailableTextView = view.findViewById(R.id.notAvailableTextView);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ConversionActivity viewActivity = (ConversionActivity) getActivity();
        String jsonContent = loadJsonFromRawResource(R.raw.country_currencies);
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        String searchCurrencyCode = viewActivity.receivedCode;
        String temp = null;

        if (searchCurrencyCode.equals("EUR")) {LinkedList<String> euroCountries = new LinkedList<>();
            try {
                JSONArray countriesArray = new JSONArray(jsonContent);
                for (int i = 0; i < countriesArray.length(); i++) {
                    JSONObject countryObject = countriesArray.getJSONObject(i);
                    String currencyCode = countryObject.getString("currency_code");
                    if (currencyCode.equals("EUR")) {
                        String countryName = countryObject.getString("country");
                        euroCountries.add(countryName);
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            // Move the camera to a desired location (e.g., center of Europe)
            double europeCenterLatitude = 50.0;
            double europeCenterLongitude = 10.0;
            LatLng europeCenterLocation = new LatLng(europeCenterLatitude, europeCenterLongitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(europeCenterLocation, 3f)); // Adjust zoom level as needed

            // Start a separate thread for Geocoder operations
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                    for (String i : euroCountries) {
                        try {
                            List<Address> addresses = geocoder.getFromLocationName(i, 1);

                            if (!addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                LatLng countryLocation = new LatLng(address.getLatitude(), address.getLongitude());

                                // Use getActivity().runOnUiThread to update the UI on the main thread
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mMap.addMarker(new MarkerOptions().position(countryLocation).title("Marker in " + address.getCountryName()));
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        if (searchCurrencyCode.equals("BTC")) {
            // Hide the map and show the "Not Available" message
            mMap.clear();
            getView().findViewById(R.id.map).setVisibility(View.GONE);
            notAvailableTextView.setVisibility(View.VISIBLE); // Set visibility for notAvailableTextView
        }else{
            // Show the map and hide the "Not Available" message
            getView().findViewById(R.id.map).setVisibility(View.VISIBLE);
            notAvailableTextView.setVisibility(View.GONE); // Set visibility for notAvailableTextView
            try {

                // Parse the JSON content as a JSONArray
                JSONArray countriesArray = new JSONArray(jsonContent);

                // Get the "currency_code" you are searching for

                // Iterate through the array to find the matching currency code
                for (int i = 0; i < countriesArray.length(); i++) {
                    JSONObject countryObject = countriesArray.getJSONObject(i);
                    String currencyCode = countryObject.getString("currency_code");

                    if (currencyCode.equals(searchCurrencyCode)) {
                        // Found a match, get the country name
                        String countryName = countryObject.getString("country");

                        // Now you can assign the country name to a string
                        String foundCountry = countryName;
                        temp = foundCountry;
                        // Break out of the loop, no need to continue searching
                        break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            // Find coordinates of a country by name (e.g., "China")
            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());

            try {
                List<Address> addresses = geocoder.getFromLocationName(temp, 1);

                if (!((List<?>) addresses).isEmpty()) {
                    Address address = addresses.get(0);
                    LatLng countryLocation = new LatLng(address.getLatitude(), address.getLongitude());

                    mMap.addMarker(new MarkerOptions().position(countryLocation).title("Marker in " + address.getCountryName()));

                    // Move the camera to the marker's position and adjust the zoom level
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(countryLocation, 5f)); // Adjust the zoom level (higher values for closer zoom)

                    // Optionally, you can animate the camera movement
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(countryLocation, 5f), 1000, null); // Adjust duration as needed
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String loadJsonFromRawResource(int resourceId) {
        StringBuilder content = new StringBuilder();

        try (InputStream inputStream = getResources().openRawResource(resourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }


}
