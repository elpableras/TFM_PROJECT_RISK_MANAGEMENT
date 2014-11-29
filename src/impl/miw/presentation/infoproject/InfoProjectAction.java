package impl.miw.presentation.infoproject;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.ProjectService;
import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para información de los proyectos en la
 * aplicación, extiende de ActionSupport que nos proporciona una implementación
 * por defecto para las acciones más comunes con implementación de dos interface
 * “aware” para alojar objetos que puedan estar a disposición en otras partes de
 * la aplicación.
 * 
 * @author Pablo
 * 
 */
public class InfoProjectAction extends ActionSupport implements
	ServletRequestAware, ApplicationAware {
    private static final long serialVersionUID = -6346120559503444781L;

    private Map<String, Object> application;
    private HttpServletRequest request;
    private ProjectService projectService;
    private UserService userService;
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
     * modelo de proyecto
     * 
     * @return ProjectService Objeto que hace referencia a la interfaz
     */
    public ProjectService getProjectService() {
	log.debug("Invocado el getProjectService de InfoProject");
	return projectService;
    }

    /**
     * Setter para establecer la interfaz para los servicios aplicados al modelo
     * de proyecto
     * 
     * @param projectService
     *            objecto interfaz
     */
    public void setProjectService(ProjectService projectService) {
	log.debug("Invocado el setProjectService  de InfoProject");
	this.projectService = projectService;
    }

    /**
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de usuarios
     * 
     * @return UserService Objeto que hace referencia a la interfaz
     */
    public UserService getUserService() {
	log.debug("Invocado el getUserService de InfoProject");
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
	log.debug("Invocado el setUserService  de InfoProject");
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
	log.debug("Invocado el setServletRequest de InfoProject");
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
	log.debug("Procesando el execute de InfoProject");

	User user = (User) request.getSession().getAttribute("usuario");

	if (user.isAdmin()) {
	    try {
		application
			.put("listinfoproject", projectService.getProjects());
		request.getSession().setAttribute("listinfomanager",
			userService.getManagers());
		application.put("listinfomanager", userService.getManagers());
	    } catch (Exception e) {
		this.addActionError(getText("error"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	} else if (user.isManager()) {
	    try {
		application.put("listinfoprojectmanager",
			projectService.getProject(user));
		application.put("listinfomembers",
			userService.getUsers(user.getIdProyecto()));
	    } catch (Exception e) {
		this.addActionError(getText("error"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	}
	return (SUCCESS);
    }

    /**
     * Setter que establece el mapa de propiedades de la aplicación. Esto dará
     * acceso a un mapa en el que puedan poner objetos que deben estar a
     * disposición en otras partes de la aplicación.
     * 
     * @param arg0
     *            un mapa de propiedades de la aplicación.
     */
    @Override
    public void setApplication(Map<String, Object> arg0) {
	application = arg0;
    }
}