package impl.miw.presentation.login;

import org.apache.struts2.StrutsStatics;

import com.miw.infrastructure.log.LogService;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Clase para implementar los interceptores, los cuales actuarán como un
 * filtroHTTP o un Proxy, proveerán un camino para suplir el pre-processing y
 * post-processing a través del action
 * 
 * @author Pablo
 * 
 */
public class LoginInterceptor extends AbstractInterceptor implements
	StrutsStatics {

    private static final long serialVersionUID = -4203964390619150335L;
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
	log.debug("Destroy invocado de LoginInterceptor");
    }

    /**
     * Método de inicialización del interceptor
     */
    @Override
    public void init() {
	log.debug("init de LoginInterceptor");

    }

    /**
     * Método de ejecución del interceptor, dependiendo que tipo de usuario
     * entre se le lleva a una acción o a otra en la aplicación
     * 
     * @param inv
     *            Invocación de la acción
     * @return String cadena de la acción a procesar
     */
    @Override
    public String intercept(ActionInvocation inv) throws Exception {
	ActionContext ctx = inv.getInvocationContext();
	User usuario = (User) ctx.getSession().get("usuario");
	String uri = ctx.getName().toLowerCase();
	if (usuario == null && !uri.contains("login")) {
	    return "login";
	} else if (usuario.isAdmin()) {
	    // Página del admin
	    return "admin";
	} else if (usuario.isManager()) {
	    // Página del manager
	    return "manager";
	} else {
	    // Página de demás miembros
	    return "miembro";
	}
    }
}
