package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class LaundryActivity extends AppCompatActivity {

    private LaundryViewModel laundryViewModel;
    private LaundryListAdapter laundryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);

        // init
        laundryViewModel = ViewModelProviders.of(this).get(LaundryViewModel.class);
        laundryListAdapter = new LaundryListAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.rv_laundries);
        final LaundryListAdapter adapter = new LaundryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // add observer to handle synced data (live)
        laundryViewModel.getAllLaundries().observe(this, new Observer<List<Laundry>>() {
            @Override
            public void onChanged(List<Laundry> laundries) {
                // update cache
                laundryListAdapter.setLaundries(laundries);
            }
        });


    }
}
