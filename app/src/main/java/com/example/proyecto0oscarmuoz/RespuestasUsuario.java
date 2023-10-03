package com.example.proyecto0oscarmuoz;

public class RespuestasUsuario {
    private int preguntaID;
    private int respuestaSeleccionada;

    public RespuestasUsuario(int preguntaID, int respuestaSeleccionada) {
        this.preguntaID = preguntaID;
        this.respuestaSeleccionada = respuestaSeleccionada;
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
