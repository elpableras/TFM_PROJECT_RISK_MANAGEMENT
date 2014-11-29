package com.miw.model;

import java.util.Vector;

/**
 * Modelo de datos para la información de la identificación de riesgos
 * 
 * @author Pablo
 * 
 */
public class Iden {

    private Long idP;
    private Long idU;
    private Integer id = 0;
    private Double version;
    private String nombre = "";
    private String descripcion = "";
    private String responsable = "";
    private Integer probabilidad = 0;
    private String impacto = "";
    private Double valor = 0.0;
    private String response = "";
    private String notes = "";
    private Vector<Cambio> cambio = null;

    public Long getIdP() {
	return idP;
    }

    public void setIdP(Long idP) {
	this.idP = idP;
    }

    public Long getIdU() {
	return idU;
    }

    public void setIdU(Long idU) {
	this.idU = idU;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Double getVersion() {
	return version;
    }

    public void setVersion(Double version) {
	this.version = version;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getResponsable() {
	return responsable;
    }

    public void setResponsable(String responsable) {
	this.responsable = responsable;
    }

    public Integer getProbabilidad() {
	return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
	this.probabilidad = probabilidad;
    }

    public String getImpacto() {
	return impacto;
    }

    public void setImpacto(String impacto) {
	this.impacto = impacto;
    }

    public Double getValor() {
	return valor;
    }

    public void setValor(Double valor) {
	this.valor = valor;
    }

    public String getResponse() {
	return response;
    }

    public void setResponse(String response) {
	this.response = response;
    }

    public String getNotes() {
	return notes;
    }

    public void setNotes(String notes) {
	this.notes = notes;
    }

    public Vector<Cambio> getCambio() {
	return cambio;
    }

    public void setCambio(Vector<Cambio> cambio) {
	this.cambio = cambio;
    }

}
