package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
* This class is used to a connector between DAO and DATABASE
*
* */
public class LaundryRepository {

    private LaundryDao laundryDao;
    private LiveData<List<Laundry>> allLaundries;

    LaundryRepository(Application application) {
        ClouThingDatabase db = ClouThingDatabase.getDatabase(application);
        laundryDao = db.laundryDao();
        allLaundries = laundryDao.getAllLaundries();

    }

    public LiveData<List<Laundry>> getAllLaundries() {
        return allLaundries;
    }

    public void insert (Laundry laundry) {
        new insertAsyncTask(laundryDao).execute(laundry);
    }

    private static class insertAsyncTask extends AsyncTask<Laundry, Void, Void> {

        private LaundryDao mAsyncTaskDao;

        insertAsyncTask(LaundryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Laundry... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
