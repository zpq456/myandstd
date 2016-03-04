package kr.ac.hansung.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Owner on 2016-02-14.
 */
public class Stopwatch extends Activity{
    TextView timeprint;
    Button st;
    Button pa;
    Button re;

    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;
    long mBaseTime;
    long mPauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        timeprint = (TextView)findViewById(R.id.timeprint);
        st = (Button)findViewById(R.id.st_bt);
        pa = (Button)findViewById(R.id.pa_bt);
        re = (Button)findViewById(R.id.re_bt);

    }

    Handler mTimer = new Handler(){
        public void handleMessage(android.os.Message msg){
            timeprint.setText(gettime());
            mTimer.sendEmptyMessage(0);
        };
    };

    @Override
    protected void onDestroy(){
        mTimer.removeMessages(0);//메시지를 지워서 메모리릭 방지
        super.onDestroy();
    }

    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.st_bt://start버튼
                switch(mStatus){
                    case IDLE:
                        mBaseTime = SystemClock.elapsedRealtime();
                        mTimer.sendEmptyMessage(0);
                        mStatus = RUNNING;
                        break;

                    case RUNNING:
                        long now = SystemClock.elapsedRealtime();
                        mBaseTime += (now - mPauseTime);
                        mTimer.sendEmptyMessage(0);
                        break;
                }
                break;
            case R.id.pa_bt://pause버튼
                mTimer.removeMessages(0);
                mPauseTime = SystemClock.elapsedRealtime();
                break;
            case R.id.re_bt://reset버튼
                mTimer.removeMessages(0);

                timeprint.setText("00:00:00");
                mStatus = IDLE;
                break;
        }
    }

    String gettime(){
        long now = SystemClock.elapsedRealtime();
        long ell = now - mBaseTime;//현재시간 - 지난시간
        String sEll = String.format("%02d:%02d:%02d",
                (ell/1000)/60, (ell/1000)%60, (ell%1000)/10);
        return sEll;
    }
}
