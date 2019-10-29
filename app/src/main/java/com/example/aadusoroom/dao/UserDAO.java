package com.example.aadusoroom.dao;

import com.example.aadusoroom.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {
    // Todas estas operaciones se tienen que hacer en una ebra excepto las que devuelven liveData

    @Delete
    int delete(User usuario); //devuelve nº filas borrado

    @Update
    int edit(User usuario); //devuelve nº filas afectads

    @Insert
    long insert(User usuario); //devuelve id autonumerico

    @Query("SELECT * FROM user WHERE id = :id")
    User get(int id);

    @Query("SELECT * FROM user ORDER BY apellidos, nombre, id DESC")
    List<User> getAll();

    @Query("SELECT * FROM user ORDER BY apellidos, nombre, id DESC") // LiveData es una lista que se gestiona sola y en segundo plano.
    LiveData<List<User>> getAllLive();
}
