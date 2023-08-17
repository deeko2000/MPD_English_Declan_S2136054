//STUDENT NAME: DECLAN ENGLISH
//STUDENT ID: S2136054

package org.me.gcu.mpd_english_declan_s2136054;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] data;
    private OnItemClickListener onItemClickListener;
    private int selectedItem = RecyclerView.NO_POSITION; // Initially, no item is selected

    public MyAdapter(String[] data, OnItemClickListener onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = data[position];
        holder.textView.setText(item);
        holder.itemView.setSelected(selectedItem == position);
        holder.itemView.setOnClickListener(v -> {
            setSelectedItem(position);
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setSelectedItem(int position) {
        int previousSelectedItem = selectedItem;
        selectedItem = position;
        notifyItemChanged(previousSelectedItem);
        notifyItemChanged(selectedItem);
    }
}
