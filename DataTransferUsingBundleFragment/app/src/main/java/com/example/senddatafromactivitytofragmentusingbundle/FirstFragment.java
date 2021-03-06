package com.example.senddatafromactivitytofragmentusingbundle;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    TextView txtRes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        txtRes = view.findViewById(R.id.txtRes);
        Bundle bundle = new Bundle();
        bundle.getString("name");
        bundle.getString("course");
        txtRes.setText("Name is "+getArguments().getString("name")
                +" Course is "+getArguments().get("course"));
        return view;
    }
}