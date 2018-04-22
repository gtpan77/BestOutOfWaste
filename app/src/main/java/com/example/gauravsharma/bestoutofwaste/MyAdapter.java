package com.example.gauravsharma.bestoutofwaste;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gauravsharma.bestoutofwaste.Model.UserScrapItem;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<UserScrapItem> dataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView orderIdTextView;
        public TextView statusTextView;
        public ViewHolder(View v) {
            super(v);
            orderIdTextView = v.findViewById(R.id.orderIdTextView);
            statusTextView = v.findViewById(R.id.statusTextView);
        }
    }

    public MyAdapter(List<UserScrapItem> myDataset) {
        dataset = myDataset;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_donations, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        UserScrapItem item = dataset.get(position);
        holder.orderIdTextView.setText(item.id);
        holder.statusTextView.setText(item.status);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
