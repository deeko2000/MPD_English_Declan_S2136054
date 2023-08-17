//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {//shares data between listview & spinner
    private MutableLiveData<String> sharedData = new MutableLiveData<>();

    public void setSharedData(String data) {
        sharedData.setValue(data);
    }

    public LiveData<String> getSharedData() {
        return sharedData;
    }
}

