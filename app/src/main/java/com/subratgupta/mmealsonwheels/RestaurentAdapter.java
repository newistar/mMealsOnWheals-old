package com.subratgupta.mmealsonwheels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RestaurentAdapter extends RecyclerView.Adapter<RestaurentAdapter.ViewHolder>  {
    private List<RestaurentType> mData;
    private LayoutInflater mInflater;
    private RestaurentAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    RestaurentAdapter(Context context, List<RestaurentType> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public RestaurentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.restaurent_item_raw, parent, false);
        return new RestaurentAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(RestaurentAdapter.ViewHolder holder, int position) {
        RestaurentType restaurent = mData.get(position);
        holder.myNameView.setText("Name: "+restaurent.getName());
        holder.myPhoneView.setText("Phone: "+restaurent.getPhone());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myNameView;
        TextView myPhoneView;

        ViewHolder(View itemView) {
            super(itemView);
            myNameView = itemView.findViewById(R.id.restaurent_name);
            myPhoneView = itemView.findViewById(R.id.restaurent_phone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    RestaurentType getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(RestaurentAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
