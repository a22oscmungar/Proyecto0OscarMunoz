package com.example.proyecto0oscarmuoz;

import java.util.List;

public class RespuestasEnvio {
    private List<RespuestasUsuario> respuestasEnviadas;

    public RespuestasEnvio(List<RespuestasUsuario> preguntas) {
        this.respuestasEnviadas = preguntas;
    }
}
