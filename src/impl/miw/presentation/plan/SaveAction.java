package impl.miw.presentation.plan;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.InfoService;
import com.miw.business.ProjectService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Cambio;
import com.miw.model.Impacto;
import com.miw.model.Info;
import com.miw.model.Probabilidad;
import com.miw.model.Project;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de almacenamiento de la
 * información de los planes de riesgo escritos en la aplicación, extiende de
 * ActionSupport que nos proporciona una implementación por defecto para las
 * acciones más comunes con implementación de dos interfaces “aware” para alojar
 * objetos que puedan estar a disposición en otras partes de la aplicación.
 * 
 * @author Pablo
 * 
 */
public class SaveAction extends ActionSupport implements ServletRequestAware,
	ApplicationAware {

    private static final long serialVersionUID = -6346120559503444781L;
    private HttpServletRequest request;
    private Map<String, Object> application;

    // BBDD
    private InfoService infoService;
    private ProjectService projectService;
    private LogService log;

    // Datos plan general
    private String plan;
    private Long idP;
    private Long id;
    private Long codPlan;
    private String version;

    private String date;
    private String name;
    private int numFilas;
    private String matrixCorte;
    private String rango;

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
	log.debug("Invocado el getInfoService de Save");
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
	// log.debug("Invocado el setInfoService de Save");
    }

    /**
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de proyecto
     * 
     * @return ProjectService Objeto que hace referencia a la interfaz
     */
    public ProjectService getProjectService() {
	log.debug("Invocado el getProjectService de Save");
	return projectService;
    }

    /**
     * Setter para establecer la interfaz para los servicios aplicados al modelo
     * de proyecto
     * 
     * @param projectService
     *            objecto interfaz
     */
    public void setProjectService(ProjectService projectService) {
	log.debug("Invocado el setProjectService  de Save");
	this.projectService = projectService;
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
	log.debug("Invocado el setServletRequest de Save");
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
	log.debug("Procesando el execute de Save");

	Double v = 0.0;
	String nombre = "";
	Info data = new Info();
	Vector<Cambio> vc = new Vector<Cambio>();
	Cambio cambio = new Cambio();
	data.setIdP(idP);
	data.setIdU(id);
	data.setFecha(getDate());
	data.setNombre(getName());

	try {
	    Project project = projectService.getProject(idP);
	    nombre = project.getNombre();
	    v = Double.valueOf(version) + 0.0001;
	} catch (Exception e1) {
	    addActionError(getText("error"));
	    log.error(e1.getClass() + " " + e1.getMessage());
	}
	data.setVersion(v);
	data.setTitulo(nombre);

	switch ((int) (long) getCodPlan()) {
	case 2:
	    cambio.setPlan("Metodología");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setMetodologia(getPlan());
	    update(data, 2);
	    break;
	case 3:
	    cambio.setPlan("Herramientas y Tecnologías");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setHtecno(getPlan());
	    update(data, 3);
	    break;
	case 4:
	    cambio.setPlan("Roles y Responsabilidades");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setRoles(getPlan());
	    update(data, 4);
	    break;
	case 5:
	    cambio.setPlan("Presupuesto");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setPresu(getPlan());
	    update(data, 5);
	    break;
	case 6:
	    cambio.setPlan("Calendario");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setCalendar(getPlan());
	    update(data, 6);
	    break;
	case 7:
	    cambio.setPlan("Categoría de Riesgos");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setRiesgo(getPlan());
	    update(data, 7);
	    break;
	case 8:
	    Vector<Probabilidad> vp = new Vector<Probabilidad>();
	    Iterator<Entry<String, String[]>> it = request.getParameterMap()
		    .entrySet().iterator();
	    Entry<String, String[]> porcentaje = null;
	    Entry<String, String[]> probabilidad = null;
	    Entry<String, String[]> descripcion = null;

	    while (it.hasNext()) {
		Entry<String, String[]> aux = it.next();
		if (aux.getKey().compareToIgnoreCase("porcentaje") == 0) {
		    porcentaje = aux;
		}
		if (aux.getKey().compareToIgnoreCase("probabilidad") == 0) {
		    probabilidad = aux;
		}
		if (aux.getKey().compareToIgnoreCase("descripcion") == 0) {
		    descripcion = aux;
		}
	    }
	    for (int j = 0; j < porcentaje.getValue().length; j++) {
		Probabilidad p = new Probabilidad();
		p.setPorcentaje(Integer.parseInt(porcentaje.getValue()[j]));
		p.setProbabilidad(probabilidad.getValue()[j]);
		p.setDescripcion(descripcion.getValue()[j]);
		vp.add(p);
	    }
	    cambio.setPlan("Definiciones de Probabilidad");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setProbabilidad(vp);
	    update(data, 8);
	    break;
	case 9:
	    Vector<Impacto> vi = new Vector<Impacto>();
	    Vector<Probabilidad> vpi = null;
	    Iterator<Entry<String, String[]>> it2 = request.getParameterMap()
		    .entrySet().iterator();
	    Entry<String, String[]> objetivo = null;
	    Entry<String, String[]> porcentajeI = null;
	    Entry<String, String[]> probabilidadI = null;
	    Entry<String, String[]> descripcionI = null;

	    while (it2.hasNext()) {
		Entry<String, String[]> aux = it2.next();
		if (aux.getKey().compareToIgnoreCase("objetivo") == 0) {
		    objetivo = aux;
		}
		if (aux.getKey().compareToIgnoreCase("porcentaje") == 0) {
		    porcentajeI = aux;
		}
		if (aux.getKey().compareToIgnoreCase("probabilidad") == 0) {
		    probabilidadI = aux;
		}
		if (aux.getKey().compareToIgnoreCase("descripcion") == 0) {
		    descripcionI = aux;
		}
	    }
	    int numDesc = 0;
	    for (int j = 0; j < objetivo.getValue().length; j++) {
		Impacto i = new Impacto();
		i.setObjetivo(objetivo.getValue()[j]);
		vpi = new Vector<Probabilidad>();
		for (int k = 0; k < porcentajeI.getValue().length; k++) {
		    Probabilidad p = new Probabilidad();
		    p.setPorcentaje(Integer.parseInt(porcentajeI.getValue()[k]));
		    p.setProbabilidad(probabilidadI.getValue()[k]);
		    p.setDescripcion(descripcionI.getValue()[numDesc]);
		    numDesc++;
		    vpi.add(p);
		}
		i.setProbabilidad(vpi);
		vi.add(i);
	    }
	    cambio.setPlan("Definiciones de Impacto por Objetivo");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setImpacto(vi);
	    update(data, 9);
	    break;
	case 10:
	    cambio.setPlan("Índice de Corte y Rango");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setCorte(Double.valueOf(matrixCorte));
	    data.setRango(rango);
	    update(data, 10);
	    break;
	case 11:
	    cambio.setPlan("Plan de Contingencia");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setContingencia(getPlan());
	    update(data, 11);
	    break;
	case 12:
	    cambio.setPlan("Formato de la Documentación");
	    cambio.setVersion(v);
	    addCambio(data, vc, cambio);
	    data.setFormato(getPlan());
	    update(data, 12);
	    request.getSession().setAttribute("plan", 2);
	    break;
	default:
	    break;
	}
	application.put("version", v);
	return (SUCCESS);
    }

    /**
     * Método para añadir el cambio en la versión
     * 
     * @param data
     *            información del cambion en el plan de riesgos
     * @param vc
     *            vector con los cambios
     * @param cambio
     *            objeto cambio
     */
    private void addCambio(Info data, Vector<Cambio> vc, Cambio cambio) {
	vc.add(cambio);
	data.setCambio(vc);
    }

    /**
     * Método para actualizar los diferentes puntos del plan que es llamado
     * desde la ejecución
     * 
     * @param data
     *            información de los datos a actualizar en los planes de riesgo
     * @param plan
     *            entero con el tipo de plan
     */
    private void update(Info data, Integer plan) {
	try {
	    infoService.setUpdateInfo(data);
	    this.addActionMessage(getText("plan.correct.update"));
	    request.getSession().setAttribute("saveplan", plan);
	} catch (Exception e) {
	    addActionError(getText("plan.error.update"));
	    log.error(e.getClass() + " " + e.getMessage());
	}
    }

    /*
     * Getter y Setter del Request
     */
    public String getPlan() {
	return plan;
    }

    public void setPlan(String plan) {
	this.plan = plan;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getIdP() {
	return idP;
    }

    public void setIdP(Long idP) {
	this.idP = idP;
    }

    public Long getCodPlan() {
	return codPlan;
    }

    public void setCodPlan(Long codPlan) {
	this.codPlan = codPlan;
    }

    public int getNumFilas() {
	return numFilas;
    }

    public void setNumFilas(int numFilas) {
	this.numFilas = numFilas;
    }

    public String getCorte() {
	return matrixCorte;
    }

    public void setCorte(String matrixCorte) {
	this.matrixCorte = matrixCorte;
    }

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public String getRango() {
	return rango;
    }

    public void setRango(String rango) {
	this.rango = rango;
    }

    /*
     * Fin Getter y Setter del Request
     */
}