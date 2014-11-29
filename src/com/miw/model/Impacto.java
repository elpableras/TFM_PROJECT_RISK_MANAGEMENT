package com.miw.model;

import java.util.Vector;

/**
 * Modelo de datos para los impactos de los riesgos
 * 
 * @author Pablo
 * 
 */
public class Impacto {

    private String objetivo;
    private Vector<Probabilidad> probabilidad = null;

    public String getObjetivo() {
	return objetivo;
    }

    public void setObjetivo(String objetivo) {
	this.objetivo = objetivo;
    }

    public Vector<Probabilidad> getProbabilidad() {
	return probabilidad;
    }

    public void setProbabilidad(Vector<Probabilidad> probabilidad) {
	this.probabilidad = probabilidad;
    }

}
