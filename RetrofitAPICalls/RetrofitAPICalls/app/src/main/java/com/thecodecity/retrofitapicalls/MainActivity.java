package com.thecodecity.retrofitapicalls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        rvMain = findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<List<PhotoModel>> listCall = retrofitInterface.getAllPhotos();
        listCall.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                parseData(response.body());
            }
            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
            }
        });
    }

    private void parseData(List<PhotoModel> body) {
        RecycerViewAdapter recycerViewAdapter = new RecycerViewAdapter(body);
        rvMain.setAdapter(recycerViewAdapter);
    }

}
