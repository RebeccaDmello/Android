package com.example.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listViewColors = view.findViewById(R.id.listViewColors);

        ColorSpecViewModel colorSpecViewModel = new ViewModelProvider(requireActivity()).get(ColorSpecViewModel.class);
        colorSpecViewModel.getColors().observe(getViewLifecycleOwner(), ColorList ->{
            listViewColors.setAdapter(new CustomAdapter(ColorList));
            //if spinner use setOnItemSelectedListener
            listViewColors.setOnItemClickListener((AdapterView<?> adapterView, View Lview, int i, long l)->{
                Bundle bundle = new Bundle();
                bundle.putInt("COLORVAL", ColorList.get(i).getColorVal());
                NavHostFragment.findNavController(ListViewFragment.this).
                        navigate(R.id.action_listViewFragment_to_SecondFragment, bundle);
            });
        });
    }
}