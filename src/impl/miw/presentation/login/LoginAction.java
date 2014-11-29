package impl.miw.presentation.login;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.UserService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Clase de la capa de presentación para la acción de logueo de los diferentes
 * usuarios de la aplicación comprobandose que existán en la base de datos,
 * extiende de ActionSupport que nos proporciona una implementación por defecto
 * para las acciones más comunes con implementación de una interface “aware”
 * para alojar objetos que puedan estar a disposición en otras partes de la
 * aplicación y del ModelDriven que proporciona un objeto de modelo que se
 * inserta en el ValueStack además de la propia acción
 * 
 * @author Pablo
 * 
 */
public class LoginAction extends ActionSupport implements ServletRequestAware,
	ModelDriven<User> {

    private static final long serialVersionUID = 9105304741910854626L;
    private final User usuario = new User();
    private UserService userService;
    private HttpServletRequest request;

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
	log.debug("Invocado el getUserService de Login");
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
	log.debug("Invocado el setUserService de Login");
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
	log.debug("Invocado el setServletRequest de Login");
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
	// Supuestamente, por "dependecy injection" debería
	// tener un: usuario, password
	User user = (User) request.getSession().getAttribute(
		"usuarioRegistrado");
	if (user != null) {
	    usuario.setId(user.getId());
	    usuario.setLogin(user.getLogin());
	    usuario.setEmail(user.getEmail());
	    usuario.setPassword(user.getPassword());
	    usuario.setAdmin(user.isAdmin());
	    usuario.setManager(user.isManager());
	    usuario.setLanguage(user.getLanguage());
	    usuario.setIdProyecto(user.getIdProyecto());
	    request.getSession().setAttribute("usuario", usuario);
	    return SUCCESS;
	}
	log.debug("Procesando el execute de Login con ModelDriven");
	log.debug("Id: " + usuario.getId());
	log.debug("Nombre: " + usuario.getLogin());
	log.debug("Email: " + usuario.getEmail());
	log.debug("Password: " + usuario.getPassword());
	log.debug("Admin: " + usuario.isAdmin());
	log.debug("Manager: " + usuario.isManager());
	log.debug("Language: " + usuario.getLanguage());

	// Recuperando los usuarios de la base de datos
	Vector<User> users;
	try {
	    users = userService.getUsers();

	    for (int i = 0; i < users.size(); i++) {

		// Colocamos el usuario en sesión. En caso de saltar
		// una excepción, no llegaría a colocarse nada.
		if (usuario.getEmail().equals(users.get(i).getEmail())
			&& usuario.getPassword().equals(
				users.get(i).getPassword())) {
		    request.getSession().setAttribute("usuario", users.get(i));
		    break;
		}
	    }
	} catch (Exception e) {
	    log.error(e.getClass() + " " + e.getMessage());
	    addActionError(getText("user.error") + " "
		    + getText("password.error"));
	    return ERROR;
	}
	return SUCCESS;
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
