package impl.miw.presentation.update;

import com.miw.business.ProjectService;
import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Project;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para la acción de actualización de los datos
 * de las tablas por parte del administrador de los tipos de proyectos de la
 * aplicación, extiende de ActionSupport que nos proporciona una implementación
 * por defecto para las acciones más comunes con implementación del ModelDriven
 * que proporciona un objeto de modelo que se inserta en el ValueStack además de
 * la propia acción
 * 
 * @author Pablo
 * 
 */
public class UpdateProjectAction extends ActionSupport implements
	ModelDriven<Project> {

    private static final long serialVersionUID = -6047411842966496456L;
    private Project project;
    private ProjectService projectService;
    private UserService userService;
    private Long idProjectUpdate;
    private String nombreProjectUpdate;
    private String managerProjectUpdate;
    private String emailProjectUpdate;
    private String fechaProjectUpdate;
    private Integer pasoProjectUpdate;

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
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de usuarios
     * 
     * @return UserService Objeto que hace referencia a la interfaz
     */
    public UserService getUserService() {
	log.debug("Invocado el getUserService de UpdateProject");
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
	log.debug("Invocado el setUserService de UpdateProject");
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
	log.debug("Procesando el execute de UpdateProject con ModelDriven");
	log.debug("Update Project ");
	log.debug("Id: " + getIdProjectUpdate());
	log.debug("Nombre: " + getNombreProjectUpdate());
	log.debug("Manager: " + getManagerProjectUpdate());
	log.debug("Email: " + getEmailProjectUpdate());
	log.debug("Fecha: " + getFechaProjectUpdate());
	log.debug("Paso: " + getPasoProjectUpdate());

	Project updateProject = new Project();
	updateProject.setId(getIdProjectUpdate());
	updateProject.setNombre(getNombreProjectUpdate());
	updateProject.setManager(getManagerProjectUpdate());
	updateProject.setEmail(getEmailProjectUpdate());
	updateProject.setFecha(getFechaProjectUpdate());
	updateProject.setPaso(getPasoProjectUpdate());

	String ms;
	try {
	    if (userService.seekUser(getEmailProjectUpdate()) != null) {
		ms = projectService.setUpdateProject(updateProject);

		log.debug(ms);

		if (ms.compareToIgnoreCase("Actualizado") == 0) {
		    addActionMessage(getText("update"));
		} else {
		    addActionError(getText("noUpdate"));
		}
	    } else {
		addActionError(getText("noUser"));
		log.error("No existe usuario");
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
    public Long getIdProjectUpdate() {
	return idProjectUpdate;
    }

    public void setIdProjectUpdate(Long idProjectUpdate) {
	this.idProjectUpdate = idProjectUpdate;
    }

    public String getNombreProjectUpdate() {
	return nombreProjectUpdate;
    }

    public void setNombreProjectUpdate(String nombreProjectUpdate) {
	this.nombreProjectUpdate = nombreProjectUpdate;
    }

    public String getManagerProjectUpdate() {
	return managerProjectUpdate;
    }

    public void setManagerProjectUpdate(String managerProjectUpdate) {
	this.managerProjectUpdate = managerProjectUpdate;
    }

    public String getEmailProjectUpdate() {
	return emailProjectUpdate;
    }

    public void setEmailProjectUpdate(String emailProjectUpdate) {
	this.emailProjectUpdate = emailProjectUpdate;
    }

    public String getFechaProjectUpdate() {
	return fechaProjectUpdate;
    }

    public void setFechaProjectUpdate(String fechaProjectUpdate) {
	this.fechaProjectUpdate = fechaProjectUpdate;
    }

    public Integer getPasoProjectUpdate() {
	return pasoProjectUpdate;
    }

    public void setPasoProjectUpdate(Integer pasoProjectUpdate) {
	this.pasoProjectUpdate = pasoProjectUpdate;
    }

    /*
     * Fin Getter y Setter del Request
     */

    /**
     * Getter para obtener el modelo que se inserta en la ValueStack en lugar de
     * la propia acción
     * 
     * @return Project modelo de proyecto
     */
    @Override
    public Project getModel() {
	return project;
    }
}
