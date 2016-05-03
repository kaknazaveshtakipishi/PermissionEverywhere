package com.permissioneverywhere.demo;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;
import android.widget.Toast;

import com.permissioneverywhere.PermissionEverywhere;
import com.permissioneverywhere.PermissionResponse;
import com.permissioneverywhere.PermissionResultCallback;

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
        boolean asyncTest = true;

        if(asyncTest) {

            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... params) {
                    PermissionResponse response = PermissionEverywhere.getPermission(getApplicationContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            123, "My Awesome App", "This app needs a write permission", R.mipmap.ic_launcher).call();

                    boolean isGranted = response.isGranted();

                    return isGranted;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    Toast.makeText(TestService.this, "is Granted " + aBoolean, Toast.LENGTH_SHORT).show();
                }
            }.execute();


        }else {


            PermissionEverywhere.getPermission(getApplicationContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123, "My Awesome App", "This app needs a permission", R.mipmap.ic_launcher).enqueue(new PermissionResultCallback() {
                @Override
                public void onComplete(PermissionResponse permissionResponse) {
                    Toast.makeText(TestService.this, "is Granted " + permissionResponse.isGranted(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
