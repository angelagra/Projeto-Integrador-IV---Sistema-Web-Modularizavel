package com.hipposupermecado;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCode extends AppCompatActivity{

    private ZXingScannerView sccanerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sccanerView = new ZXingScannerView(QRCode.this);

        setContentView(sccanerView);

        if(ContextCompat.checkSelfPermission(QRCode.this, Manifest.permission.CAMERA) != (PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(QRCode.this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        if(requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast toast = Toast.makeText(QRCode.this, "Esta Aplicação precisa de permissão para Utilizar a Câmera", Toast.LENGTH_LONG );
                toast.show();
                this.finish();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        final ZXingScannerView.ResultHandler resultHandler = new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {

                AlertDialog.Builder builder = new AlertDialog.Builder(QRCode.this);

                builder.setTitle("Encontrado");
                builder.setMessage(result.getText());
                builder.setCancelable(false);

                final ZXingScannerView.ResultHandler rh = this;

                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sccanerView.resumeCameraPreview(rh);
                    }
                };

                builder.setPositiveButton("OK", listener);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };

        sccanerView.setResultHandler(resultHandler);
        sccanerView.startCamera();
    }

    @Override
    public void onPause(){
        super.onPause();

        sccanerView.startCamera();
    }


}
