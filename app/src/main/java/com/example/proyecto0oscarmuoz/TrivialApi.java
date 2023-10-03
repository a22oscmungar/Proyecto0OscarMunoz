package com.example.proyecto0oscarmuoz;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TrivialApi {
    @GET ("/preguntas")
    Call<List<TriviaResponse.Pregunta>> getPreguntas();

    @POST("/recibirRespuestas")
    Call<Void> enviarPreguntas(@Body String respuestasJson);
}
