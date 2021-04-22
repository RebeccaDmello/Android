package com.example.roomdatabase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.roomdatabase.Entity.Student;
import com.example.roomdatabase.R;
import java.util.List;
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView studentNameView, studentDescriptionView, studentSID;
        private final Button editButton, deleteButton;
        private StudentViewHolder(final View itemView) {
            super(itemView);
            studentNameView = itemView.findViewById(R.id.textView_student_name);
            studentDescriptionView = itemView.findViewById(R.id.textView_student_dept);
            studentSID = itemView.findViewById(R.id.textView_sId);
            editButton = itemView.findViewById(R.id.btnEdit);
            deleteButton = itemView.findViewById(R.id.btnDel);
        }
    }
    private final LayoutInflater mInflater;
    private List<Student> mStudents;
    public StudentAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, int position) {
        if (mStudents != null) {
            Student current = mStudents.get(position);
            holder.studentNameView.setText("Name: "+current.getStudentName());
            holder.studentDescriptionView.setText("Department: "+current.getStudentDept());
            holder.studentSID.setText("Student ID: "+current.getSID());
        } else {
            holder.studentNameView.setText("No Student");
        }
    }

    public void  setStudents(List<Student> students){
        mStudents = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mStudents != null)
            return mStudents.size();
        else return 0;
    }

    public Student getStudentAt(int position) {
        return mStudents.get(position);
    }
}
