package com.example.roomdatabaseandroid.databaseRoom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdatabaseandroid.DataACCOBJ.StudentDAO;
import com.example.roomdatabaseandroid.entity.Student;

import java.util.List;

public class StudentRepo {

    private StudentDAO studentDAO;
    private LiveData<List<Student>> mAllProducts;

    public StudentRepo(Application application) {
        StudentDatabase db = StudentDatabase.getDatabase(application);
        studentDAO = db.studentDAO();
        mAllProducts = studentDAO.getAllStudent();
    }

    public LiveData<List<Student>> getAllProducts() {
        return mAllProducts;
    }


    public void insert (Student product) {
        new insertAsyncTask(studentDAO).execute(product);
    }

    public void delete(Student product) {
        new DeleteProductAsyncTask(studentDAO).execute(product);
    }

    public void update(Student product) {
        new UpdateProductAsyncTask(studentDAO).execute(product);
    }

    private static class insertAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDAO mAsyncTaskDao;

        insertAsyncTask(StudentDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO productDAO;

        private UpdateProductAsyncTask(StudentDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Student... products) {
            productDAO.update(products[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO productDao;

        private DeleteProductAsyncTask(StudentDAO productDAO) {
            this.productDao = productDAO;
        }

        @Override
        protected Void doInBackground(Student... products) {
            productDao.delete(products[0]);
            return null;
        }
    }
}
