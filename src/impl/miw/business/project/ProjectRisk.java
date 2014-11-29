package impl.miw.business.project;

import java.util.Vector;

import com.miw.business.ProjectService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Project;
import com.miw.model.User;
import com.miw.persistence.ProjectDataService;

/**
 * Clase que implementa las operaciones para la capa de Negocio para los
 * proyectos
 * 
 * @author Pablo
 * 
 */
public class ProjectRisk implements ProjectService {

    private ProjectDataService projectDataService;
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
     * Getter de la interfaz entre el negocio y la persistencia de datos del
     * proyecto
     * 
     * @return ProjectDataService interfaz entre el negocio y la persistencia
     */
    public ProjectDataService getProjectDataService() {
	return projectDataService;
    }

    /**
     * Setter de la interfaz entre el negocio y la persistencia de datos del
     * proyecto
     * 
     * @param projectDataService
     *            interfaz entre el negocio y la persistencia
     */
    public void setProjectDataService(ProjectDataService projectDataService) {
	this.projectDataService = projectDataService;
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * de los proyectos
     * 
     * @return Vector vector con información de los proyectos
     * @throws Exception
     */
    @Override
    public Vector<Project> getProjects() throws Exception {
	log.debug("Entrando en getProjects");
	return projectDataService.getProjects();
    }

    /**
     * Setter de la interfaz entre presentación y negocio para establecer los
     * datos de los proyectos
     * 
     * @param project
     *            datos con el proyecto
     * @return String cadena para el log
     * @throws Exception
     */
    @Override
    public String setProject(Project project) throws Exception {
	log.debug("Entrando en setProjects");
	return projectDataService.setProject(project);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * del proyecto
     * 
     * @param user
     *            usuario de ese proyecto
     * @return Project proyecto
     * @throws Exception
     */
    @Override
    public Project getProject(User user) throws Exception {
	log.debug("Entrando en getProject");
	return projectDataService.getProject(user);
    }

    /**
     * Método de la interfaz entre presentación y negocio para eliminar el
     * proyecto
     * 
     * @param idProject
     *            identificación del proyecto
     * @return Booelan de comprobación para el borrado
     * @throws Exception
     */
    @Override
    public Boolean deleteProject(Long idProject) throws Exception {
	log.debug("Entrando en getProject");
	return projectDataService.deleteProject(idProject);
    }

    /**
     * Método de la interfaz entre presentación y negocio para la actualización
     * de datos del proyecto
     * 
     * @param updateProject
     *            proyecto a actualizar
     * @return String valor devuelto para el log
     * @throws Exception
     */
    @Override
    public String setUpdateProject(Project updateProject) throws Exception {
	log.debug("Entrando en setUpdateProject");
	return projectDataService.setUpdateProject(updateProject);
    }

    /**
     * Método de la interfaz entre presentación y negocio para la busqueda de
     * proyectos por identificador
     * 
     * @param idProjectUserUpdate
     *            identificador del usuario del proyecto
     * @return String cadena para comprobación
     * @throws Exception
     */
    @Override
    public String seekProject(Long idProjectUserUpdate) throws Exception {
	log.debug("Entrando en seekProject");
	return projectDataService.seekProject(idProjectUserUpdate);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * del proyecto por medio de su identificador
     * 
     * @param idP
     *            identificador del proyecto
     * @return Project proyecto
     * @throws Exception
     */
    @Override
    public Project getProject(Long idP) throws Exception {
	log.debug("Entrando en getProject");
	return projectDataService.getProject(idP);
    }

    /**
     * Método para actualizar el paso actual del proyecto
     * 
     * @param idP
     *            identificador del proyecto
     * 
     * @param step
     *            paso a actualizar en el proyecto
     * 
     * @throws Exception
     */
    @Override
    public void updateStep(Long idP, Integer step) throws Exception {
	log.debug("Entrando en updateStep");
	projectDataService.updateStep(idP, step);
    }
}
