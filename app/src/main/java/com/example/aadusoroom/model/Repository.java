package com.example.aadusoroom.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.aadusoroom.dao.UserDAO;
import com.example.aadusoroom.database.UserDatabase;
import com.example.aadusoroom.entity.User;

import java.util.List;

public class Repository { // Se meten todas las operaciones contra la base de datos. Todas se inplementan aqui, para usarlas hay que solicitarlas

    private UserDAO userDAO;
    private LiveData<List<User>> usersLive;

    public Repository(Context contexto){
        UserDatabase db = UserDatabase.getDatabase(contexto);
        userDAO = db.getUserDAO();
        // Se lanza y al estar en segundo plano no hay que ejecutalo como ebra y te devuelve las cosas poco a poco
        usersLive = userDAO.getAllLive();
    }

    private void populateDb() {
        //No se debe meter la creacion del user dentro del for. Pero aqui lo hacemos
        for (int i=0; i<100; i++){
            User user = new User();
            user.setApellidos("Franco " + i);
            user.setNombre("Francisco " + i);

            insertUser(user);
        }
    }

    public LiveData<List<User>> getUsersLive(){
        return usersLive;
    }

    public void insertUser(User user){
        new InsertThread().execute(user);
    }

    private class InsertThread extends AsyncTask<User, Void, Void>{

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            Log.v("xyz", users[0].toString());
            return null;
        }
    }
}
