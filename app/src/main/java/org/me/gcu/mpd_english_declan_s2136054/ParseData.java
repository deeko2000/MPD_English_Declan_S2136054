//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseData //class for parsing data added to result variable
{
    LinkedList <ItemClass> templist;
    ItemClass item;
    Boolean tempbool = false;
    String lastbuilddate;


    //set global variables
    //Do constructors

    public ParseData() {
        templist = null;
        item = null;
    }

    public LinkedList<ItemClass> parseData(String dataToParse) {

        templist  = new LinkedList<>(); //temp list to return back to alist in mainactivity


        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( dataToParse ) );
            int eventType = xpp.getEventType(); //xmlpullparser magic

            while (eventType != XmlPullParser.END_DOCUMENT) //while not at end of xml doc do this
            {


                // Found a start tag
                if(eventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("lastbuilddate"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.e("MyTag","lastbuilddate is " + temp);
                        lastbuilddate = temp;

                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("ttl"))
                    {
                        tempbool = true; //do nothing with line

                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {

                        Log.e("MyTag","Item Start Tag found");
                        item = new ItemClass(); //create item and begin adding to it using setters
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("title") && tempbool)
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.e("MyTag","Title is " + temp);
                        item.setTitle(temp);


                        int slashIndex = temp.indexOf('/');

                        if (slashIndex != -1) {
                            // Extract the substring after the slash
                            String countrywithoutgbp = temp.substring(slashIndex + 1);

                            // Trim any leading or trailing spaces
                            countrywithoutgbp = countrywithoutgbp.trim();

                            // Print the extracted substring
                            item.setconvertedCurrency(countrywithoutgbp);
                            Log.e("MyTag","convertedCurrency is " + countrywithoutgbp);

                            String pattern = "\\((.*?)\\)";
                            Pattern regexPattern = Pattern.compile(pattern);
                            Matcher matcher = regexPattern.matcher(countrywithoutgbp);

                            if (matcher.find()) {
                                String extractedText = matcher.group(1);
                                System.out.println("COUNTRY CODE IS: " + extractedText);
                                item.setcode(extractedText);
                            } else {
                                System.out.println("No text between brackets found.");
                            }



                        } else {
                            System.out.println("Slash not found in the text.");
                        }


                    }
                    else
                        // Check which Tag we have

                        if (xpp.getName().equalsIgnoreCase("description") && tempbool)
                        {
                            boolean istrue = false; //boolean for location field
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            // Do something with text
                            Log.e("MyTag","Description is " + temp);
                            item.setdescription(temp);//description for item is set here

                            //Assigning rate for colors
                            // Define the regular expression pattern
                            String patternString = "=\\s*(\\d+(\\.\\d+)?)";

                            // Compile the pattern
                            Pattern pattern = Pattern.compile(patternString);

                            // Create a Matcher object
                            Matcher matcher = pattern.matcher(temp);

                            // Find the match
                            if (matcher.find()) {
                                // Extract the number found in the match
                                String extractedNumber = matcher.group(1);
                                Log.e("MyTag","Descriptions extracted num is: " + extractedNumber);
                                Log.e("MyTag","rate is: " + extractedNumber);
                                double d = Double.parseDouble(extractedNumber);
                                item.setrate(d);

                            } else {
                                System.out.println("Number not found after '='.");
                            }

                           }
                        else
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("link") && tempbool)
                            {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                // Do something with text
                                Log.e("MyTag","Link is " + temp);
                                item.setlink(temp);
                            }
                            else
                                // Check which Tag we have
                                if (xpp.getName().equalsIgnoreCase("pubDate") && tempbool)
                                {
                                    // Now just get the associated text
                                    String temp = xpp.nextText();
                                    // Do something with text
                                    Log.e("MyTag","Date is " + temp);
                                    item.setdate(temp);
                                }
                                else
                                    // Check which Tag we have
                                    if (xpp.getName().equalsIgnoreCase("category") && tempbool)
                                    {
                                        // Now just get the associated text
                                        String temp = xpp.nextText();
                                        // Do something with text
                                        Log.e("MyTag","Category is " + temp);
                                        item.setcateg(temp);
                                    }

                }
                else
                if(eventType == XmlPullParser.END_TAG) //if end of tag do this stuff
                {
                    if (xpp.getName().equalsIgnoreCase("item"))//if tag is <item> list item object
                    // fields and add item to list
                    {
                        Log.e("MyTag","item is " + item.toString());
                        templist.add(item);

                    }
                    if (xpp.getName().equalsIgnoreCase("rss")) //if tag equals <rss> get & declare list size
                    // as it is end of document and
                    {
                        int size;
                        size = templist.size();
                        Log.e("MyTag","item collection size is " + size);
                    }


                }
                // Get the next event
                eventType = xpp.next();
                }

             // End of while

            //return alist;
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");


        return templist;


    }

    public String getlastbuilddate() {
        return lastbuilddate;
    }
}// End of class








