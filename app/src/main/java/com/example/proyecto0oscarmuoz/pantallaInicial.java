package com.example.proyecto0oscarmuoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pantallaInicial extends AppCompatActivity {


    Button btnEmpezar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);

        btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comenzarPartida();
            }
        });
    }
    public void comenzarPartida(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}