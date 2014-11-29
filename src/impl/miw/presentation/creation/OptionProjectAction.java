package impl.miw.presentation.creation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.ProjectService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Project;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de actualización o
 * eliminación de los diferentes datos de los proyectos de la aplicación,
 * extiende de ActionSupport que nos proporciona una implementación por defecto
 * para las acciones más comunes con implementación de una interface “aware”
 * para alojar objetos que puedan estar a disposición en otras partes de la
 * aplicación
 * 
 * @author Pablo
 * 
 */
public class OptionProjectAction extends ActionSupport implements
	ServletRequestAware {

    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private LogService log;
    private ProjectService projectService;
    private String updateId;
    private String updateNombre;
    private String updateManager;
    private String updateEmail;
    private String updateFecha;
    private String updatePaso;
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
     * modelo de proyecto
     * 
     * @return ProjectService Objeto que hace referencia a la interfaz
     */
    public ProjectService getProjectService() {
	log.debug("Invocado el getProjectService de OptionProject");
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
	log.debug("Invocado el setProjectService  de OptionProject");
	this.projectService = projectService;
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
	log.debug("Invocado el setServletRequest de OptionProject");
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
	log.debug("Procesando el execute de OptionProject");
	Project project = new Project();
	if (getUpdateId() != null) {
	    project.setId(Long.valueOf(getUpdateId()));
	    project.setNombre(getUpdateNombre());
	    project.setManager(getUpdateManager());
	    project.setEmail(getUpdateEmail());
	    project.setFecha(getUpdateFecha());
	    project.setPaso(Integer.valueOf(getUpdatePaso()));
	    request.getSession().setAttribute("project", project);
	} else if (getDelete() != null) {
	    try {
		if (projectService.deleteProject(Long.valueOf(getDelete()))) {
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

    public String getUpdateNombre() {
	return updateNombre;
    }

    public void setUpdateNombre(String updateNombre) {
	this.updateNombre = updateNombre;
    }

    public String getUpdateManager() {
	return updateManager;
    }

    public void setUpdateManager(String updateManager) {
	this.updateManager = updateManager;
    }

    public String getUpdateEmail() {
	return updateEmail;
    }

    public void setUpdateEmail(String updateEmail) {
	this.updateEmail = updateEmail;
    }

    public String getUpdateFecha() {
	return updateFecha;
    }

    public void setUpdateFecha(String updateFecha) {
	this.updateFecha = updateFecha;
    }

    public String getUpdatePaso() {
	return updatePaso;
    }

    public void setUpdatePaso(String updatePaso) {
	this.updatePaso = updatePaso;
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