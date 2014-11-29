package impl.miw.presentation.plan;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;

import com.miw.infrastructure.log.LogService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de cambio de versión en el
 * plan de riesgos en la aplicación, extiende de ActionSupport que nos
 * proporciona una implementación por defecto para las acciones más comunes con
 * implementación de una interface “aware” para alojar objetos que puedan estar
 * a disposición en otras partes de la aplicación
 * 
 * @author Pablo
 * 
 */
public class ChangeAction extends ActionSupport implements ApplicationAware {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> application;
    private LogService log;
    private String version;

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
     * Setter que establece el mapa de propiedades de la aplicación. Esto dará
     * acceso a un mapa en el que puedan poner objetos que deben estar a
     * disposición en otras partes de la aplicación.
     * 
     * @param arg0
     *            un mapa de propiedades de la aplicación.
     */
    @Override
    public void setApplication(Map<String, Object> arg0) {
	application = arg0;
    }

    /**
     * Método que implementa la ejecución del action de Struts con los datos del
     * request
     * 
     * @return String cadena que será procesada en struts.xml
     */
    @Override
    public String execute() {
	log.debug("Procesando el execute de Change");

	try {
	    application.put("version", Double.parseDouble(version));
	} catch (Exception e) {
	    addActionError(getText("error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	return (SUCCESS);
    }

    /*
     * Getter y Setter del Request
     */

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    /*
     * Fin Getter y Setter del Request
     */
}
