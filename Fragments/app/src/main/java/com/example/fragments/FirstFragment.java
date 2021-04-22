package com.example.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class FirstFragment extends Fragment {

    List<ColorSpec> FragColorSpecs;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Instantiate the spinner in first fragment
        Spinner spinnerColors = view.findViewById(R.id.spinnerColors);
        String[] spinnerData = {"1","2","3"};
        spinnerColors.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, spinnerData));

        ColorSpecViewModel colorSpecViewModel = new ViewModelProvider(requireActivity()).get(ColorSpecViewModel.class);
        colorSpecViewModel.getColors().observe(getViewLifecycleOwner(),(List<ColorSpec> colorSpecs)->{
            Log.d("Fragment", colorSpecs.size()+" total colors in the List");
            spinnerColors.setAdapter(new CustomAdapter(colorSpecs));
            FragColorSpecs = colorSpecs;
        });

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chosenColorVal = FragColorSpecs.get(spinnerColors.getSelectedItemPosition()).getColorVal();
                Bundle bundle = new Bundle();
                bundle.putInt("COLORVAL", chosenColorVal);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle );
            }
        });
    }
}