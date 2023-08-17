//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AccessWeb { //Class for accessing web and taking xml data

    String result = "";
    String url = null;
    //set global variables

    public String getResult(String urlsource) {


        url = urlsource;

        URL aurl;
        URLConnection yc;
        BufferedReader in = null;
        String inputLine = "";
        //set more variables



        Log.e("MyTag","in run");

        try
        {

            Log.e("MyTag","in try");
            aurl = new URL(url);
            yc = aurl.openConnection();
            in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            while ((inputLine = in.readLine()) != null)
            {
                result = result + inputLine;
                Log.e("MyTag",inputLine);

            }
            in.close();
        }
        catch (IOException ae)
        {
            Log.e("MyTag", "ioexception");
        }

        //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
        int i = result.indexOf(">");
        result = result.substring(i+1);
        //Get rid of the 2nd tag <rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
        i = result.indexOf(">");
        result = result.substring(i+1);
        Log.e("MyTag - cleaned",result);
        //LOWERCASE THE UPPERCASED TAGS </lastBuildDate> and </pubDate>
        String[][] replacements = {
                {"</lastBuildDate>", "</lastbuilddate>"},
                {"<lastBuildDate>", "<lastbuilddate>"},
                {"</pubDate>", "</pubdate>"},
                {"<pubDate>", "<pubdate>"},
                {"</rss>", ""}
        };

        for (String[] replacement : replacements) {
            result = result.replace(replacement[0], replacement[1]);
        }
        //better way of doing this. beforehand two lines of each were randomly required halfway through creation.

        return result; //returns result string
    }

}
