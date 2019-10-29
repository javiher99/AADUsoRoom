package com.example.aadusoroom.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aadusoroom.entity.User;
import com.example.aadusoroom.model.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // Varibles
    private Repository repository;

    private LiveData<List<User>> users;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        users = repository.getUsersLive();
    }

    // Metodo obtener listado de usuarios resultado de liveData
    public LiveData<List<User>> getUsers(){
        return users;
    }

    public void insert(User user){
        // Llamamos al respositorio
        // No devolvemos ningun resultado porque al generarlo una hebra no sabemos cuando lo vamos a tener
        repository.insertUser(user);
    }
}
