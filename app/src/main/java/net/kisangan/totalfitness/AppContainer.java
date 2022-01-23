package net.kisangan.totalfitness;

import android.content.Context;

import androidx.room.Room;

import net.kisangan.totalfitness.data.AppDatabase;

public class AppContainer {
    public AppDatabase db;

    public AppContainer(Context context) {
        this.db = Room.databaseBuilder(context,
                AppDatabase.class, "pre-alpha").build();
    }
}
