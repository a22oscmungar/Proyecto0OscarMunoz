package com.example.proyecto0oscarmuoz;import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TriviaResponse {
    @SerializedName("preguntes")
    private List<Pregunta> preguntes;

    public List<Pregunta> getPreguntas() {
        return preguntes;
    }

    public static class Pregunta {
        @SerializedName("id")
        private int id;

        @SerializedName("pregunta")
        private String pregunta;

        @SerializedName("respostes")
        private List<Respuesta> respostes;

        @SerializedName("resposta_correcta")
        private int respostaCorrecta;

        @SerializedName("imatge")
        private String imagenURL;

        public int getId() {
            return id;
        }

        public String getPregunta() {
            return pregunta;
        }

        public List<Respuesta> getRespostes() {
            return respostes;
        }

        public int getRespostaCorrecta() {
            return respostaCorrecta;
        }

        public String getImagenURL() {
            return imagenURL;
        }
    }

    public static class Respuesta {
        @SerializedName("id")
        private int id;

        @SerializedName("etiqueta")
        private String etiqueta;

        public int getId() {
            return id;
        }

        public String getEtiqueta() {
            return etiqueta;
        }
    }
}
