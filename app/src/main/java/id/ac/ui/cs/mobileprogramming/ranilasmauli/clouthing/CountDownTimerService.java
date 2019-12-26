package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import static id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing.TimerFragment.START_TIME_TAG;

public class CountDownTimerService extends Service {

    private CountDownTimer countDownTimer;
    private long START_TIME_MILLIS = 60000;
    private long timeLeftInMillis = START_TIME_MILLIS;

    public static String TIME_LEFT_MILLIS_TAG = "timeleftmillistag";

    public CountDownTimerService() {
    }

    public void setStartTImeInMillis(long time) {
        this.START_TIME_MILLIS = time;
    }
    public long getStartTimeInMillis() {
        return this.START_TIME_MILLIS;
    }

    public long getTimeLeftInMillis() {
        return this.timeLeftInMillis;
    }
    public void setTimeLeftInMillis(long time) {
        this.timeLeftInMillis = time;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        // do count down
        setStartTImeInMillis(intent.getLongExtra(START_TIME_TAG, 60000));
        Log.d("COUNT DOWN TIMER", "onStartCommand: "+getStartTimeInMillis());

        countDownTimer = new CountDownTimer(getStartTimeInMillis(), 1000) {
            @Override
            public void onTick(long timeLeft) {
                update(timeLeft, intent);
                Log.d("COUNT DOWN TIMER", "onTick: "+timeLeft);
            }

            @Override
            public void onFinish() {
                // send broadcast to the activity --> alarm
                // notification
                update(0, intent);
                Log.d("COUNT DOWN TIMER", "onFinish: "+timeLeftInMillis);

            }
        }.start();

//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        countDownTimer.cancel();
        super.onDestroy();
    }

    private void update(long time, Intent intent){
        intent.putExtra(TIME_LEFT_MILLIS_TAG,time);
        intent.setAction("action.update.timer");
        Log.d("UPDATE TIMER", "update: "+time);

        sendBroadcast(intent); // onReceive dont called
    }

}
