package impl.miw.presentation.resp;

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
import com.miw.model.Respuesta;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de carga de la información de
 * las respuestas de riesgos almacenados en la base de datos, extiende de
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

    // Respuesta
    private Integer option;

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

	    Respuesta resp = infoService.getResp(id, idP, option);

	    if (resp.getId() > 0) {
		application.put("respuesta", resp);

		String impactos = resp.getImpacto();
		Vector<Integer> vimpactos = vImpactosResp(resp, impactos);
		application.put("impactosresplist", vimpactos);

		String impactosR = resp.getImpactoRevisado();
		Vector<Integer> vimpactosR = vImpactosResp(resp, impactosR);
		application.put("impactosRresplist", vimpactosR);

		String indicador = resp.getIndicador();
		Vector<String> indi = indicadores(resp, indicador);
		application.put("indicadoreslist", indi);

		String evaluacion = resp.getEvaluacion();
		Vector<String> eval = indicadores(resp, evaluacion);
		application.put("evaluacioneslist", eval);

		if (resp.getFechaRevisada() != null
			&& resp.getFechaRevisada().compareTo("") != 0) {
		    String f[] = resp.getFechaRevisada().split("/");
		    String fecha = f[2] + "-" + f[1] + "-" + f[0];
		    resp.setFechaRevisada(fecha);
		}
	    } else {
		request.getServletContext().removeAttribute("respuesta");
		request.getServletContext().removeAttribute("impactosresplist");
		request.getServletContext()
			.removeAttribute("impactosRresplist");
		request.getServletContext().removeAttribute("indicadoreslist");
		request.getServletContext().removeAttribute("evaluacioneslist");
		Vector<Integer> vimpactos = vImpactosIden(iden);
		application.put("valorimpactolist", vimpactos);
	    }

	    Vector<Probabilidad> vp = info.getProbabilidad();
	    application.put("probabilidadeslist", vp);

	    Vector<Impacto> vi = info.getImpacto();
	    application.put("impactolist", vi);

	    Vector<Probabilidad> vdesc = new Vector<Probabilidad>();
	    Vector<Probabilidad> vdesc2 = new Vector<Probabilidad>();
	    boolean activo = true;
	    for (int i = 0; i < vi.size(); i++) {
		for (int j = 0; j < vi.get(i).getProbabilidad().size(); j++) {
		    vdesc.add(vi.get(i).getProbabilidad().get(j));
		}
		if (activo) {
		    vdesc2.addAll(vdesc);
		    application.put("probabilidadlist", vdesc2);
		    activo = false;
		}
	    }

	} catch (Exception e) {
	    addActionError(getText("error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	request.getSession().setAttribute("play", 3);
	return (SUCCESS);
    }

    /**
     * Método para cargar el vector indicadores
     * 
     * @param resp
     *            objeto Respuesta donde se encuentran los datos
     * @param indiEva
     *            cadena con el indicador a evaluar
     * @return Vector vector con los indicadores
     */
    private Vector<String> indicadores(Respuesta resp, String indiEva) {
	Vector<String> vIndicadores = new Vector<String>();
	String[] array = indiEva.split("@");
	for (int j = 0; j < array.length; j++) {
	    vIndicadores.add(array[j]);
	}
	return vIndicadores;
    }

    /**
     * Método para obtener los impactos de la respuesta de los riesgos
     * 
     * @param resp
     *            objeto Respuesta donde estan los datos
     * @param impactos
     *            cadena con los impactos
     * @return Vector vector con los impactos
     */
    private Vector<Integer> vImpactosResp(Respuesta resp, String impactos) {
	Vector<Integer> vimpactos = new Vector<Integer>();
	String[] impacto = impactos.split(" ");
	for (int j = 0; j < impacto.length; j++) {
	    vimpactos.add(Integer.parseInt(impacto[j]));
	}
	return vimpactos;
    }

    /**
     * Método paraobtener los impactos de la identificación de riesgos
     * 
     * @param iden
     *            vector con los riesgos identificados
     * @return Vector vector de enteros con los impactos
     */
    private Vector<Integer> vImpactosIden(Vector<Iden> iden) {
	Vector<Integer> vimpactos = new Vector<Integer>();
	for (int i = 0; i < iden.size(); i++) {
	    String impactos = iden.get(i).getImpacto();
	    String[] impacto = impactos.split(" ");
	    for (int j = 0; j < impacto.length; j++) {
		vimpactos.add(Integer.parseInt(impacto[j]));
	    }
	}
	return vimpactos;
    }

    /*
     * Getter y Setter del Request
     */
    public Integer getOption() {
	return option;
    }

    public void setOption(Integer option) {
	this.option = option;
    }
    /*
     * Fin Getter y Setter del Request
     */
}
