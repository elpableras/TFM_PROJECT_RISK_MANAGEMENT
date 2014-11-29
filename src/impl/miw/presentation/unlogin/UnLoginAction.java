package impl.miw.presentation.unlogin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para salir de sesión en la aplicación,
 * extiende de ActionSupport que nos proporciona una implementación por defecto
 * para las acciones más comunes y con implementación de una interface “aware”
 * para alojar objetos que puedan estar a disposición en otras partes de la
 * aplicación.
 * 
 * @author Pablo
 * 
 */
public class UnLoginAction extends ActionSupport implements ServletRequestAware {

    private static final long serialVersionUID = -231793042015025420L;
    private final User usuario = new User();
    private HttpServletRequest request;
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
     * Setter que establece la solicitud al objeto HTTP en las clases de la
     * aplicación
     * 
     * @param httpServletRequest
     *            petición del Servlet
     */
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
	log.debug("Invocado el setServletRequest de UnLogin");
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
	log.debug("Procesando el execute de UnLogin con ModelDriven");
	log.debug("Usuario: " + usuario.getLogin());
	log.debug("Password: " + usuario.getPassword());
	// Quitamos el usuario de sesión.
	if (request.getSession().getAttribute("usuario") != null) {
	    request.getSession().setAttribute("usuario", null);
	    request.getSession().setAttribute("play", null);
	    request.getSession().setAttribute("saveplan", null);
	    return SUCCESS;
	} else {
	    return ERROR;
	}
    }
}
