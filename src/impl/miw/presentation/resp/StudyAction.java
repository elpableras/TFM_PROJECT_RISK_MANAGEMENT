package impl.miw.presentation.resp;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.InfoService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Iden;
import com.miw.model.Info;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de buscar los tipos de
 * riesgos identificados anteriormente y mostrarlos en la aplicación, extiende
 * de ActionSupport que nos proporciona una implementación por defecto para las
 * acciones más comunes con implementación de dos interfaces “aware” para alojar
 * objetos que puedan estar a disposición en otras partes de la aplicación.
 * 
 * @author Pablo
 * 
 */
public class StudyAction extends ActionSupport implements ApplicationAware,
	ServletRequestAware {

    private static final long serialVersionUID = -8019808350875894645L;
    private HttpServletRequest request;
    private Map<String, Object> application;

    // BBDD
    private InfoService infoService;
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
     * modelo de info que se utiliza para la información de los riesgos
     * 
     * @return InfoService Objeto que hace referencia a la interfaz
     */
    public InfoService getInfoService() {
	log.debug("Invocado el getInfoService de Study");
	return infoService;
    }

    /**
     * Setter para establecer la interfaz para los servicios aplicados al modelo
     * de info que se utiliza para la información de los riesgos
     * 
     * @param infoService
     *            objecto interfaz
     */
    public void setInfoService(InfoService infoService) {
	this.infoService = infoService;
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
     * Setter que establece la solicitud al objeto HTTP en las clases de la
     * aplicación
     * 
     * @param httpServletRequest
     *            petición del Servlet
     */
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
	log.debug("Invocado el setServletRequest de Study");
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
	log.debug("Procesando el execute de Study");

	User u = (User) request.getSession().getAttribute("usuario");
	Long id = u.getId();
	Long idP = u.getIdProyecto();
	Double version = (Double) application.get("version");

	try {

	    Vector<Double> versiones = infoService.getVersion(id, idP);
	    if (versiones.size() == 0) {
		version = 1.0000;
		infoService.setInfo(id, idP, version, null);
	    } else {
		application.put("versiones", versiones);
		if (version == null) {
		    version = versiones.get(0);
		    application.put("version", version);
		}
	    }
	    Info info = infoService.getInfo(id, idP, version);
	    Double corte = info.getCorte();
	    request.getSession().setAttribute("corte", corte);

	    Vector<Iden> iden = infoService.getIden(id, idP);
	    Vector<Iden> study = new Vector<Iden>();
	    for (int i = 0; i < iden.size(); i++) {
		if (iden.get(i).getValor() >= corte) {
		    study.add(iden.get(i));
		}
	    }
	    application.put("study", study);

	} catch (Exception e) {
	    addActionError(getText("error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	request.getSession().setAttribute("play", 3);
	return (SUCCESS);
    }
}