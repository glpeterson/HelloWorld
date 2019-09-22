package com.example.android;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    private String[] mDataset;
    private int selectedItem = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvItem);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Redraw the old selection and the new
                    notifyItemChanged(selectedItem);
                    selectedItem = getLayoutPosition();
                    Log.d("click", "Clicked on " + selectedItem);
                    notifyItemChanged(selectedItem);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ViewAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);
        holder.itemView.setSelected(selectedItem == position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public int getSelectedItem() { return selectedItem; }

    public void removeSelectedItem() {
        List<String> itemList = Arrays.asList(mDataset);
        ArrayList<String> items = new ArrayList<>();
        items.addAll(itemList);

        items.remove(selectedItem);

        mDataset = items.toArray(new String[items.size()]);
        notifyDataSetChanged();
    }

    public void updateList(String[] myDataset) {
        mDataset = myDataset;
    }

    public String[] getList() {
        return mDataset;
    }
}
