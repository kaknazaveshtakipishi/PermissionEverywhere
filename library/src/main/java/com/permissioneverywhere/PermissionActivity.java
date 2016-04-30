package com.permissioneverywhere;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.ResultReceiver;

import java.util.Arrays;

/**
 * Created by Farruxx on 30.04.2016.
 */
public class PermissionActivity extends Activity {

    private ResultReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent() != null) {

            resultReceiver = getIntent().getParcelableExtra(Const.RESULT_RECEIVER);
            String[] permissionsArray = getIntent().getStringArrayExtra(Const.PERMISSIONS_ARRAY);
            int requestCode = getIntent().getIntExtra(Const.REQUEST_CODE, Const.DEFAULT_CODE);
            ActivityCompat.requestPermissions(this, permissionsArray, requestCode);
        }else {
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Bundle bundle = new Bundle();
        bundle.putStringArray(Const.PERMISSIONS_ARRAY, permissions);
        bundle.putIntArray(Const.GRANT_RESULT, grantResults);
        resultReceiver.send(requestCode, bundle);
        finish();
    }

}
