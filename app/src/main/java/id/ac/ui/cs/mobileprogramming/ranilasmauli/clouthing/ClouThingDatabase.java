package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Laundry.class}, version = 1)
public abstract class ClouThingDatabase extends RoomDatabase {

    public abstract LaundryDao laundryDao();

    private static volatile ClouThingDatabase INSTANCE;

    static ClouThingDatabase getDatabase(final Context context) {
        // singleton
        if (INSTANCE == null) {
            synchronized (ClouThingDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClouThingDatabase.class, "clouthing_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
