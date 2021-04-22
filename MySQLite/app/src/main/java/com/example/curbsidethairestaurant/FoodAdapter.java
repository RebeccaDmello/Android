package com.example.curbsidethairestaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.DailyTransactionViewHolder>
{
    private ArrayList<FoodList> foodLists;

    private FoodAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class DailyTransactionViewHolder extends RecyclerView.ViewHolder{


        public TextView txtName;
        public TextView txtDescription;
        public TextView txtPrice;


        public DailyTransactionViewHolder(@NonNull View itemView, OnItemClickListener listener)
        {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPrice = itemView.findViewById(R.id.txtPrice);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(listener!=null)
                    {
                        int position = getAdapterPosition();

                        if(position !=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }

                    }
                }
            });
        }
    }

    public FoodAdapter(ArrayList<FoodList> bkls)
    {
        foodLists = bkls;
    }

    @NonNull
    @Override
    public FoodAdapter.DailyTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewfood,parent,false);
        FoodAdapter.DailyTransactionViewHolder evh = new FoodAdapter.DailyTransactionViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.DailyTransactionViewHolder holder, int position) {
        FoodList currentItem = foodLists.get(position);

        holder.txtName.setText(currentItem.getName());
        holder.txtDescription.setText(currentItem.getDescription());
        holder.txtPrice.setText(currentItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return foodLists.size();
    }
}