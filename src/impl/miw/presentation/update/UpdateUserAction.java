package impl.miw.presentation.update;

import com.miw.business.ProjectService;
import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para la acción de actualización de los datos
 * de las tablas por parte del administrador de los tipos de usuarios de la
 * aplicación, extiende de ActionSupport que nos proporciona una implementación
 * por defecto para las acciones más comunes con implementación del ModelDriven
 * que proporciona un objeto de modelo que se inserta en el ValueStack además de
 * la propia acción
 * 
 * @author Pablo
 * 
 */
public class UpdateUserAction extends ActionSupport implements
	ModelDriven<User> {

    private static final long serialVersionUID = -6047411842966496456L;
    private User user;
    private UserService userService;
    private ProjectService projectService;
    private Long idUserUpdate;
    private String loginUserUpdate;
    private String emailUserUpdate;
    private Long idProjectUserUpdate;

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
	log.debug("Invocado el getUserService de UpdateUser");
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
	log.debug("Invocado el setUserService de UpdateUser");
	this.userService = userService;
    }

    /**
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de proyecto
     * 
     * @return ProjectService Objeto que hace referencia a la interfaz
     */
    public ProjectService getProjectService() {
	log.debug("Invocado el getProjectService de UpdateProject");
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
	log.debug("Invocado el setProjectService de UpdateProject");
	this.projectService = projectService;
    }

    /**
     * Método que implementa la ejecución del action de Struts con los datos del
     * request
     * 
     * @return String cadena que será procesada en struts.xml
     */
    @Override
    public String execute() {
	log.debug("Procesando el execute de UpdateUser con ModelDriven");
	log.debug("Update User ");
	log.debug("Id: " + getIdUserUpdate());
	log.debug("Nombre: " + getLoginUserUpdate());
	log.debug("Email: " + getEmailUserUpdate());
	log.debug("Id.Project: " + getIdProjectUserUpdate());

	User updateUser = new User();
	updateUser.setId(getIdUserUpdate());
	updateUser.setLogin(getLoginUserUpdate());
	updateUser.setEmail(getEmailUserUpdate());
	updateUser.setIdProyecto(getIdProjectUserUpdate());

	String ms;
	try {
	    if (projectService.seekProject(getIdProjectUserUpdate()).compareTo(
		    " ") != 0) {
		if (projectService.seekProject(getIdProjectUserUpdate())
			.compareTo("") == 0) {
		    ms = userService.setUpdateUserAdmin(updateUser);
		    log.debug(ms);
		    if (ms.compareToIgnoreCase("Actualizado") == 0) {
			addActionMessage(getText("update"));
		    } else {
			addActionError(getText("noUpdate"));
		    }
		} else {
		    addActionError(getText("manager"));
		    log.error("Ya existe un Manager");
		}
	    } else {
		addActionError(getText("project"));
		log.error("No existe Proyecto");
	    }
	} catch (Exception e) {
	    addActionError(getText("noUpdate"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */
    public Long getIdUserUpdate() {
	return idUserUpdate;
    }

    public void setIdUserUpdate(Long idUserUpdate) {
	this.idUserUpdate = idUserUpdate;
    }

    public String getLoginUserUpdate() {
	return loginUserUpdate;
    }

    public void setLoginUserUpdate(String loginUserUpdate) {
	this.loginUserUpdate = loginUserUpdate;
    }

    public String getEmailUserUpdate() {
	return emailUserUpdate;
    }

    public void setEmailUserUpdate(String emailUserUpdate) {
	this.emailUserUpdate = emailUserUpdate;
    }

    public Long getIdProjectUserUpdate() {
	return idProjectUserUpdate;
    }

    public void setIdProjectUserUpdate(Long idProjectUserUpdate) {
	this.idProjectUserUpdate = idProjectUserUpdate;
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
	return user;
    }
}
