package com.dspread.demoui.blusaltmpos;

import android.app.Application;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Keep;

import java.util.List;

@Keep
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static MyApplication INSTANCE;

    public static MyApplication getINSTANCE(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        // AppLog.debug(true);
    }


}

