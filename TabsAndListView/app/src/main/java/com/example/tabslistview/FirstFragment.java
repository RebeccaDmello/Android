package com.example.tabslistview;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    List<Dog> DogList = new ArrayList();
    String dateStr, resourceName, dogName; //is to maintain the pick up date that has been picked by the user
    TextView txtViewAdoptSummary;

    List<Dog> AllTunesList = new ArrayList<>();
    List<Dog> MovieTunesList = new ArrayList<>();
    List<Dog> TVShowTunesList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        DogList = ReadDogData(); //reading the csv file

        txtViewAdoptSummary = view.findViewById(R.id.txtViewAdoptionSumary);

        ListView listViewItems = view.findViewById(R.id.listViewItems);


        DogAdapter dogAdapter = new DogAdapter(DogList);
        listViewItems.setAdapter(new DogAdapter(DogList.subList(3,5)));

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dogName = DogList.get(i).getDogName();
                resourceName = getResources()
                        .getResourceEntryName(DogList.get(i).getDogpicDrawable()); //using the drawable identifier, it returns the resource name

                LocalDate dob = DogList.get(i).getDob();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d-yyyy");
                dateStr = formatter.format(dob); //formatting the dob in a different format

                txtViewAdoptSummary.setText("Adopted Dog: " + dogName
                        + "\nResource Name: " + resourceName + "\nDob: " + dateStr);

                //Navigate to next Fragment
                Bundle bundle = new Bundle();
                bundle.putString("name", dogName);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });

        TabLayout tuneTabs = view.findViewById(R.id.tabsLayout);
        tuneTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tuneTabs.getSelectedTabPosition()){
                    case 0:
                        listViewItems.setAdapter(new DogAdapter(DogList));
                        Log.d("AllDog","All");
                        break;
                    case 1:
                        listViewItems.setAdapter(new DogAdapter(DogList.subList(0,3)));
                        Log.d("AllMovie","Movie");
                        dogAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        listViewItems.setAdapter(new DogAdapter(DogList.subList(3,5)));
                        Log.d("AllTV","TV");
                        dogAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return  view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onItemClick(Dog dog) {
        String name = dog.getDogName();
        Bundle bundle = new Bundle();
        bundle.putString("Name", name);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Dog> ReadDogData() {
        List<Dog> DogsList = new ArrayList<>();

        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;
            //if you have header line,
            // you must read it first before any loop for parsing is set up

            String headerLine = reader.readLine();
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(","); //comma separated String[]
                int id = Integer.parseInt(row[0]);
                String dogPicName = row[1]; //this is the drawable resource name

                int dogDrawable = getResources().getIdentifier(dogPicName,
                        "drawable", getResources().getResourcePackageName(R.id.imgDog));
                String dogBreed = row[2];
                String dogName = row[3];
                String dogDobStr = row[4];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dob = LocalDate.parse(dogDobStr, formatter);

                Dog eachDog = new Dog(id, dogBreed, dogName, dogDrawable, dob);

                DogsList.add(eachDog);

            }

                TVShowTunesList = DogsList.subList(3,5);
                MovieTunesList = DogsList.subList(0,3);
                AllTunesList = DogsList.subList(0,5);

        } catch (IOException ex) {
            Log.d("ADOPT", ex.getMessage());
        } catch (NumberFormatException ex) {
            Log.d("ADOPT", ex.getMessage());
        } catch (DateTimeException ex) {
            Log.d("ADOPT", ex.getMessage());
        } finally {
            //close the input stream and throw an exception if there is an issue
            try {
                inputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException("Error while closing stream...");
            }
        }

        return DogsList;
    }
}