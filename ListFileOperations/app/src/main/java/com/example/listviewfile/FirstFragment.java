package com.example.listviewfile;

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

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        DogList = ReadDogData(); //reading the csv file

        ListView listViewItems = view.findViewById(R.id.listViewItems);
        listViewItems.setAdapter(new DogAdapter(DogList));

        txtViewAdoptSummary = view.findViewById(R.id.txtViewAdoptionSumary);

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

        return  view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });


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