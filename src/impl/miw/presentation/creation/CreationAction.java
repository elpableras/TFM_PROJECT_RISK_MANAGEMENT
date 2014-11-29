package impl.miw.presentation.creation;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.ProjectService;
import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Project;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para la acción de la creación de los
 * proyectos con los datos necesarios para tenerlo en la aplicación, extiende de
 * ActionSupport que nos proporciona una implementación por defecto para las
 * acciones más comunes con implementación de una interface “aware” para alojar
 * objetos que puedan estar a disposición en otras partes de la aplicación y del
 * ModelDriven que proporciona un objeto de modelo que se inserta en el
 * ValueStack además de la propia acción
 * 
 * @author Pablo
 * 
 */
public class CreationAction extends ActionSupport implements
	ServletRequestAware, ModelDriven<Project> {

    private static final long serialVersionUID = 5517366146230360848L;

    private final Project project = new Project();

    private ProjectService projectService;
    private UserService userService;
    private HttpServletRequest request;

    private String nameProjectRegister;
    private String dateProjectRegister;
    private String managerProjectRegister;
    private String emailProjectRegister;
    private Integer stepProjectRegister;

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
	log.debug("Invocado el getProjectService de Creation");
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
	log.debug("Invocado el setProjectService de Creation");
	this.projectService = projectService;
    }

    /**
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de usuarios
     * 
     * @return UserService Objeto que hace referencia a la interfaz
     */
    public UserService getuserService() {
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
	log.debug("Invocado el setServletRequest de Creation");
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
	log.debug("Procesando el execute de Creation con ModelDriven");
	log.debug("Proyecto ");
	log.debug("Nombre: " + getNameProjectRegister());
	log.debug("Manager: " + getManagerProjectRegister());
	log.debug("Email: " + getEmailProjectRegister());
	log.debug("Fecha: " + getDateProjectRegister());
	log.debug("Siguiente Paso: " + getStepProjectRegister());

	Project creationProject = new Project();
	creationProject.setNombre(getNameProjectRegister());
	creationProject.setManager(getManagerProjectRegister());
	creationProject.setEmail(getEmailProjectRegister());
	creationProject.setFecha(getDateProjectRegister());
	creationProject.setPaso(getStepProjectRegister());
	try {
	    log.debug(projectService.setProject(creationProject));
	} catch (Exception e1) {
	    log.error(e1.getClass() + " " + e1.getMessage());
	    addActionError(getText("incorrect.creation"));
	    return ERROR;
	}
	request.getSession().setAttribute("project", creationProject);
	User user = (User) request.getSession().getAttribute(
		"managerRegistrado");
	try {
	    // COMUNICACIONES
	    userService.send(user, 1, 2, creationProject);
	} catch (MessagingException e) {
	    log.error(e.getClass() + " " + e.getMessage());
	    addActionError(getText("error.email"));
	    return ERROR;
	}
	addActionMessage(getText("correct.creation"));
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */
    public String getNameProjectRegister() {
	return nameProjectRegister;
    }

    public void setNameProjectRegister(String nameProjectRegister) {
	this.nameProjectRegister = nameProjectRegister;
    }

    public String getDateProjectRegister() {
	return dateProjectRegister;
    }

    public void setDateProjectRegister(String dateProjectRegister) {
	this.dateProjectRegister = dateProjectRegister;
    }

    public String getManagerProjectRegister() {
	return managerProjectRegister;
    }

    public void setManagerProjectRegister(String managerProjectRegister) {
	this.managerProjectRegister = managerProjectRegister;
    }

    public Integer getStepProjectRegister() {
	return stepProjectRegister;
    }

    public void setStepProjectRegister(Integer stepProjectRegister) {
	this.stepProjectRegister = stepProjectRegister;
    }

    public String getEmailProjectRegister() {
	return emailProjectRegister;
    }

    public void setEmailProjectRegister(String emailProjectRegister) {
	this.emailProjectRegister = emailProjectRegister;
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
