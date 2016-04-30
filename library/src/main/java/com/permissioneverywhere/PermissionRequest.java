package com.permissioneverywhere;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.os.ResultReceiver;

/**
 * Created by Farruxx on 30.04.2016.
 */
public class PermissionRequest {
    Context context;
    String[] permissions;
    int requestCode;
    String notificationTitle;
    String notificationText;
    int icon;

    PermissionResponse response;

    public PermissionRequest(Context context, String[] permissions, int requestCode, String notificationTitle, String notificationText, int icon) {
        this.context = context;
        this.permissions = permissions;
        this.requestCode= requestCode;
        this.notificationTitle = notificationTitle;
        this.notificationText = notificationText;
        this.icon = icon;
    }

    public PermissionResponse call(){
        try {
            final Object lock = new Object();
            NotificationHelper.sendNotification(context, permissions, requestCode,
                    notificationTitle, notificationText, icon, new ResultReceiver(new Handler(Looper.getMainLooper())){
                        @Override
                        protected void onReceiveResult(int resultCode, Bundle resultData) {
                            super.onReceiveResult(resultCode, resultData);
                            int [] grantResult = resultData.getIntArray(Const.GRANT_RESULT);
                            String [] permissions = resultData.getStringArray(Const.PERMISSIONS_ARRAY);
                            response = new PermissionResponse(permissions, grantResult,resultCode);

                            synchronized (lock){
                                lock.notifyAll();
                            }
                        }
                    });
            synchronized (lock) {
                lock.wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(response == null){
            response = new PermissionResponse(permissions, null, requestCode);
        }
        return response;
    }

    public void enqueue(final PermissionResultCallback callback){
        NotificationHelper.sendNotification(context, permissions, requestCode,
                notificationTitle, notificationText, icon, new ResultReceiver(new Handler()){
                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        super.onReceiveResult(resultCode, resultData);
                        int [] grantResult = resultData.getIntArray(Const.GRANT_RESULT);
                        String [] permissions = resultData.getStringArray(Const.PERMISSIONS_ARRAY);
                        response = new PermissionResponse(permissions, grantResult,resultCode);
                        callback.onComplete(new PermissionResponse(permissions, grantResult,resultCode));
                    }
                });
    }
}
