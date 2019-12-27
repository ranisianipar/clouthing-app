package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing.OpenGl.OpenGLView;

public class AboutMeFragment extends Fragment {

    private OpenGLView glView;

    public AboutMeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        glView = new OpenGLView(this.getActivity()); //I believe you may also use getActivity().getApplicationContext();
//        return inflater.inflate(R.layout.fragment_about_me, container, false);
        return glView;
    }


}
