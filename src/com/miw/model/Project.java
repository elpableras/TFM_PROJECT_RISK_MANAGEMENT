package com.miw.model;

/**
 * Modelo de datos para los proyectos
 * 
 * @author Pablo
 * 
 */
public class Project {

    private Long id;
    private String nombre;
    private String manager;
    private String email;
    private String fecha;
    private Integer paso;
    private String create;
    private String modify;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public String getManager() {
	return manager;
    }

    public void setManager(String manager) {
	this.manager = manager;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Integer getPaso() {
	return paso;
    }

    public void setPaso(Integer paso) {
	this.paso = paso;
    }

    public String getCreate() {
	return create;
    }

    public void setCreate(String create) {
	this.create = create;
    }

    public String getModify() {
	return modify;
    }

    public void setModify(String modify) {
	this.modify = modify;
    }
}
