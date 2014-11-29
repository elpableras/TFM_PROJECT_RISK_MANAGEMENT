package com.miw.persistence;

import impl.miw.presentation.passforgotten.PassForgottenAction;

import java.util.Vector;

import com.miw.model.User;

/**
 * Facade. Interfaz entre la capa de Negocio y Persistencia para datos de
 * usuarios
 * 
 * @author Pablo
 * 
 */
public interface UserDataService {
    public Vector<User> getUsers() throws Exception;

    public String setUser(User user) throws Exception;

    public User getPassForgotten(PassForgottenAction passForgottenAction)
	    throws Exception;

    public String setUpdateUser(User user) throws Exception;

    public Vector<User> getManagers() throws Exception;

    public User getUser(String email) throws Exception;

    public Vector<User> getUsers(Long idProyecto) throws Exception;

    public Boolean deleteUser(Long idUser) throws Exception;

    public String setUpdateUserAdmin(User updateUser) throws Exception;
}
