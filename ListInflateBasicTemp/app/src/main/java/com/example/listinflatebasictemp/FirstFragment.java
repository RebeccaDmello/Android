package com.example.listinflatebasictemp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class FirstFragment extends Fragment {

    TextView txtName;
    ImageView imgStar;
    List<Student> listStudent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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

        ListView listViewStudent = view.findViewById(R.id.listViewStudents);
        TextView txtStdName = view.findViewById(R.id.txtViewStdName);

        StudentSpecViewModel studentSpecViewModel = new ViewModelProvider(requireActivity()).get(StudentSpecViewModel.class);
        studentSpecViewModel.getStudents().observe(getViewLifecycleOwner(), StudentList ->{
            listViewStudent.setAdapter(new StudentAdapter(StudentList));
            listViewStudent.setOnItemClickListener((AdapterView<?> adapterView, View Lview, int i, long l )->{
                String itemValue = StudentList.get(i).getStdCourse();
                Bundle bundle = new Bundle();
                bundle.putString("STUDENTNAME", itemValue);
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);
            });
        });



//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }
}