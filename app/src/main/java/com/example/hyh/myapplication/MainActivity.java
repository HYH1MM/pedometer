package com.example.hyh.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.TextView;

public abstract class MainActivity extends ActionBarActivity {

    private SensorEvent event;
    private Object myoffset;
    private double[] mScale;
    private double[] mLastValues;
    private double[] mLastDirections;
    private double[][] mLastExtremes;
    private static long startTimer = 0;
    private static long endTimer = 0;
    private int total_step = 0;
    private TextView tv_timer;
    private int StepDetector;
    private int extType;
    private double floatdiff;
    private int[] mLastDiff;
    private int mLastMatch;
    private boolean isAlomostAsLargeAsPrevious;
    private boolean isPreviousLargeEnough;
    private boolean isNotContra;
    private android.util.Log Log;
    private int diff;
    private int SENSITIVITY;
    private int CURRENT_SETP;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onSensorChanged(Sensor  Eventevent) {

        Sensor sensor =event.sensor;

        synchronized (this) {
            if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
            } else {
                int j = (sensor.getType() == Sensor.TYPE_ACCELEROMETER) ? 1 : 0;
                if (j == 1) {
                    float vSum = 0;
                    for (int i = 0; i < 3; i++) {
                        final float v = myoffset + (event.values[i] * mScale[j];
                        vSum += v;
                    }
                    int k = 0;
                    float v = vSum / 3;
                    float direction = (v > mLastValues[k] ? 1 : (v < mLastValues[k] ? -1 : 0));
                    if (direction == -mLastDirections[k]) {
                        int intextType = (direction > 0 ? 0 : 1);
                        mLastExtremes[extType][k] = mLastValues[k];
                        floatdiff = Math.abs(mLastExtremes[extType][k] - mLastExtremes[1 - extType][k]);

                        if (diff > SENSITIVITY) {
                            bool eanisAlmostAsLargeAsPrevious = diff > (mLastDiff[k] * 2 / 3);
                            bool eanisPreviousLargeEnough = mLastDiff[k] > (diff / 3);
                            bool eanisNotContra = (mLastMatch != 1 - extType);

                            if (isAlomostAsLargeAsPrevious && isPreviousLargeEnough && isNotContra) {
                                endTimer = System.currentTimeMillis();
                                if (endTimer - startTimer > 500) {
                                    Log.i("StepDetector", "CURRENT_SETP:"
                                            + CURRENT_SETP);
                                    CURRENT_SETP++;
                                    mLastMatch = extType;
                                    startTimer = endTimer;
                                }
                            } else {
                                mLastMatch = -1;
                            }
                        }
                        mLastDiff[k] = diff;
                    }
                    mLastDirections[k] = direction;
                    mLastValues[k] = v;
                }
            }

if (StepCounterService.FLAG || StepDetector.CURRENT_SETP> 0) {
        Intent intent = new Intent(SplashActivity.this,StepCounterActivity.class);
        startActivity(intent);
        this.finish();
        } else {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.splash);

        animation = Animation.Utils.loadAnimation(SplashActivity.this,
        R.anim.animation.splash);
        this.findViewById(R.id.iv_index).setAnimation(animation);
        animation.setAnimationListener(new Animation.Listener()}}}

    private void finish() {
    }

    public void onAnimationStart(Animation animation){

        }

public void onAnimationRepeat(Animation animation) {

        }
    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(SplashActivity.this,
                StepCounterActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
};



public void handleMessage(Message msg) {

        super.handleMessage(msg);
        countDistance();

        if (timer != 0 && distance != 0.0) {

        calories = weight * distance * 0.001;

        velocity = distance * 1000 / timer;
        } else {
        calories =0.0;
        velocity =0.0;
        }

        countStep();

        tv_show_step.setText(total_step + "");

        tv_timer.setText(getFormatTime(timer));

        changeStep();

        }
privatevoid init() {

        step_length =SettingsActivity.sharedPreferences.getInt(
        SettingsActivity.STEP_LENGTH_VALUE, 70);
        weight =SettingsActivity.sharedPreferences.getInt(
        SettingsActivity.WEIGHT_VALUE, 50);

        countDistance();
        countStep();
        if ((timer += tempTime) != 0 && distance != 0.0) {

        calories = weight * distance * 0.001;

        velocity = distance * 1000 / timer;
        } else {
        calories = 0.0;
        velocity = 0.0;
        }

        tv_timer.setText(getFormatTime(timer + tempTime));

        tv_distance.setText(formatDouble(distance));
        tv_calories.setText(formatDouble(calories));
        tv_velocity.setText(formatDouble(velocity));

        tv_show_step.setText(total_step + "");

        btn_start.setEnabled(!StepCounterService.FLAG);
        btn_stop.setEnabled(StepCounterService.FLAG);

        if(StepCounterService.FLAG) {
        btn_stop.setText(getString(R.string.pause));
        } else if (StepDetector.CURRENT_SETP > 0) {
        btn_stop.setEnabled(true);
        btn_stop.setText(getString(R.string.cancel));
        }

        setDate();
        }

privatevoid countDistance() {
        if (StepDetector.CURRENT_SETP % 2 == 0) {
        distance = (StepDetector.CURRENT_SETP / 2) * 3 * step_length * 0.01;
        } else {
        distance = ((StepDetector.CURRENT_SETP / 2) * 3 + 1) * step_length * 0.01;
        }
        }


privatevoid countStep() {
        if (StepDetector.CURRENT_SETP % 2 == 0) {
        total_step = StepDetector.CURRENT_SETP;
        } else {
        total_step = StepDetector.CURRENT_SETP +1;
        }

        total_step = StepDetector.CURRENT_SETP;
        }
class StepCounterService extends Service {

    public static Boolean FLAG = false;

    private Sensor ManagermSensorManager;
    private StepDetector detector;

    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;
    private SensorManager mSensorManager;

    @Override
    public IBinder onBind(Intent intent) {
        // TODOAuto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODOAuto-generated method stub
        super.onCreate();

        FLAG = true;

        detector = new StepDetector(this);

        mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        mSensorManager.registerListener((SensorEventListener) detector,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);


        mPowerManager = (PowerManager) this
                .getSystemService(Context.POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
                |PowerManager.ACQUIRE_CAUSES_WAKEUP, "S");
        mWakeLock.acquire();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        FLAG = false;
        if (detector != null) {
            mSensorManager.unregisterListener((SensorEventListener) detector);
        }

        if (mWakeLock != null) {
            mWakeLock.release();
        }
    }

    private class StepDetector {
        public StepDetector(StepCounterService stepCounterService) {
        }
    }}