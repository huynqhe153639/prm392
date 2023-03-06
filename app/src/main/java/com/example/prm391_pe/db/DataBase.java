package com.example.prm391_pe.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Order.class}, version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract OrderDAO orderDAO();
    private static volatile DataBase instance;
    private static final Object mutex = new Object();

    public static DataBase getInstance(Context context) {
        if(instance==null){
            synchronized (mutex){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                                DataBase.class, "OrderDatabase")
                        //.addTypeConverter(DateConverter.class)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }
}

