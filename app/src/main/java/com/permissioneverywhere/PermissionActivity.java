package com.permissioneverywhere;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Farruxx on 30.04.2016.
 */
public class PermissionActivity extends Activity {
    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] permissionsArray = getIntent().getStringArrayExtra(Const.PERMISSIONS_ARRAY);
        requestCode = getIntent().getIntExtra(Const.REQUEST_CODE, Const.DEFAULT_CODE);
        ActivityCompat.requestPermissions(this, permissionsArray, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(this.requestCode == requestCode){

        }
    }
}
