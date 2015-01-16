package impl.miw.presentation.iden;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.InfoService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Cambio;
import com.miw.model.Iden;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de almacenamiento de la
 * información de la identificación de riesgos para el estudio escritos en la
 * aplicación, extiende de ActionSupport que nos proporciona una implementación
 * por defecto para las acciones más comunes con implementación de dos
 * interfaces “aware” para alojar objetos que puedan estar a disposición en
 * otras partes de la aplicación.
 * 
 * @author Pablo
 * 
 */
public class SaveAction extends ActionSupport implements ServletRequestAware,
	ApplicationAware {

    private static final long serialVersionUID = 582068251731617787L;

    // BBDD
    private InfoService infoService;
    private Map<String, Object> application;
    private LogService log;
    private HttpServletRequest request;
    private Long idP;
    private Long idU;

    // Iden
    private String id;
    private String nombre;
    private String descripcion;
    private String responsable;
    private String probabilidad;
    private String impacto;
    private String valor;
    private String response;
    private String notes;
    // Numero de Objetivos
    private Integer numeroI;

    private String[] descs;

    private String[] nombs;

    private String[] resps;

    private String[] probs;

    private String[] impas;

    private String[] valos;

    private String[] respos;

    private String[] notes2;

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
	log.debug("Invocado el getInfoService de Save de Iden");
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
     * Setter que establece la solicitud al objeto HTTP en las clases de la
     * aplicación
     * 
     * @param httpServletRequest
     *            petición del Servlet
     */
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
	log.debug("Invocado el setServletRequest de Save de Iden");
	this.request = httpServletRequest;
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
	log.debug("Procesando el execute de Save de Iden");

	Vector<Iden> vIden;
	Vector<Double> versiones = null;
	Double v = 1.0000;
	try {
	    versiones = infoService.getVersionIden(idU, idP);
	} catch (Exception e) {
	    addActionError(getText("iden.error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}

	String[] numI = id.split(", ");
	descs = null;
	nombs = null;
	resps = null;
	probs = null;
	impas = null;
	valos = null;
	respos = null;
	notes2 = null;

	int riesgos = numI.length;
	if (riesgos > 1) {
	    descs = descripcion.split(", ");
	    nombs = nombre.split(", ");
	    resps = responsable.split(", ");
	    probs = probabilidad.split(", ");
	    impas = impacto.split(", ");
	    valos = valor.split(", ");
	    respos = response.split(", ");
	    notes2 = notes.split(", ");
	} else {
	    descs[0] = descripcion;
	    nombs[0] = nombre;
	    resps[0] = responsable;
	    probs[0] = probabilidad;
	    impas[0] = impacto;
	    valos[0] = valor;
	    respos[0] = response;
	    notes2[0] = notes;
	}

	if (versiones.size() > 0) {
	    v = versiones.get(0) + 0.0001;
	}

	Iden iden;
	vIden = new Vector<Iden>();
	int k = 0;
	for (int i = 0; i < riesgos; i++) {
	    iden = new Iden();
	    iden.setIdU(idU);
	    iden.setIdP(idP);
	    iden.setId(Integer.parseInt(numI[i]));
	    iden.setNombre(nombs[i]);
	    iden.setDescripcion(descs[i]);
	    iden.setResponsable(resps[i]);
	    iden.setProbabilidad(Integer.parseInt(probs[i]));
	    String vImpactos = "";
	    for (int j = 0; j < numeroI; j++) {
		vImpactos += String.valueOf(impas[k++]) + " ";
	    }
	    iden.setImpacto(vImpactos);
	    iden.setValor(Double.valueOf(valos[i]));
	    iden.setResponse(respos[i]);
	    if (notes2.length > i) {
		iden.setNotes(notes2[i]);
	    }
	    iden.setVersion(v);
	    addCambio(v, iden);
	    vIden.add(iden);
	}

	try {
	    infoService.setIden(vIden);
	    this.addActionMessage(getText("iden.correct"));
	    saveVariables();
	} catch (Exception e) {
	    addActionError(getText("iden.error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}

	return (SUCCESS);
    }

    /**
     * Método para almacenar los cambios de las versiones
     * 
     * @param cambio
     *            objeto cambio
     * @param v
     *            double de la versión
     * @param vc
     *            vector de cambios
     * @param iden
     *            objeto de la identificación de riesgos
     */
    private void addCambio(Double v, Iden iden) {
	Cambio cambio = new Cambio();
	cambio.setPlan(iden.getId() + "-" + iden.getNombre());
	cambio.setVersion(v);
	Vector<Cambio> vc = new Vector<Cambio>();
	vc.add(cambio);
	iden.setCambio(vc);
    }

    /**
     * Método para alojar objetos que puedan estar a disposición en otras partes
     * de la aplicación.
     */
    private void saveVariables() {
	Double corte = (Double) application.get("corte");
	Vector<Iden> iden = null;
	try {
	    iden = infoService.getIden(idU, idP);
	} catch (Exception e) {
	    addActionError(getText("iden.error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
	Vector<Iden> study = new Vector<Iden>();
	for (int i = 0; i < iden.size(); i++) {
	    if (iden.get(i).getValor() >= corte) {
		study.add(iden.get(i));
	    }
	}
	application.put("study", study);
	request.getSession().setAttribute("play", 3);
    }

    /*
     * Getter y Setter del Request
     */
    public Long getIdP() {
	return idP;
    }

    public void setIdP(Long idP) {
	this.idP = idP;
    }

    public Long getIdU() {
	return idU;
    }

    public void setIdU(Long idU) {
	this.idU = idU;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getResponsable() {
	return responsable;
    }

    public void setResponsable(String responsable) {
	this.responsable = responsable;
    }

    public String getProbabilidad() {
	return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
	this.probabilidad = probabilidad;
    }

    public String getImpacto() {
	return impacto;
    }

    public void setImpacto(String impacto) {
	this.impacto = impacto;
    }

    public String getValor() {
	return valor;
    }

    public void setValor(String valor) {
	this.valor = valor;
    }

    public String getResponse() {
	return response;
    }

    public void setResponse(String response) {
	this.response = response;
    }

    public String getNotes() {
	return notes;
    }

    public void setNotes(String notes) {
	this.notes = notes;
    }

    public Integer getNumeroI() {
	return numeroI;
    }

    public void setNumeroI(Integer numeroI) {
	this.numeroI = numeroI;
    }
    /*
     * Fin Getter y Setter del Request
     */
}