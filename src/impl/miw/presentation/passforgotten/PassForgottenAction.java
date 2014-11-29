package impl.miw.presentation.passforgotten;

import javax.mail.MessagingException;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para recuperar la contraseña olvidada para
 * entrar en la aplicación, extiende de ActionSupport que nos proporciona una
 * implementación por defecto para las acciones más comunes con implementación
 * de ModelDriven que proporciona un objeto de modelo que se inserta en el
 * ValueStack además de la propia acción
 * 
 * @author Pablo
 * 
 */
public class PassForgottenAction extends ActionSupport implements
	ModelDriven<User> {

    private static final long serialVersionUID = -682185838463206923L;
    private UserService userService;
    private final User usuario = new User();
    private String email;
    private String password;
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
	log.debug("Invocado el getUserService de PassForgotten");
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
	log.debug("Invocado el setUserService de PassForgotten");
	this.userService = userService;
    }

    /**
     * Método que implementa la ejecución del action de Struts con los datos del
     * request
     * 
     * @return String cadena que será procesada en struts.xml
     */
    @Override
    public String execute() {
	log.debug("Procesando el execute de PassForgotten con ModelDriven");
	log.debug("Email: " + usuario.getEmail());

	try {
	    if (userService.getPassForgotten(this)) {
		log.debug("Correo correcto, contraseña enviada");
		this.addActionMessage(getText("email.valid"));
	    } else {
		log.error("Correo incorrecto");
		this.addActionError(getText("error.email"));
		return (ERROR);
	    }
	} catch (MessagingException e) {
	    this.addActionError(getText("email.wrong"));
	    log.error(e.getClass() + " " + e.getMessage());
	} catch (Exception e) {
	    this.addActionError(getText("error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
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