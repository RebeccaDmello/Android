package com.example.tabsrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TuneAdapter2 extends RecyclerView.Adapter<TuneAdapter2.TuneViewHolder2> {

    List<Tune> tuneList;
    Context context;
    int CurrentPlayingInd = -1;

    public TuneAdapter2(List<Tune> tuneList, Context context){
        this.tuneList = tuneList;
        this.context = context;
    }

    public void ChangeData(List<Tune> newtuneList){
        this.tuneList = newtuneList;
        CurrentPlayingInd = -1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TuneViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tuneitem2,parent,false);
        TuneViewHolder2 myHolder = new TuneViewHolder2(itemView);
        myHolder.tuneTextView = itemView.findViewById(R.id.txtViewTune2);
        myHolder.tuneImageView = itemView.findViewById(R.id.imgViewTune2);
        myHolder.tunePlayPauseImageView = itemView.findViewById(R.id.imgViewPlayPause);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder2 holder, int position) {
        holder.tuneTextView.setText(tuneList.get(position).getTuneName());
        holder.tuneImageView.setImageResource(tuneList.get(position).getTunePic());
        if(CurrentPlayingInd == position){
            holder.tunePlayPauseImageView.setImageResource(R.drawable.pause);
        }else{
            holder.tunePlayPauseImageView.setImageResource(R.drawable.play);
        }

        holder.tunePlayPauseImageView.setOnClickListener((view)->{
            if(CurrentPlayingInd == position){
                CurrentPlayingInd = -1;
                notifyDataSetChanged();
            }else{
                CurrentPlayingInd = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tuneList.size();
    }


    public class TuneViewHolder2 extends RecyclerView.ViewHolder{
        TextView tuneTextView;
        ImageView tuneImageView;
        ImageView tunePlayPauseImageView;

       public TuneViewHolder2(@NonNull View itemView){
           super(itemView);
       }
    }
}
