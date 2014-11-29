package impl.miw.presentation.iden;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.InfoService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Iden;
import com.miw.model.Impacto;
import com.miw.model.Info;
import com.miw.model.Probabilidad;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de carga de la información de
 * los riesgos identificados almacenados en la base de datos, extiende de
 * ActionSupport que nos proporciona una implementación por defecto para las
 * acciones más comunes con implementación de dos interface “aware” para alojar
 * objetos que puedan estar a disposición en otras partes de la aplicación.
 * 
 * @author Pablo
 * 
 */
public class LoadAction extends ActionSupport implements ApplicationAware,
	ServletRequestAware {

    private static final long serialVersionUID = 1L;

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
	log.debug("Invocado el getInfoService de Load");
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
	log.debug("Invocado el setServletRequest de Load");
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
	log.debug("Procesando el execute de Load");

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
	    Vector<Iden> iden = infoService.getIden(id, idP);

	    application.put("iden", iden);

	    Vector<Integer> vimpactos = new Vector<Integer>();
	    for (int i = 0; i < iden.size(); i++) {
		String impactos = iden.get(i).getImpacto();
		String[] impacto = impactos.split(" ");
		for (int j = 0; j < impacto.length; j++) {
		    vimpactos.add(Integer.parseInt(impacto[j]));
		}
	    }
	    application.put("valorimpactolist", vimpactos);

	    Vector<Probabilidad> vp = info.getProbabilidad();
	    application.put("probabilidadeslist", vp);

	    Vector<Impacto> vi = info.getImpacto();
	    application.put("impactolist", vi);

	    application.put("corte", info.getCorte());

	    Vector<Probabilidad> vdesc = new Vector<Probabilidad>();
	    Vector<Probabilidad> vdesc2 = new Vector<Probabilidad>();

	    for (int j = 0; j < vi.get(0).getProbabilidad().size(); j++) {
		vdesc.add(vi.get(0).getProbabilidad().get(j));
	    }
	    vdesc2.addAll(vdesc);
	    application.put("probabilidadlist", vdesc2);

	} catch (Exception e) {
	    addActionError(getText("error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	request.getSession().setAttribute("play", 2);
	return (SUCCESS);
    }
}