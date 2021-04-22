package com.thecodecity.retrofitapicalls;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RecycerViewAdapter extends RecyclerView.Adapter<RecycerViewAdapter.RecyclerHolder> {

    List<PhotoModel> models;
    Context context;


    public RecycerViewAdapter(List<PhotoModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public RecycerViewAdapter(List<PhotoModel> body) {
        this.models = body;
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_row, viewGroup, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int i) {
        recyclerHolder.textView.setText(models.get(i).tvtitle);
        recyclerHolder.imageView.setImageURI(Uri.parse(models.get(i).fullUrl));

//        InputStream is = (InputStream) new URL(Uri.parse(models.get(i).fullUrl));
//        Drawable d = Drawable.createFromStream(is,"src");
//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(models.get(i).getFullUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(recyclerHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
