package com.example.asif.qrscanner;

import android.*;
import android.content.Intent;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import java.util.ArrayList;

public class ScanActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    Toolbar mToolbar;
    Button button;
    TextView Title;

    String Data,id;
    static ArrayList<String> scanned = new ArrayList<String>();

    FirebaseHelper helper;
    private QRCodeReaderView qrCodeReaderView;
    boolean hasCameraPermission = false;

    private static final String cameraPerm = android.Manifest.permission.CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        mToolbar = (Toolbar)findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Title=(TextView)findViewById(R.id.title) ;
        Title.setText("Qr Scanner");
        button = (Button)findViewById(R.id.Button);
        button.setVisibility(View.INVISIBLE);

        helper = new FirebaseHelper();

        if(!hasCameraPermission){
            RuntimePermissionUtil.requestPermission(this, cameraPerm, 100);
        }

        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();


    }

    @Override
    public void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
        }

    @Override
    public void onPause() {
        super.onPause();

        qrCodeReaderView.stopCamera();

    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Log.v("Result:     ",text);

        Data = text;
        String [] arrOfStr = Data.split("!",2);
        id=arrOfStr[0];
        if (check(id)) {
            //Toast.makeText(getApplicationContext(),"Already Scanned!!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"id:  "+id,Toast.LENGTH_SHORT).show();
            scanned.add(id);
            helper.Scan(id,getApplicationContext(),false);
        }
    }

    private boolean check(String id) {
        for(int i =0;i<scanned.size();i++){
            if(id.equals(scanned.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionGranted() {
                    if ( RuntimePermissionUtil.checkPermissonGranted(ScanActivity.this, cameraPerm)) {
                        //restartActivity();
                    }
                }

                @Override
                public void onPermissionDenied() {
                    // do nothing
                }
            });
        }
    }

    private void restartActivity() {
        Intent restart = new Intent(this,ScanActivity.class);
        startActivity(restart);
        finish();
    }

}
