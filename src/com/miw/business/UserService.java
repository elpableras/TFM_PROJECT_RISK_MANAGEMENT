package com.miw.business;

import impl.miw.presentation.passforgotten.PassForgottenAction;

import java.util.Vector;

import javax.mail.MessagingException;

import com.miw.model.Project;
import com.miw.model.User;

/**
 * Facade. Interfaz entre la capa de Presentación y Negocio para datos de
 * usuarios
 * 
 * @author Pablo
 * 
 */
public interface UserService {
    public Vector<User> getUsers() throws Exception;

    public String setUser(User user) throws Exception;

    public Boolean getPassForgotten(PassForgottenAction passForgottenAction)
	    throws MessagingException, Exception;

    public String setUpdateUser(User user) throws Exception;

    public Vector<User> getManagers() throws Exception;

    public void send(User user, int optionSend, int i, Project pro)
	    throws MessagingException;

    public User seekUser(String email) throws Exception;

    public Vector<User> getUsers(Long idProyecto) throws Exception;

    public Boolean deleteUser(Long idUser) throws Exception;

    public String setUpdateUserAdmin(User updateUser) throws Exception;

    public boolean getManager(Long idUserUpdate, Long idProject) throws Exception;
}
