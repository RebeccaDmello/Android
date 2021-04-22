package com.example.roomdatabase.Database;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.roomdatabase.DAO.StudentDAO;
import com.example.roomdatabase.Entity.Student;
import java.util.List;

public class StudentRepo {
    private StudentDAO studentDAO;
    private LiveData<List<Student>> AllStudents;

   public StudentRepo(Application application) {
       StudentDatabase db = StudentDatabase.getDatabase(application);
       studentDAO = db.studentDAO();
       AllStudents = studentDAO.getAllStudent();
    }
    public LiveData<List<Student>> getAllStudents() {
        return AllStudents;
    }
    public void insert (Student student) {
        new insertAsyncTask(studentDAO).execute(student);
    }
    public void delete(Student student) {
        new DeleteStudentAsyncTask(studentDAO).execute(student);
    }
    public void update(Student student) {
        new UpdateStudentAsyncTask(studentDAO).execute(student);
    }
    private static class insertAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO TaskDao;
        insertAsyncTask(StudentDAO dao) {
            TaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Student... params) {
            TaskDao.insert(params[0]);
            return null;
        }
    }
    private static class UpdateStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDAO;
        private UpdateStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDAO.update(students[0]);
            return null;
        }
    }

    private static class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDao;
        private DeleteStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDao = studentDAO;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.delete(students[0]);
            return null;
        }
    }
}
