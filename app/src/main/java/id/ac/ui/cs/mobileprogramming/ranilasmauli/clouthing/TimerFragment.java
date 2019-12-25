package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerFragment extends Fragment {
    private static final String TIME_LEFT_TAG = "timelefttag";
    private static final String IS_TIME_RUNNING_TAG = "istimerunning";

    private CountDownTimer countDownTimer;

    private TextView tvRemainingTime;
    private EditText etRemainingTime;
    private Button btStartPause;
    private Button btSetTime;
    private Button btReset;
    private long startTimeInMillis = 60000;

    private long timeLeftMillis = startTimeInMillis;
    private boolean isTimerRunning;



    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View timerView = inflater.inflate(R.layout.fragment_timer, container, false);

        btStartPause = timerView.findViewById(R.id.bt_start_pause);
        btReset = timerView.findViewById(R.id.bt_reset);
        tvRemainingTime = timerView.findViewById(R.id.tv_timer_view);
        etRemainingTime = timerView.findViewById(R.id.et_remain_time);
        btSetTime = timerView.findViewById(R.id.bt_set_time);

        if (btReset == null || btStartPause == null) {
            Toast.makeText(container.getContext(), "NO COMPONENTS!", Toast.LENGTH_SHORT).show();
        }

        if (savedInstanceState != null) {
            // belom ngecek, harusnya si bener
             timeLeftMillis = savedInstanceState.getInt(TIME_LEFT_TAG);
             isTimerRunning = savedInstanceState.getBoolean(IS_TIME_RUNNING_TAG);
            // Lakukan ssesuatu dengan someStateValue jika diperlukan
        }

        btSetTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setTime(Integer.parseInt(etRemainingTime.getText().toString())*1000);
                Toast.makeText(timerView.getContext(), "Time has been set!", Toast.LENGTH_SHORT).show();
            }
        });

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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Menyimpan data tertentu (String) ke Bundle
         savedInstanceState.putLong(TIME_LEFT_TAG, timeLeftMillis);
         savedInstanceState.putBoolean(IS_TIME_RUNNING_TAG, isTimerRunning);

        // Selalu simpan pemanggil superclass di bawah agar data di view tetap tersimpan
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            timeLeftMillis = savedInstanceState.getLong(TIME_LEFT_TAG);
            isTimerRunning = savedInstanceState.getBoolean(IS_TIME_RUNNING_TAG);
        }
    }

    private void setTime(long timeInMillis) {
        this.startTimeInMillis = timeInMillis;
        resetTimer();
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
                timeLeftMillis = 0;
                updateCountDownText();
                updateButton();
                // notification

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
        timeLeftMillis = startTimeInMillis;
        updateButton();
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftMillis / 1000) / 60;
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        tvRemainingTime.setText(timeLeftFormatted);
    }

//    time is in mm:ss format
    private long getTimeLeftMillis(String timeInFormat) {
        String[] timeArray = timeInFormat.split(":");
        int minutes = Integer.parseInt(timeArray[0]);
        int seconds = Integer.parseInt(timeArray[1]);
        return (minutes * 60 + seconds) * 1000;
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

            if (timeLeftMillis < startTimeInMillis) {
                btReset.setVisibility(View.VISIBLE);
            } else {
                btReset.setVisibility(View.INVISIBLE);
            }
        }
    }
}
