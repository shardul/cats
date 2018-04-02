package github.shardul.cats.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import github.shardul.cats.models.Cat;

/**
 * Room Database
 * Created by Shardul on 31/03/18.
 */
@Database(entities = {Cat.class}, version = 1, exportSchema = false)
public abstract class CatsDatabase extends RoomDatabase {
    public abstract CatsDao catsDao();
}
