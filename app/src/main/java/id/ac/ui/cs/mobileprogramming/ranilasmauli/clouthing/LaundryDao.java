package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LaundryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Laundry laundry);

    @Query("DELETE FROM Laundries")
    void deleteAll();

    @Delete
    void delete(Laundry laundry);

    @Query("SELECT * FROM Laundries ORDER BY createdAt ASC")
    LiveData<List<Laundry>> getAllLaundries();

    @Query("SELECT * FROM Laundries WHERE status = 0")
    List<Laundry> getPendingLaundries();

    @Query("SELECT * FROM Laundries WHERE id = :id")
    Laundry getById(long id);
}
