package com.paygilant.myshop.OnLine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.paygilant.myshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ImageItem> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
//    private PaygilantScreenListener paygilantScreenListener;
    private  Context context;
    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<ImageItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
//        this.paygilantScreenListener = paygilantScreenListener;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageItem imageItem = mData.get(position);
        holder.textTitle.setText(imageItem.getTitle());
        holder.textPrice.setText("$ "+imageItem.getPrice());
        Picasso.get().load(imageItem.getImage())
                .error(R.drawable.br1)
                .into(holder.myImageView);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;
        TextView textTitle;
        TextView textPrice;
        ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.imagegrid);
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);

            itemView.setOnClickListener(this);
//            itemView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    paygilantScreenListener.onTouch(v,event);
//                    return false;
//                }
//            });

        }

        @Override
        public void onClick(View view) {

            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    public ImageItem getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setFilter(ArrayList<ImageItem> newList){
        mData=new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}