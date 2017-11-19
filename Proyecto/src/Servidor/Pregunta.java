/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;

/**
 *
 * @author Cristhian Guzman
 */
public class Pregunta implements Serializable{
    
    private String encabezado, pregunta, opcionA, opcionB, opcionC, opcionD, opcionCorrecta;

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setOpcionA(String opcionA) {
        this.opcionA = opcionA;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public void setOpcionD(String opcionD) {
        this.opcionD = opcionD;
    }

    public void setOpcionCorrecta(String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }
    
    
    
}
