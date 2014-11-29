package com.miw.model;

/**
 * Modelo de datos para las probabilidades de los riesgos
 * 
 * @author Pablo
 * 
 */
public class Probabilidad {

    private Integer porcentaje;
    private String probabilidad;
    private String descripcion;

    public Integer getPorcentaje() {
	return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
	this.porcentaje = porcentaje;
    }

    public String getProbabilidad() {
	return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
	this.probabilidad = probabilidad;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }
}
