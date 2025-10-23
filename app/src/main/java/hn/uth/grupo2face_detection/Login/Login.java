package hn.uth.grupo2face_detection.Login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import hn.uth.grupo2face_detection.Maestros;
import hn.uth.grupo2face_detection.R;

public class Login extends AppCompatActivity {

    TextView txtRegistro;
    ImageView imagen;
    final static int DAME_PERMISO = 100;



    private static final int REQUEST_PERMISSIONS = 1000;
    private static final int REQUEST_IMAGE_CAPTURE = 1001;
    private static final int REQUEST_PICK_IMAGE = 1002;
    private static String IMAGE_CAMERA_TAG = "IMAGEN_CAMARA";

    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        txtRegistro = findViewById(R.id.textRegistro);
        imagen = findViewById(R.id.imgUsuario);

        btnLogin = findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(v -> {
            Intent menu = new Intent(getApplicationContext(), Maestros.class);
            startActivity(menu);
        });


        txtRegistro.setOnClickListener(v -> {
            Intent registro = new Intent(getApplicationContext(), Registro.class);
            startActivity(registro);
        });

        imagen.setOnClickListener(v -> {
            try {
                abrirCamara();
            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    private void abrirCamara() {
        if(revisarPermisos()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 1);
            }
        }

    }

    // Resultado de la cámara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);

        }
    }

    // Resultado del permiso



    private boolean revisarPermisos(){
        int cameraPermission = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA);
        Log.d(IMAGE_CAMERA_TAG,"Evaluando permisos de camara");
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
            int storagePermission = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(cameraPermission != PackageManager.PERMISSION_GRANTED || storagePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
                Log.d(IMAGE_CAMERA_TAG,"No se  tienen los permisos");
                return false;
            }
        }else{
            if(cameraPermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSIONS);
                Log.d(IMAGE_CAMERA_TAG,"No se  tienen los permisos");
                return false;
            }
        }
        Log.d(IMAGE_CAMERA_TAG,"Permisos concedidos");

        return true;
    }


}