package com.example.proyecto0oscarmuoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearPreguntas;
    Button btnEnviar, btnEmpezar;
    TextView tvContador;
    CountDownTimer countDownTimer;

    private static final String URL = "http://192.168.19.160:3005/"; //string que usaremos con nuestra url
    private Retrofit retrofit;
    private TrivialApi trivialApi;

    private List<TriviaResponse.Pregunta> preguntas = new ArrayList<>(); //array donde guardaremos las preguntas
    List<RespuestasUsuario> respuestasSeleccionadas = new ArrayList<>(); //array para las respuestas del usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazamos el linear layout donde irán las preguntas y el boton para enviar las respuestas
        linearPreguntas = findViewById(R.id.linearPreguntas);
        tvContador = findViewById(R.id.tvCountdown);


        //creamos el contador
        countDownTimer = new CountDownTimer(30000    , 1000) {
            public void onTick(long millisUntilFinished) {
                tvContador.setText("Tiempo restante: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                tvContador.setText("Tiempo agotado");
                enviarRespuestas();
            }
        };

        // Inicia el temporizador
        countDownTimer.start();


        // Inicializa Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // Crea una instancia de la interfaz TriviaApi
        trivialApi = retrofit.create(TrivialApi.class);

        // Realiza la llamada a la API
        Call<List<TriviaResponse.Pregunta>> call = trivialApi.getPreguntas();
        call.enqueue(new Callback<List<TriviaResponse.Pregunta>>() {
            @Override
            public void onResponse(Call<List<TriviaResponse.Pregunta>> call, Response<List<TriviaResponse.Pregunta>> response) {
                Log.d("Prueba","Estoy entrando al onResponse");
                if (response.isSuccessful()) {
                    // Log.d("Prueba","El response ha funcionado");
                    //List<TriviaResponse.Pregunta> triviaResponse = response.body();
                    preguntas = response.body();
                    //preguntas = triviaResponse.getPreguntas();
                    mostrarPreguntas(preguntas);
                } else {
                    Log.d("prueba", "error al hacer el call "+response.code()+" "+response);
                }
            }

            @Override
            public void onFailure(Call<List<TriviaResponse.Pregunta>> call, Throwable t) {
                Log.d("prueba", "error onFailure "+t.getMessage()+" "+t+" "+ call);
            }
        });
    }



    public void mostrarPreguntas(List<TriviaResponse.Pregunta> preguntas) {
        for (TriviaResponse.Pregunta pregunta : preguntas) {
            // Crear un TextView para la pregunta
            TextView tvPregunta = new TextView(this);
            tvPregunta.setText(pregunta.getPregunta());
            tvPregunta.setTextSize(24);
            tvPregunta.setGravity(Gravity.CENTER);
            tvPregunta.setPadding(0, 16, 0, 16);
            linearPreguntas.addView(tvPregunta);

            // Agregar la imagen usando Picasso (si tienes la URL de la imagen)
            String imageUrl = pregunta.getImagenURL();
            carregarImatge(imageUrl);

            // Crear un RadioGroup para las opciones
            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setPadding(100, 0, 0, 0);

            // Obtener la lista de respuestas de la pregunta
            List<TriviaResponse.Respuesta> respuestas = pregunta.getRespostes();

            // Crear un RadioButton para cada respuesta
            for (TriviaResponse.Respuesta respuesta : respuestas) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(respuesta.getEtiqueta());
                radioButton.setId(respuesta.getId());
                int marginBottom = 30; // Ajusta según el espacio deseado
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams2.setMargins(0, 0, 0, marginBottom);
                radioButton.setLayoutParams(layoutParams2);
                radioButton.setTextSize(20);
                radioGroup.addView(radioButton);
            }

            linearPreguntas.addView(radioGroup);
        }
    }

    //creamos una funcion que nos permitirá cargar imagenes a partir de una URL
    private void carregarImatge(String imatgeUrl) {
        // Crear un ImageView
        ImageView imagen = new ImageView(this);

        // Crear parámetros de diseño para el ImageView
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        //darle valores adicionales al imagewiew, como el tamaño de la imagen y que esté centrada
        layoutParams.width = 750;
        layoutParams.height = 600;
        layoutParams.gravity = Gravity.CENTER;

        //le asignamos los valores a la imagen
        imagen.setLayoutParams(layoutParams);


        // Agregar el ImageView al LinearLayout
        linearPreguntas.addView(imagen);

        // Usar Picasso para cargar la imagen desde la URL
        try {
            Picasso.get()
                    .load(imatgeUrl)
                    .error(R.drawable.imagenprueba) // Establecer una imagen de marcador de posición en caso de error
                    .into(imagen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //esta es la función que llamamos al darle al boton de enviar respuestas
    public void enviarRespuestas() {
        // Definimos el número total de preguntas que hay y una variable para ir contando las correctas
        int totalPreguntas = preguntas.size();
        int respuestasCorrectas = 0;

       // int radioGroupIndex = 0; // Variable para rastrear el índice del RadioGroup actual

        // Recorremos todas las preguntas
        for (int i = 0; i < totalPreguntas; i++) {
            TriviaResponse.Pregunta pregunta = preguntas.get(i);

            // Obtenemos el RadioGroup correspondiente a la pregunta
            View childView = linearPreguntas.getChildAt(i * 3 + 2);

            Log.d("PruebaIndex","i: "+i+" index: "+i * 3 + 2);

            if (childView instanceof RadioGroup) {
                RadioGroup radioGroup = (RadioGroup) childView;
                // Asignamos la opción seleccionada con getCheckedRadioButtonId()
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if(selectedRadioButtonId != -1) {
                    RespuestasUsuario respuesta = new RespuestasUsuario();
                    respuesta.setPreguntaID(i+1);

                    Log.d("pruebaRespuestas", "selected: " + selectedRadioButtonId + " correcta: " + pregunta.getRespostaCorrecta());
                    // Comprobamos si la opción seleccionada es la respuesta correcta
                    if (selectedRadioButtonId == pregunta.getRespostaCorrecta()) {
                        respuestasCorrectas++;
                        respuesta.setAcierto(true);
                    }
                    respuestasSeleccionadas.add(respuesta);
                }

            }
        }

        Gson gson = new Gson();

        RespuestasEnvio respuestasEnvio = new RespuestasEnvio(respuestasSeleccionadas);
        String respuestasJson = gson.toJson(respuestasEnvio);



        //String respuestasJson = gson.toJson(respuestasSeleccionadas);
        //String respuestasJson = gson.toJson(respuestasSeleccionadas, new TypeToken<List<RespuestasUsuario>>() {}.getType());
        Log.d("prueba", "enviarRespuestas: "+ respuestasJson);

        Call<Void> call = trivialApi.enviarPreguntas(respuestasEnvio);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("prueba",response.toString());
                if(response.isSuccessful()){
                    Log.d("prueba","ha funcionado");
                }else{
                    Log.e("prueba","error en solicitud al enviar: "+response.code()+" "+call.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Error", "Error en la solicitud al servidor: " + t.getMessage()+" "+ call);
            }
        });


        // Creamos un intent para mostrar la puntuación en una actividad separada
        Intent intent = new Intent(this, puntuacion.class);
        intent.putExtra("puntuacion", respuestasCorrectas);
        intent.putExtra("totalPreguntas", totalPreguntas);

        startActivity(intent);
    }
}