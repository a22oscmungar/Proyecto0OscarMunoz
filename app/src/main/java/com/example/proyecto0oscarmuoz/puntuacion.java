package com.example.proyecto0oscarmuoz;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class puntuacion extends AppCompatActivity {
    TextView tvPuntuacion;
    Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);

        tvPuntuacion = findViewById(R.id.tvPuntuacion);
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverInicio();
            }
        });

        int puntuacion = getIntent().getIntExtra("puntuacion",0);
        int totalPreguntas = getIntent().getIntExtra("totalPreguntas",0);

        tvPuntuacion.setText("TU PUNTUACIÓN HA SIDO:\n\n"+puntuacion+" DE "+totalPreguntas);

    }

    public void volverInicio(){
        Intent intent = new Intent(this, pantallaInicial.class);
        startActivity(intent);
    }
}
