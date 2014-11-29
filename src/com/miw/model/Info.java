package com.miw.model;

import java.util.Vector;

/**
 * Modelo de datos para la información de los planes de riesgo
 * 
 * @author Pablo
 * 
 */
public class Info {

    private Long id;
    private String fecha;
    private String titulo;
    private Double version;
    private String nombre;
    private Long idP;
    private Long idU;
    private Vector<Cambio> cambio = null;
    private String metodologia = "";
    private String htecno = "";
    private String roles = "";
    private String presu = "";
    private String calendar = "";
    private String riesgo = "";
    private Vector<Probabilidad> probabilidad = null;
    private Vector<Impacto> impacto = null;
    private Double corte = 0.0;
    private String rango = "";
    private String contingencia = "";
    private String formato = "";

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
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

    public String getFecha() {
	return fecha;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

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

    public Vector<Cambio> getCambio() {
	return cambio;
    }

    public void setCambio(Vector<Cambio> cambio) {
	this.cambio = cambio;
    }

    public String getMetodologia() {
	return metodologia;
    }

    public void setMetodologia(String metodologia) {
	this.metodologia = metodologia;
    }

    public String getHtecno() {
	return htecno;
    }

    public void setHtecno(String htecno) {
	this.htecno = htecno;
    }

    public String getRoles() {
	return roles;
    }

    public void setRoles(String roles) {
	this.roles = roles;
    }

    public String getPresu() {
	return presu;
    }

    public void setPresu(String presu) {
	this.presu = presu;
    }

    public String getCalendar() {
	return calendar;
    }

    public void setCalendar(String calendar) {
	this.calendar = calendar;
    }

    public String getRiesgo() {
	return riesgo;
    }

    public void setRiesgo(String riesgo) {
	this.riesgo = riesgo;
    }

    public Vector<Probabilidad> getProbabilidad() {
	return probabilidad;
    }

    public void setProbabilidad(Vector<Probabilidad> probabilidad) {
	this.probabilidad = probabilidad;
    }

    public Vector<Impacto> getImpacto() {
	return impacto;
    }

    public void setImpacto(Vector<Impacto> impacto) {
	this.impacto = impacto;
    }

    public String getContingencia() {
	return contingencia;
    }

    public void setContingencia(String contingencia) {
	this.contingencia = contingencia;
    }

    public String getFormato() {
	return formato;
    }

    public void setFormato(String formato) {
	this.formato = formato;
    }

    public Double getCorte() {
	return corte;
    }

    public void setCorte(Double corte) {
	this.corte = corte;
    }

    public String getRango() {
	return rango;
    }

    public void setRango(String rango) {
	this.rango = rango;
    }

}
