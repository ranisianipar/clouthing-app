package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final long START_TIME_IN_MILLIS = 60000;
    private static final String TIME_LEFT_TAG = "timelefttag";
    private static final String IS_TIME_RUNNING_TAG = "istimerunning";

    private CountDownTimer countDownTimer;

    private TextView tvRemainingTime;
    private Button btStartPause;
    private Button btReset;

    // TODO: Rename and change types of parameters
    private long timeLeftMillis = START_TIME_IN_MILLIS;
    private boolean isTimerRunning;

    private OnFragmentInteractionListener mListener;

    public TimerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TimerFragment newInstance() {
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View timerView = inflater.inflate(R.layout.fragment_timer, container, false);

        btStartPause = timerView.findViewById(R.id.bt_start_pause);
        btReset = timerView.findViewById(R.id.bt_reset);

        if (savedInstanceState != null) {
            // belom ngecek, harusnya si bener
             timeLeftMillis = savedInstanceState.getInt(TIME_LEFT_TAG);
             isTimerRunning = savedInstanceState.getBoolean(IS_TIME_RUNNING_TAG);
            // Lakukan ssesuatu dengan someStateValue jika diperlukan
        }

        btStartPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isTimerRunning) {
                     pauseTimer();
                } else {
                     startTimer();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 resetTimer();
            }
        });

        updateCountDownText();
        return timerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Menyimpan data tertentu (String) ke Bundle
         savedInstanceState.putLong(TIME_LEFT_TAG, timeLeftMillis);
         savedInstanceState.putBoolean(IS_TIME_RUNNING_TAG, isTimerRunning);

        // Selalu simpan pemanggil superclass di bawah agar data di view tetap tersimpan
        super.onSaveInstanceState(savedInstanceState);
    }



    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
            @Override
            public void onTick(long timeLeft) {
                timeLeftMillis = timeLeft;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                // notification
                updateButton();
                btReset.setText("Got my clothing cleaned!");
            }
        }.start();

        isTimerRunning = true;
        updateButton();

    }
    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        updateButton();
    }
    private void resetTimer() {
        timeLeftMillis = START_TIME_IN_MILLIS;
        updateButton();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftMillis / 1000) / 60;
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        tvRemainingTime.setText(timeLeftFormatted);
    }

    private void updateButton() {
        if (isTimerRunning) {
            btStartPause.setText("pause");
            btReset.setVisibility(View.INVISIBLE);
        } else {
            btStartPause.setText("start");
            if (timeLeftMillis < 1000) {
                btStartPause.setText("start");
                btStartPause.setVisibility(View.INVISIBLE);
            } else {
                btStartPause.setVisibility(View.VISIBLE);
            }

            if (timeLeftMillis < START_TIME_IN_MILLIS) {
                btReset.setVisibility(View.VISIBLE);
            } else {
                btReset.setVisibility(View.INVISIBLE);
            }
        }
    }
}
