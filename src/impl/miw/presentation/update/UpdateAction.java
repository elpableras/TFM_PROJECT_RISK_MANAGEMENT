package impl.miw.presentation.update;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para la acción de actualización de los datos
 * de los tipos de usuarios de la aplicación, extiende de ActionSupport que nos
 * proporciona una implementación por defecto para las acciones más comunes con
 * implementación de una interface “aware” para alojar objetos que puedan estar
 * a disposición en otras partes de la aplicación y del ModelDriven que
 * proporciona un objeto de modelo que se inserta en el ValueStack además de la
 * propia acción
 * 
 * @author Pablo
 * 
 */
public class UpdateAction extends ActionSupport implements ServletRequestAware,
	ModelDriven<User> {

    private static final long serialVersionUID = 5517366146230360848L;

    private final User user = new User();

    private UserService userService;
    private HttpServletRequest request;
    private Long idAccountUpdate;
    private String loginAccountUpdate;
    private String emailAccountUpdate;
    private String passAccountUpdate;
    private String languageAccountUpdate;
    private Boolean adminAccountUpdate;
    private Boolean managerAccountUpdate;
    private Long idProyectoAccountUpdate;

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
	log.debug("Invocado el getUserService de Update");
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
	log.debug("Invocado el setUserService de Update");
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
	log.debug("Invocado el setServletRequest de Update");
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
	log.debug("Procesando el execute de Update con ModelDriven");
	log.debug("Update User ");
	log.debug("Id: " + getIdAccountUpdate());
	log.debug("Login: " + getLoginAccountUpdate());
	log.debug("Email: " + getEmailAccountUpdate());
	log.debug("Pass: " + getPassAccountUpdate());
	log.debug("Lenguaje: " + getLanguageAccountUpdate());
	log.debug("idProyecto: " + getIdProyectoAccountUpdate());
	log.debug("Admin: " + getAdminAccountUpdate());
	log.debug("Manager: " + getManagerAccountUpdate());

	User updateUser = new User();
	updateUser.setId(getIdAccountUpdate());
	if(getLoginAccountUpdate().compareToIgnoreCase("")!=0)
	    updateUser.setLogin(getLoginAccountUpdate());
	if(getEmailAccountUpdate().compareToIgnoreCase("")!=0)
	    updateUser.setEmail(getEmailAccountUpdate());
	if(getPassAccountUpdate().compareToIgnoreCase("")!=0)
	    updateUser.setPassword(getPassAccountUpdate());
	updateUser.setLanguage(getLanguageAccountUpdate());
	updateUser.setIdProyecto(getIdProyectoAccountUpdate());
	updateUser.setAdmin(getAdminAccountUpdate());
	updateUser.setManager(getManagerAccountUpdate());

	String ms;
	try {
	    ms = userService.setUpdateUser(updateUser);

	    log.debug(ms);

	    if (ms.compareToIgnoreCase("Actualizado") == 0) {
		request.getSession().setAttribute("usuario", updateUser);
		addActionMessage(getText("update"));
	    } else {
		addActionError(getText("noUpdate"));
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
    public Long getIdAccountUpdate() {
	return idAccountUpdate;
    }

    public void setIdAccountUpdate(Long idAccountUpdate) {
	this.idAccountUpdate = idAccountUpdate;
    }

    public String getLoginAccountUpdate() {
	return loginAccountUpdate;
    }

    public void setLoginAccountUpdate(String loginAccountUpdate) {
	this.loginAccountUpdate = loginAccountUpdate;
    }

    public String getEmailAccountUpdate() {
	return emailAccountUpdate;
    }

    public void setEmailAccountUpdate(String emailAccountUpdate) {
	this.emailAccountUpdate = emailAccountUpdate;
    }

    public String getPassAccountUpdate() {
	return passAccountUpdate;
    }

    public void setPassAccountUpdate(String passAccountUpdate) {
	this.passAccountUpdate = passAccountUpdate;
    }

    public String getLanguageAccountUpdate() {
	return languageAccountUpdate;
    }

    public void setLanguageAccountUpdate(String languageAccountUpdate) {
	this.languageAccountUpdate = languageAccountUpdate;
    }

    public Boolean getAdminAccountUpdate() {
	return adminAccountUpdate;
    }

    public void setAdminAccountUpdate(Boolean adminAccountUpdate) {
	this.adminAccountUpdate = adminAccountUpdate;
    }

    public Boolean getManagerAccountUpdate() {
	return managerAccountUpdate;
    }

    public void setManagerAccountUpdate(Boolean managerAccountUpdate) {
	this.managerAccountUpdate = managerAccountUpdate;
    }

    public Long getIdProyectoAccountUpdate() {
	return idProyectoAccountUpdate;
    }

    public void setIdProyectoAccountUpdate(Long idProyectoAccountUpdate) {
	this.idProyectoAccountUpdate = idProyectoAccountUpdate;
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
