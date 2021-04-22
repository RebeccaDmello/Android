package com.example.recyclerinflatebasictemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.TuneViewHolder2> {

    List<Student> studentList;
    Context context;
    boolean bigger = false;
    private ItemClickListener studentListener;

    public StudentAdapter(List<Student> studentList, ItemClickListener studentListener){
        this.studentList = studentList;
//        this.context = context;
        this.studentListener = studentListener;
    }

    //Inflating external layout
    @NonNull
    @Override
    public TuneViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_external_item, parent,false);
        TuneViewHolder2 myHolder = new TuneViewHolder2(itemView);
        myHolder.txtStdName = itemView.findViewById(R.id.txtStdName);
        myHolder.imgId = itemView.findViewById(R.id.imgStd);
        return myHolder;
    }

    //setting data from dataholder
    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder2 holder, int position) {
        holder.txtStdName.setText(studentList.get(position).getStdName());
        holder.imgId.setImageResource(studentList.get(position).getImgId());

        holder.txtStdName.setOnTouchListener(new CustomTouchListener(context){
            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Log.d("GESTURE","Detected single click on the TextView");
                if (holder.txtStdName.getCurrentTextColor()
                        != Color.parseColor("blue")){
                    holder.txtStdName.setTextColor(Color.parseColor("blue"));
                } else {
                    holder.txtStdName.setTextColor(Color.parseColor("black"));
                }
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTURE","Detected onTouch on the TextView...");
                return super.onTouch(view, motionEvent);
            }
        });

//        holder.imgId.setOnTouchListener(new CustomTouchListener(context){
//            @Override
//            public void onSingleClick() {
//                super.onSingleClick();
//                Log.d("GESTURE","Detected single click on the ImageView");
//            }
//        });

//        holder.imgId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, SecondFragment.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });

        //row click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentListener.onItemClick(studentList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class TuneViewHolder2 extends RecyclerView.ViewHolder{
        TextView txtStdName;
        ImageView imgId;
        public TuneViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }

    //Interface for ItemClickListener
    public interface  ItemClickListener{
        public void onItemClick(Student std);
    }
}
