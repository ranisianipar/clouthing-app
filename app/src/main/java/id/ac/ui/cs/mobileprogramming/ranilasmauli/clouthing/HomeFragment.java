package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private ImageButton buttonCreateForm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        buttonCreateForm = homeView.findViewById(R.id.bt_create_form);
        buttonCreateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(homeView.getContext(), FormActivity.class);
                homeView.getContext().startActivity(myIntent);
            };
        });
        return homeView;
    }
}
