package com.miw.model;

/**
 * Modelo de datos para la información de los cambios de versión
 * 
 * @author Pablo
 * 
 */
public class Cambio {

    private Long id;
    private String plan;
    private String fecha;
    private Double version;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPlan() {
	return plan;
    }

    public void setPlan(String plan) {
	this.plan = plan;
    }

    public String getFecha() {
	return fecha;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public Double getVersion() {
	return version;
    }

    public void setVersion(Double version) {
	this.version = version;
    }

}
