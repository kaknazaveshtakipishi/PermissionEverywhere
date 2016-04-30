package com.permissioneverywhere.helper;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.os.ResultReceiver;

import com.permissioneverywhere.Const;
import com.permissioneverywhere.PermissionActivity;
import com.permissioneverywhere.R;

public class NotificationHelper {
  public static final String KEY_TYPE = "type";
  public static final String GCM_MESSAGE = "message";
  public static final int REQUEST_CODE_PUSH = 77;

  public void sendNotification(Context context, String message, String type, ResultReceiver receiver) {
    Intent intent = new Intent(context, PermissionActivity.class);
    intent.putExtra(KEY_TYPE, type);
    intent.putExtra(Const.REQUEST_CODE,234);
    intent.putExtra(Const.PERMISSIONS_ARRAY, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
    intent.putExtra(Const.RESULT_RECEIVER, receiver);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_PUSH, intent,
        PendingIntent.FLAG_ONE_SHOT);

    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
        .setSmallIcon(android.R.mipmap.sym_def_app_icon)//TODO FIX app data
        .setContentTitle(context.getString(R.string.app_name))
        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
        .setContentText(message)
        .setAutoCancel(true)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent);

    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    notificationManager.notify(type.hashCode(), notificationBuilder.build());
  }
}
