package com.miw.model;

/**
 * Modelo de datos para la información de la respuesta de los riesgos
 * 
 * @author Pablo
 * 
 */

public class Respuesta {

    private Long idP;
    private Long idU;
    private Integer option = -1;
    private Integer id = 0;
    private String nombre = "";
    private String descripcion = "";
    private String categoria = "";
    private String status = "";
    private String causas = "";

    private Integer probabilidad = 0;
    private String impacto = "";
    private Double valor = 0.0;
    private String response = "";

    private String fechaRevisada = "";
    private Integer probabilidadRevisada = 0;
    private String impactoRevisado = "";
    private Double valorRevisado = 0.0;
    private String responseRevisado = "";

    private String derivado = "";
    private String residual = "";
    private String contingencia = "";
    private String presupuesto = "";
    private String planificacion = "";
    private String comentarios = "";
    private String monitorizacion = "";
    private String indicador = "";
    private String evaluacion = "";

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

    public Integer getOption() {
	return option;
    }

    public void setOption(Integer option) {
	this.option = option;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

    public String getCategoria() {
	return categoria;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getCausas() {
	return causas;
    }

    public void setCausas(String causas) {
	this.causas = causas;
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

    public String getFechaRevisada() {
	return fechaRevisada;
    }

    public void setFechaRevisada(String fechaRevisada) {
	this.fechaRevisada = fechaRevisada;
    }

    public Integer getProbabilidadRevisada() {
	return probabilidadRevisada;
    }

    public void setProbabilidadRevisada(Integer probabilidadRevisada) {
	this.probabilidadRevisada = probabilidadRevisada;
    }

    public String getImpactoRevisado() {
	return impactoRevisado;
    }

    public void setImpactoRevisado(String impactoRevisado) {
	this.impactoRevisado = impactoRevisado;
    }

    public Double getValorRevisado() {
	return valorRevisado;
    }

    public void setValorRevisado(Double valorRevisado) {
	this.valorRevisado = valorRevisado;
    }

    public String getResponseRevisado() {
	return responseRevisado;
    }

    public void setResponseRevisado(String responseRevisado) {
	this.responseRevisado = responseRevisado;
    }

    public String getDerivado() {
	return derivado;
    }

    public void setDerivado(String derivado) {
	this.derivado = derivado;
    }

    public String getResidual() {
	return residual;
    }

    public void setResidual(String residual) {
	this.residual = residual;
    }

    public String getContingencia() {
	return contingencia;
    }

    public void setContingencia(String contingencia) {
	this.contingencia = contingencia;
    }

    public String getPresupuesto() {
	return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
	this.presupuesto = presupuesto;
    }

    public String getPlanificacion() {
	return planificacion;
    }

    public void setPlanificacion(String planificacion) {
	this.planificacion = planificacion;
    }

    public String getComentarios() {
	return comentarios;
    }

    public void setComentarios(String comentarios) {
	this.comentarios = comentarios;
    }

    public String getMonitorizacion() {
	return monitorizacion;
    }

    public void setMonitorizacion(String monitorizacion) {
	this.monitorizacion = monitorizacion;
    }

    public String getIndicador() {
	return indicador;
    }

    public void setIndicador(String indicador) {
	this.indicador = indicador;
    }

    public String getEvaluacion() {
	return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
	this.evaluacion = evaluacion;
    }

}
