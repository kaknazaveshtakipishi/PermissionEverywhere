package com.permissioneverywhere;

import android.content.pm.PackageManager;

/**
 * Created by Farruxx on 01.05.2016.
 */
public class PermissionResponse {
    String[] permission;
    int [] grantResult;
    int requestCode;

    public PermissionResponse(String[] permission, int[] grantResult, int requestCode) {
        this.permission = permission;
        this.grantResult = grantResult;
        this.requestCode = requestCode;
    }

    public boolean isGranted(){
        if(grantResult != null && grantResult.length>0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    public String[] getPermission() {
        return permission;
    }

    public int[] getGrantResult() {
        return grantResult;
    }

    public int getRequestCode() {
        return requestCode;
    }
}
