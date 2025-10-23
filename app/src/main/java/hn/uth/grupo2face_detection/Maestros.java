package hn.uth.grupo2face_detection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import hn.uth.grupo2face_detection.Login.Login;

public class Maestros extends AppCompatActivity {
    Button btnEstadistica;
    Button btnAlumnos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maestros);

        btnAlumnos = findViewById(R.id.btnAlumnos);
        btnEstadistica = findViewById(R.id.btnEstadistica);

        btnEstadistica.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Estadistica.class);
            startActivity(intent);
        });
        btnAlumnos.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Alumnos.class);
            startActivity(intent);
        });



    }
}