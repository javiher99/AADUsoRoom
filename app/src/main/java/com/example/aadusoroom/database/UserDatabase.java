package com.example.aadusoroom.database;

import android.content.Context;

import com.example.aadusoroom.dao.UserDAO;
import com.example.aadusoroom.entity.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO getUserDAO();

    private static UserDatabase INSTANCIA;

    public static UserDatabase getDatabase(final Context context){
        if(INSTANCIA == null){
            synchronized (UserDatabase.class){ //synchronized hace que no se ejecuten al mismo tiempo
                if(INSTANCIA == null){
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,"user.db").build();
                }
            }
        }
        return INSTANCIA;
    }

}

