package com.example.tabsrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   List<String> AllTuneNames = new ArrayList<>(Arrays.asList("Beauty and the beast",
           "Lion King","Mary Poppins","Game of Thrones","Ozark"));
   List<Integer> AllTunePics = new ArrayList<>(Arrays.asList(R.drawable.beauty,
           R.drawable.lionking,R.drawable.marypoppins,R.drawable.gameofthrones,R.drawable.ozark));

   //creating three list of tune objects for each of three lists
   List<Tune> AllTunesList = new ArrayList<>();
   List<Tune> MovieTunesList = new ArrayList<>();
   List<Tune> TVShowTunesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddData();

        RecyclerView recyclerViewTunes = findViewById(R.id.recyclerMain);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerViewTunes.setLayoutManager(lm);

        TuneAdapter2 tuneAdapter = new TuneAdapter2(AllTunesList,this);
        recyclerViewTunes.setAdapter(tuneAdapter);

        TabLayout tuneTabs = findViewById(R.id.tabsLayout);
        tuneTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tuneTabs.getSelectedTabPosition()){
                    case 0:
                        tuneAdapter.ChangeData(AllTunesList);
                        break;
                    case 1:
                        tuneAdapter.ChangeData(MovieTunesList);
                        tuneAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        tuneAdapter.ChangeData(TVShowTunesList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void AddData(){
        for (int i = 0; i < AllTuneNames.size(); i++){
            Tune eachTune = new Tune(AllTuneNames.get(i),AllTunePics.get(i));
            AllTunesList.add(eachTune);
        }
        MovieTunesList = AllTunesList.subList(0,3);
        TVShowTunesList = AllTunesList.subList(3,5);
    }
}