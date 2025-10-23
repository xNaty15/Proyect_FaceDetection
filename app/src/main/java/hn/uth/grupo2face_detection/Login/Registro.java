package hn.uth.grupo2face_detection.Login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import hn.uth.grupo2face_detection.R;

public class Registro extends AppCompatActivity {

    private static final int DAME_PERMISO_PORFA = 1;

    private Uri imagenUri;

    TextView txtIniciarSesion;
    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);

        txtIniciarSesion = findViewById(R.id.TextIniciarSesion);
        imagen = findViewById(R.id.imagen);



        txtIniciarSesion.setOnClickListener(v -> {
            Intent login = new Intent(getApplicationContext(), Login.class);
            startActivity(login);
        });


        imagen.setOnClickListener(v -> {
            seleccionarImagen();
        });

    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, DAME_PERMISO_PORFA);
    }
//queso
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DAME_PERMISO_PORFA && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagenUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagenUri);
                imagen.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}