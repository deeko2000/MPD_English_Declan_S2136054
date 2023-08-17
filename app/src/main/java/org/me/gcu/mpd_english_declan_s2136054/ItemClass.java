//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import java.io.Serializable;

public class ItemClass implements Serializable
{
    private String title;
    private String link;
    private String guid;
    private String pubdate;
    private String description;
    private String categ;
    private String lastbuilddate;
    private String convertedCurrency;
    private String code;
    private double rate;







    //set variables
    //Do constructors

    public ItemClass() {
        title = "";
        description = "";
        link = "";
        pubdate = "";
        categ = "";
        guid = "";
        lastbuilddate="";
        convertedCurrency="";
        code="";
        rate = 0;

    }

    public ItemClass(String atitle,String adescription,
                     String alink, String apubdate,
                     String acateg,String aguid, String alastbuilddate,String aconvertedCurrency, String acode, double arate)
    {
        title = atitle;
        description = adescription;
        link = alink;
        pubdate = apubdate;
        categ = acateg;
        guid = aguid;
        lastbuilddate= alastbuilddate;
        convertedCurrency=aconvertedCurrency;
        rate = arate;
        code=acode;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String atitle)
    {
        title = atitle;//Get and set title
    }

    public String getdescription()
    {
        return description;
    }

    public void setdescription(String adescription)
    {
        description = adescription;//Get and set description
    }

    public String getlink()
    {
        return link;
    }

    public void setlink(String alink)
    {
        link = alink;//Get and set link
    }

    public String getpubdate()
    {
        return pubdate;
    }

    public void setdate(String apubdate)
    {
        pubdate = apubdate;//Get and set pubdate
    }

    public String getcateg()
    {
        return categ;
    }

    public void setcateg(String acateg)
    {
        categ = acateg;//Get and set categ
    }

    public String getguid()
    {
        return guid;
    }

    public void setguid(String aguid)
    {
        guid = aguid;//Get and set guid
    }

    public double getrate()
    {
        return rate;
    }

    public void setrate(double arate)
    {
        rate = arate;//Get and set categ
    }

    public String getconvertedCurrency()
    {
        return convertedCurrency;
    }

    public void setconvertedCurrency(String aconvertedCurrency)
    {
        convertedCurrency = aconvertedCurrency;//Get and set convertedCurrency
    }

    public String getcode()
    {
        return code;
    }

    public void setcode(String acode)
    {
        code = acode;//Get and set convertedCurrency
    }





    public String toString() //returns fields for item object in string format
    {
        String temp;

        temp = title + "\n" + description + "\n" + link + "\n" + pubdate + "\n" + categ + "\n" + guid;//Set and return string containing all details of item object

        return temp;
    }


}// End of class
