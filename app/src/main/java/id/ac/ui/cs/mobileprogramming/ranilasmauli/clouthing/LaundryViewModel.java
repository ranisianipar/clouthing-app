package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LaundryViewModel extends AndroidViewModel {
    private LaundryRepository repository;

    private LiveData<List<Laundry>> allLaundries;

    public LaundryViewModel(Application application) {
        super(application);
        repository = new LaundryRepository(application);
        allLaundries = repository.getAllLaundries();
    }

    public LiveData<List<Laundry>> getAllLaundries() {
        return allLaundries;
    }

    public void insert(Laundry laundry) {
        repository.insert(laundry);
    }
}
