package com.example.datatransferusingbundleobject;

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

        Bundle bundle = getArguments();
        txtRes.setText("Name is " +bundle.getString("name")+" course is "+bundle.getString("course"));
        return view;
    }
}