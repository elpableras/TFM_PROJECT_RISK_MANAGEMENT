package impl.miw.presentation.register;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de busqueda de manager sin
 * proyecto asignado en la aplicación, extiende de ActionSupport que nos
 * proporciona una implementación por defecto para las acciones más comunes con
 * implementación de una interface “aware” para alojar objetos que puedan estar
 * a disposición en otras partes de la aplicación
 * 
 * @author Pablo
 * 
 */
public class SeekManagerFreeAction extends ActionSupport implements
	ServletRequestAware {
    private static final long serialVersionUID = -6346120559503444781L;

    private HttpServletRequest request;
    private LogService log;
    private UserService userService;
    private String addedManager;

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
	log.debug("Invocado el getUserService de RegisterManager");
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
	log.debug("Invocado el setUserService de RegisterManager");
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
	log.debug("Invocado el setServletRequest de RegisterManager");
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
	log.debug("Procesando el execute de RegisterManager");
	User user = new User();
	user.setEmail(getAddedManager());
	try {
	    user = userService.seekUser(user.getEmail());
	    request.getSession().setAttribute("managerRegistrado", user);
	} catch (Exception e) {
	    addActionError(getText("error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */
    public String getAddedManager() {
	return addedManager;
    }

    public void setAddedManager(String addedManager) {
	this.addedManager = addedManager;
    }
    /*
     * Fin Getter y Setter del Request
     */
}