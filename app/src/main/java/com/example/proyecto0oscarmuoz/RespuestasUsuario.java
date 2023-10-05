package com.example.proyecto0oscarmuoz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespuestasUsuario {
    @SerializedName("preguntaID")
    @Expose
    private int preguntaID;

    @SerializedName(("acierto"))
    @Expose
    private boolean acierto;


    public RespuestasUsuario() {
        this.preguntaID = 0;
        this.acierto = false;
    }
    public RespuestasUsuario(int preguntaID, boolean acierto) {
        this.preguntaID = preguntaID;
        this.acierto = acierto;
    }

    public int getPreguntaID() {
        return preguntaID;
    }

    public void setPreguntaID(int preguntaID) {
        this.preguntaID = preguntaID;
    }

    public boolean isAcierto() {
        return acierto;
    }

    public void setAcierto(boolean acierto) {
        this.acierto = acierto;
    }
}


