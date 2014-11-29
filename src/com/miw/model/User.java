package com.miw.model;

/**
 * Modelo de datos para los usuarios, tanto para el admin, manager, como demas
 * componentes del grupo de proyecto
 * 
 * @author Pablo
 * 
 */
public class User {
    private Long id;
    private String login;
    private String email;
    private String password;
    private String language;
    private Boolean admin;
    private Boolean manager;
    private Long idProyecto;
    private String create;
    private String modify;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public Boolean isAdmin() {
	return admin;
    }

    public void setAdmin(Boolean admin) {
	this.admin = admin;
    }

    public Boolean isManager() {
	return manager;
    }

    public void setManager(Boolean manager) {
	this.manager = manager;
    }

    public Long getIdProyecto() {
	return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
	this.idProyecto = idProyecto;
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
