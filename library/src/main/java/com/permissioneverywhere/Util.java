package com.permissioneverywhere;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by Farruxx on 01.05.2016.
 */
 class Util {
    public static boolean hasPermission(Context context, String[] permissions){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission :permissions) {
                boolean result = (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
                if(result == false){
                    return false;
                }
            }
        }
            return true;
        }
}
