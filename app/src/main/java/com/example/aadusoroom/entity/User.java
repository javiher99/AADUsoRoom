package com.example.aadusoroom.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user") //crea la tabla usuario en la BD
public class User {

    @PrimaryKey(autoGenerate = true)
    //campo clave primary y se rellena automaticamente, cuyo nombre es id
    private long id;

    @ColumnInfo(name = "nombre") //no haria falta poner lo de (name="nombre") al lado de @ColumnInfo
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}