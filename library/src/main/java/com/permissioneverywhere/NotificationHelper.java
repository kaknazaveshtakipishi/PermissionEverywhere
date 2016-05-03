package com.permissioneverywhere;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.os.ResultReceiver;


 class NotificationHelper {
  public static final int REQUEST_CODE_PUSH = 77;

  public static void sendNotification(Context context,
                                      String[] permissions,
                                      int requestCode,
                                      String notificationTitle,
                                      String notificationText,
                                      int notificationIcon,
                                      ResultReceiver receiver) {

    Intent intent = new Intent(context, PermissionActivity.class);
    intent.putExtra(Const.REQUEST_CODE, requestCode);
    intent.putExtra(Const.PERMISSIONS_ARRAY, permissions);
    intent.putExtra(Const.RESULT_RECEIVER, receiver);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_PUSH, intent,
        PendingIntent.FLAG_ONE_SHOT);

    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
        .setSmallIcon(notificationIcon)
        .setContentTitle(notificationTitle)
        .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText))
        .setContentText(notificationText)
        .setAutoCancel(true)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent);

    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    notificationManager.notify(requestCode, notificationBuilder.build());
  }
}
