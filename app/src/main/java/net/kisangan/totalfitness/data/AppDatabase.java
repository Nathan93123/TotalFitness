package net.kisangan.totalfitness.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import net.kisangan.totalfitness.data.dao.UserDao;
import net.kisangan.totalfitness.data.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}