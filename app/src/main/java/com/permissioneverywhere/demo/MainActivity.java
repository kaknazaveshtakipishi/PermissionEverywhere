package com.permissioneverywhere.demo;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.permissioneverywhere.Const;
import com.permissioneverywhere.PermissionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), PermissionActivity.class);
        intent.putExtra(Const.REQUEST_CODE,234);
        intent.putExtra(Const.PERMISSIONS_ARRAY, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        startActivity(intent);
    }
}
