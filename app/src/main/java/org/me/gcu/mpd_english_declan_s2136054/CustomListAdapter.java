//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private LinkedList<ItemClass> items;

    public CustomListAdapter(Context context, LinkedList<ItemClass> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_listview, parent, false);
        }

        TextView itemNameTextView = convertView.findViewById(R.id.label);
        ItemClass item = (ItemClass) getItem(position);
        itemNameTextView.setText(item.toString());

        // Calculate the percentage difference
        double percentageDifference = ((item.getrate() - 1) / 1) * 100;

        // Apply different colors based on strength of currency against GBP
        if (percentageDifference >= 15) {  // VERY STRONG
            convertView.setBackgroundColor(context.getResources().getColor(R.color.green));  // Adjust color as needed
            itemNameTextView.setTextColor(Color.BLACK);  // Set text color to black for better readability
        } else if (percentageDifference <= -15) {  // VERY WEAK
            convertView.setBackgroundColor(context.getResources().getColor(R.color.red));  // Adjust color as needed
            itemNameTextView.setTextColor(Color.WHITE);  // Set text color to white for better readability
        } else if (percentageDifference > -15 && percentageDifference < 0) {  // WEAK
            convertView.setBackgroundColor(context.getResources().getColor(R.color.orange));  // Adjust color as needed
            itemNameTextView.setTextColor(Color.BLACK);  // Set text color to black for better readability
        } else {  // MODERATE
            convertView.setBackgroundColor(context.getResources().getColor(R.color.yellow));  // Adjust color as needed
            itemNameTextView.setTextColor(Color.BLACK);  // Set text color to black for better readability
        }

        return convertView;
    }

}
