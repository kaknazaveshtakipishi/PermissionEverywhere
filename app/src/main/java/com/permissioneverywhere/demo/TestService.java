package com.permissioneverywhere.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;
import android.widget.Toast;

import com.permissioneverywhere.helper.NotificationHelper;

/**
 * Created by Farruxx on 30.04.2016.
 */
public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new NotificationHelper().sendNotification(getApplicationContext(), "TEST!!", "TESTTT", new ResultReceiver(new Handler(Looper.getMainLooper())){
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);
                Toast.makeText(TestService.this, "THIS IS SPARTAA!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
