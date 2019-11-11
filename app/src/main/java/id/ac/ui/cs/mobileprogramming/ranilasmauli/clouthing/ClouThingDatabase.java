package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/*
* Export scheme: https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we
* */

@Database(entities = {Laundry.class}, version = 1, exportSchema = false)
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

    // FOR DEVELOPMENT PURPOSE
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LaundryDao dao;

        PopulateDbAsync(ClouThingDatabase db) {
            dao = db.laundryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();
            Laundry laundry = new Laundry();
            laundry.setTitle("NGAKAK");
            dao.insert(laundry);
            laundry = new Laundry();
            laundry.setTitle("AYO DONG");
            dao.insert(laundry);
            return null;
        }
    }
}
