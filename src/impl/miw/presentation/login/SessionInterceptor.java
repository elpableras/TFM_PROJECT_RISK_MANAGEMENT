package impl.miw.presentation.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.infrastructure.log.LogService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Clase que crea un interceptor, cuando la sesión de la aplicación halla
 * caducado se pondrán las varibles a null con la implementación de una interfaz
 * "aware" para eliminar esas variables de sesión y extiende de
 * AbstractInterceptor
 * 
 * @author Pablo
 * 
 */
public class SessionInterceptor extends AbstractInterceptor implements
	ServletRequestAware {

    private static final long serialVersionUID = 1L;
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
     * Método de destrucción del interceptor
     */
    @Override
    public void destroy() {
	log.debug("Destroy invocado de SessionInterceptor");
    }

    /**
     * Método de inicialización del interceptor
     */
    @Override
    public void init() {
	log.debug("Init de SessionInterceptor");

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
     * Método de ejecución del interceptor, si la sesion ha expirado, te
     * devuelve al login
     * 
     * @param invocation
     *            Invocación de la acción
     * @return String cadena de la acción a procesar
     * @exception Exception
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
	ActionContext ctx = invocation.getInvocationContext();
	String uri = ctx.getName().toLowerCase();
	Map<String, Object> session = invocation.getInvocationContext()
		.getSession();

	if (session.isEmpty() && !uri.contains("login")
		&& !uri.contains("counter") && !uri.contains("forgotten")
		&& !uri.contains("passforgotten") && !uri.contains("index")) {
	    if (request.getSession().getAttribute("usuario") != null) {
		request.getSession().setAttribute("usuario", null);
		request.getSession().setAttribute("play", null);
		request.getSession().setAttribute("saveplan", null);
	    }
	    return "Login"; // session is empty/expired
	}

	return invocation.invoke();
    }
}