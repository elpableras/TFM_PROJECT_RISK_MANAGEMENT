package impl.miw.presentation.counter;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;

import com.miw.infrastructure.log.LogService;
import com.miw.model.Counter;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para información de estadisticas de entrada
 * de usuarios en la aplicación, extiende de ActionSupport que nos proporciona
 * una implementación por defecto para las acciones más comunes con
 * implementación de un interface “aware” para alojar objetos que puedan estar a
 * disposición en otras partes de la aplicación.
 * 
 * @author Pablo
 * 
 */
public class CounterAction extends ActionSupport implements ApplicationAware {

    private static final long serialVersionUID = 5390485447488424041L;
    private Map<String, Object> application;
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
     * Método que implementa la ejecución del action de Struts con los datos del
     * request
     * 
     * @return String cadena que será procesada en struts.xml
     */
    @Override
    public String execute() {
	log.debug("Procesando el execute de Counter");
	Counter counter = (Counter) application.get("counter");
	if (counter == null) {
	    counter = new Counter();
	}
	counter.increment();
	counter.ip();
	counter.timestamp();
	log.debug("Visitas: " + counter.getValue());
	log.debug("Ip: " + counter.getIp());
	log.debug("Timestamp: " + counter.getTimestamp());
	application.put("counter", counter);

	return SUCCESS;
    }

    /**
     * Setter que establece el mapa de propiedades de la aplicación. Esto les
     * dará acceso a un mapa en el que puedan poner objetos que deben estar a
     * disposición en otras partes de la aplicación.
     * 
     * @param arg0
     *            - Un mapa de propiedades de la aplicación.
     */
    @Override
    public void setApplication(Map<String, Object> arg0) {
	this.application = arg0;
    }

}