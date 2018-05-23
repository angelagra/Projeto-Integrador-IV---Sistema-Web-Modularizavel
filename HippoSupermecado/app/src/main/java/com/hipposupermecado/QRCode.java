package com.hipposupermecado;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.google.zxing.Result;
import com.hipposupermecado.Adapter.CategoriaAdapter;
import com.hipposupermecado.Model.Categoria;
import com.hipposupermecado.Model.Produto;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                Intent intent = new Intent();
                intent.putExtra("id", result.getText().replaceAll("H",""));

                setResult(Activity.RESULT_OK, intent);
                finish();


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
