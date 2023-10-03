package com.example.proyecto0oscarmuoz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespuestasUsuario {
    @SerializedName("preguntaID")
    @Expose
    private int preguntaID;
    @SerializedName("respuestaSeleccionada")
    @Expose
    private int respuestaSeleccionada;
    @SerializedName("respuestaCorrecta")
    @Expose
    private int respuestaCorrecta;

    public RespuestasUsuario(int preguntaID, int respuestaSeleccionada, int respuestaCorrecta) {
        this.preguntaID = preguntaID;
        this.respuestaSeleccionada = respuestaSeleccionada;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getPreguntaID() {
        return preguntaID;
    }

    public void setPreguntaID(int preguntaID) {
        this.preguntaID = preguntaID;
    }

    public int getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(int respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }
}

