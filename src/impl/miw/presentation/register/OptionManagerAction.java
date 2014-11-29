package impl.miw.presentation.register;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de actualización o
 * eliminación de los diferentes datos de los usuarios en los proyectos de la
 * aplicación, extiende de ActionSupport que nos proporciona una implementación
 * por defecto para las acciones más comunes con implementación de una interface
 * “aware” para alojar objetos que puedan estar a disposición en otras partes de
 * la aplicación
 * 
 * @author Pablo
 * 
 */
public class OptionManagerAction extends ActionSupport implements
	ServletRequestAware {

    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private LogService log;
    private UserService userService;
    private String updateId;
    private String updateLogin;
    private String updateEmail;
    private String updateIdProject;
    private String delete;

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
	log.debug("Invocado el getUserService de OptionManager");
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
	log.debug("Invocado el setUserService  de OptionManager");
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
	log.debug("Invocado el setServletRequest de OptionManager");
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
	log.debug("Procesando el execute de OptionManager");

	User user = new User();
	if (getUpdateId() != null) {
	    user.setId(Long.valueOf(getUpdateId()));
	    user.setLogin(getUpdateLogin());
	    user.setEmail(getUpdateEmail());
	    user.setIdProyecto(Long.valueOf(getUpdateIdProject()));
	    request.getSession().setAttribute("user", user);
	} else if (getDelete() != null) {
	    try {
		if (userService.deleteUser(Long.valueOf(getDelete()))) {
		    addActionMessage(getText("correct.delete"));
		    log.debug(getText("correct.delete"));
		} else {
		    addActionError(getText("incorrect.delete"));
		    log.debug(getText("incorrect.delete"));
		}
	    } catch (NumberFormatException e) {
		addActionError(getText("incorrect.number"));
		log.error(e.getClass() + " " + e.getMessage());
	    } catch (Exception e) {
		addActionError(getText("incorrect.delete"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	    return "home";
	}
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */
    public String getUpdateId() {
	return updateId;
    }

    public void setUpdateId(String updateId) {
	this.updateId = updateId;
    }

    public String getUpdateLogin() {
	return updateLogin;
    }

    public void setUpdateLogin(String updateLogin) {
	this.updateLogin = updateLogin;
    }

    public String getUpdateEmail() {
	return updateEmail;
    }

    public void setUpdateEmail(String updateEmail) {
	this.updateEmail = updateEmail;
    }

    public String getUpdateIdProject() {
	return updateIdProject;
    }

    public void setUpdateIdProject(String updateIdProject) {
	this.updateIdProject = updateIdProject;
    }

    public String getDelete() {
	return delete;
    }

    public void setDelete(String delete) {
	this.delete = delete;
    }
    /*
     * Fin Getter y Setter del Request
     */
}