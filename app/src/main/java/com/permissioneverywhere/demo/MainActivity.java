package com.permissioneverywhere.demo;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.permissioneverywhere.Const;
import com.permissioneverywhere.PermissionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ResultReceiver resultReceiver = new ResultReceiver(new Handler()){
//            @Override
//            protected void onReceiveResult(int resultCode, Bundle resultData) {
//                super.onReceiveResult(resultCode, resultData);
//            }
//        };
//        Intent intent = new Intent(getApplicationContext(), PermissionActivity.class);
//        intent.putExtra(Const.REQUEST_CODE,234);
//        intent.putExtra(Const.PERMISSIONS_ARRAY, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
//        intent.putExtra(Const.RESULT_RECEIVER, resultReceiver);
//        startActivity(intent);

        Intent intent = new Intent(getApplicationContext(), TestService.class);
        startService(intent);
    }
}
