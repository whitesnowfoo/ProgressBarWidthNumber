package app.fuck.com.progressbarwidthnumber;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends Activity {

    //private HorizontalProgressBarWithNumber mProgressBar;
    private RoundProgressBarWidthNumber mProgressBar;
    private static final int MSG_PROGRESS_UPDATE = 0x110;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int progress = mProgressBar.getProgress();
            mProgressBar.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);

            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (RoundProgressBarWidthNumber) findViewById(R.id.id_progress02);
        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);

    }
}
