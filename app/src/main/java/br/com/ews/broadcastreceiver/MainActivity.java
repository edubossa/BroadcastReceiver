package br.com.ews.broadcastreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_PERMISSION_RECEIVE_SMS = 101;
    private static final int CODE_PERMISSION_VIBRATE = 102;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED)
        {
            Log.i(TAG, "Solicitando Permissao ao usuario ");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, CODE_PERMISSION_RECEIVE_SMS);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, CODE_PERMISSION_VIBRATE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODE_PERMISSION_RECEIVE_SMS :
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "CODE_PERMISSION_RECEIVE_SMS: " + requestCode);
                }
                break;

            case CODE_PERMISSION_VIBRATE :
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "CODE_PERMISSION_VIBRATE: " + requestCode);
                }
                break;
        }
    }
}
