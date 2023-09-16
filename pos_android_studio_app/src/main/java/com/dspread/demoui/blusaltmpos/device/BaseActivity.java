package com.blusalt.blusaltmpos.device;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
@Keep
public class BaseActivity extends AppCompatActivity {

    private final String LOG_TAG = BaseActivity.class.getSimpleName();

    protected void showResult(final TextView textView, final String text) {
        Log.d(LOG_TAG,text);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}
