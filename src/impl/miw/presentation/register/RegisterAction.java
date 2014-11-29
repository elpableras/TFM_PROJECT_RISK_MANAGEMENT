package impl.miw.presentation.register;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para la acción de registrar un nuevo manager
 * sin proyecto asignado en la aplicación, extiende de ActionSupport que nos
 * proporciona una implementación por defecto para las acciones más comunes con
 * implementación de una interface “aware” para alojar objetos que puedan estar
 * a disposición en otras partes de la aplicación y del ModelDriven que
 * proporciona un objeto de modelo que se inserta en el ValueStack además de la
 * propia acción
 * 
 * @author Pablo
 * 
 */
public class RegisterAction extends ActionSupport implements
	ServletRequestAware, ModelDriven<User> {

    private static final long serialVersionUID = -5248426744413574083L;

    private final User usuario = new User();

    private UserService userService;
    private HttpServletRequest request;

    private String loginRegister;
    private String emailRegister;
    private String languageRegister;
    private String passwordRegister;
    private boolean adminRegister;
    private boolean managerRegister;
    private LogService log;

    /**
     * Getter de la Infraestructura de Log
     * 
     * @return Log
     */
    public LogService getLog() {
	return log;
    }

    /**
     * Setter de la infraestructura de Log
     * 
     * @param log
     */
    public void setLog(LogService log) {
	this.log = log;
    }

    /**
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de usuarios
     * 
     * @return UserService Objeto que hace referencia a la interfaz
     */
    public UserService getUserService() {
	log.debug("Invocado el getUserService de Register");
	return userService;
    }

    /**
     * Setter para establecer la interfaz para los servicios aplicados al modelo
     * de usuarios
     * 
     * @param userService
     *            objecto interfaz
     */
    public void setUserService(UserService userService) {
	log.debug("Invocado el setUserService de Register");
	this.userService = userService;
    }

    /**
     * Setter que establece la solicitud al objeto HTTP en las clases de la
     * aplicación
     * 
     * @param httpServletRequest
     *            petición del Servlet
     */
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
	log.debug("Invocado el setServletRequest de Register");
	this.request = httpServletRequest;
    }

    /**
     * Método que implementa la ejecución del action de Struts con los datos del
     * request
     * 
     * @return String cadena que será procesada en struts.xml
     */
    @Override
    public String execute() {
	log.debug("Procesando el execute de Register con ModelDriven");
	log.debug("Usuario Registro: " + getLoginRegister());
	log.debug("Email Registro: " + getEmailRegister());
	log.debug("Language Registro: " + getLanguageRegister());
	log.debug("Password Registro: " + getPasswordRegister());
	log.debug("Admin: " + isAdminRegister());
	log.debug("Manager: " + isManagerRegister());

	User user = new User();
	user.setLogin(getLoginRegister());
	user.setEmail(getEmailRegister());
	user.setLanguage(getLanguageRegister());
	user.setPassword(getPasswordRegister());
	user.setAdmin(isAdminRegister());
	user.setManager(isManagerRegister());

	String correo;

	try {
	    correo = userService.setUser(user);

	    log.debug(correo);

	    if (correo.equalsIgnoreCase("Email")) {
		log.debug("Email Repetido");
		addActionError(getText("email.repeat"));
		return ERROR;
	    }
	} catch (Exception e1) {
	    log.error(e1.getClass() + " " + e1.getMessage());
	    addActionError(getText("error"));
	}

	if (isManagerRegister())
	    request.getSession().setAttribute("managerRegistrado", user);
	try {
	    // COMUNICACIONES
	    userService.send(user, 1, 1, null);
	} catch (MessagingException e) {
	    log.error(e.getClass() + " " + e.getMessage());
	    addActionError(getText("error.email"));
	    return ERROR;
	}
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */
    public String getLoginRegister() {
	return loginRegister;
    }

    public void setLoginRegister(String loginRegister) {
	this.loginRegister = loginRegister;
    }

    public String getPasswordRegister() {
	return passwordRegister;
    }

    public void setPasswordRegister(String passwordRegister) {
	this.passwordRegister = passwordRegister;
    }

    public String getLanguageRegister() {
	return languageRegister;
    }

    public void setLanguageRegister(String languageRegister) {
	this.languageRegister = languageRegister;
    }

    public String getEmailRegister() {
	return emailRegister;
    }

    public void setEmailRegister(String emailRegister) {
	this.emailRegister = emailRegister;
    }

    public boolean isAdminRegister() {
	return adminRegister;
    }

    public void setAdminRegister(boolean adminRegister) {
	this.adminRegister = adminRegister;
    }

    public boolean isManagerRegister() {
	return managerRegister;
    }

    public void setManagerRegister(boolean managerRegister) {
	this.managerRegister = managerRegister;
    }

    /*
     * Fin Getter y Setter del Request
     */

    /**
     * Getter para obtener el modelo que se inserta en la ValueStack en lugar de
     * la propia acción
     * 
     * @return User modelo de usuario
     */
    @Override
    public User getModel() {
	return usuario;
    }
}
