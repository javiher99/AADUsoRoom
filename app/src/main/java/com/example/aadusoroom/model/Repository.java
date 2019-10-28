package com.example.aadusoroom.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aadusoroom.dao.UserDAO;
import com.example.aadusoroom.database.UserDatabase;
import com.example.aadusoroom.entity.User;

import java.util.List;

public class Repository {
    private UserDAO userDAO;
    private List<User> users;

    public Repository(Context contexto){
        UserDatabase db = UserDatabase.getDatabase(contexto);
        userDAO = db.getUserDAO();
        fetchUsers();
    }

    private void populateDb() {
        //No se debe meter la creacion del user dentro del for. Pero aqui lo hacemos
        for (int i=0; i<100; i++){
            User user = new User();
            user.setApellidos("Franco "+i);
            user.setNombre("Francisco "+i);
            insertUser(user);
        }
    }

    public List<User> getUsers(){
        return users;
    }

    public void insertUser(User user){
        new InsertThread().execute(user);
    }

    private void fetchUsers(){
        new FetchThread().execute();
    }

    private class InsertThread extends AsyncTask<User, Void, Void>{

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            Log.v("xyz", users[0].toString());
            return null;
        }
    }

    private class FetchThread extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            users = userDAO.getAll();
            if(users.size() < 1){
                populateDb();
            }
        }
    }
}
