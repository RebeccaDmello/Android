package com.example.roomdatabaseandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaseandroid.R;
import com.example.roomdatabaseandroid.entity.Student;
import com.example.roomdatabaseandroid.model.VModel;

import java.util.List;

public class ListAdapterpro  extends RecyclerView.Adapter<ListAdapterpro.ProductViewHolder>{

    private VModel productViewModel;

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productNameView, productCreationDateView, productQuantityView;
        private final Button editButton, deleteButton;
        private ProductViewHolder(final View itemView) {
            super(itemView);
            productNameView = itemView.findViewById(R.id.textView_student_name);
            productQuantityView = itemView.findViewById(R.id.textView_student_Id);
            productCreationDateView = itemView.findViewById(R.id.textView_creation_date);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);
            //productViewModel = ViewModelProviders.of().get(ProductViewModel.class);


        }
    }

    private final LayoutInflater mInflater;
    private List<Student> mProducts; // Cached copy of words

    public ListAdapterpro(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_student, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        if (mProducts != null) {
            Student current = mProducts.get(position);
            holder.productNameView.setText("Name: "+current.getStudentName());
            holder.productCreationDateView.setText("Date Created: "+current.getDateTimeFormatted(holder.productCreationDateView.getContext()));
            holder.productQuantityView.setText("Number: "+current.getStudentNo());
        } else {
            // Covers the case of data not being ready yet.
            holder.productNameView.setText("No Product");
        }

    }

    public void  setmProducts(List<Student> products){
        mProducts = products;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        else return 0;
    }

    public Student getProductAt(int position) {
        return mProducts.get(position);
    }
}
