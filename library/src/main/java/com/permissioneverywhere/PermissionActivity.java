package com.permissioneverywhere;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ResultReceiver;

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
            if(!hasPermissions(permissionsArray)) {
                ActivityCompat.requestPermissions(this, permissionsArray, requestCode);
            }else {
                onComplete(requestCode, permissionsArray, new int[]{PackageManager.PERMISSION_GRANTED});
            }
        }else {
            finish();
        }
    }

    private void onComplete(int requestCode, String[] permissions, int[] grantResults) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(Const.PERMISSIONS_ARRAY, permissions);
        bundle.putIntArray(Const.GRANT_RESULT, grantResults);
        bundle.putInt(Const.REQUEST_CODE, requestCode);
        resultReceiver.send(requestCode, bundle);
        finish();

    }

    private boolean hasPermissions(String[] permissionsArray) {
        boolean result = true;
        for(String permission:permissionsArray) {
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onComplete(requestCode, permissions, grantResults);
    }

}
