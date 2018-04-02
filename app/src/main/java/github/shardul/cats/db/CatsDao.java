package github.shardul.cats.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import github.shardul.cats.models.Cat;

/**
 * Data access object for cat table
 * Created by Shardul on 31/03/18.
 */
@Dao
public interface CatsDao {

    @Query("SELECT * FROM cat")
    LiveData<List<Cat>> getAll();

    @Query("SELECT * FROM cat WHERE _id LIKE :catid")
    public List<Cat> catsWithId(String catid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(Cat... cats);

    @Query("DELETE FROM cat")
    public void deleteAll();
}
