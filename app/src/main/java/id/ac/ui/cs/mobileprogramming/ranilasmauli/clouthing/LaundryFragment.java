package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LaundryFragment extends Fragment implements LaundryListAdapter.ItemClickListener {

    LaundryListAdapter adapter;
    List<Laundry> laundries = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View laundryView = inflater.inflate(R.layout.fragment_laundry, container, false);
        seed();

        RecyclerView recyclerView = laundryView.findViewById(R.id.rv_laundries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        Log.d("LAUNDRIES RV", "onCreateView: "+ laundryView.findViewById(R.id.rv_laundries));

        // this.getActivity() -> MainActivity
        adapter = new LaundryListAdapter(this.getActivity(), laundries, this);

        recyclerView.setAdapter(adapter);

//        // add observer to handle synced data (live)
//        laundryViewModel.getAllLaundries().observe(this, new Observer<List<Laundry>>() {
//            @Override
//            public void onChanged(List<Laundry> laundries) {
//                // update cache
//                laundryListAdapter.setLaundries(laundries);
//            }
//        });

        return laundryView;
    }

    void seed() {
        for (int x = 0; x < 5; x++) {
            Laundry dummy = new Laundry();
            dummy.setTitle("Dummy "+x);
            dummy.setAmount(x+3);
            laundries.add(dummy);
        }

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this.getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}