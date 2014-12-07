package impl.miw.presentation.resp;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.miw.business.InfoService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Respuesta;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de almacenamiento de la
 * información de la respuesta de los riesgos estudiados escritos en la
 * aplicación, extiende de ActionSupport que nos proporciona una implementación
 * por defecto para las acciones más comunes con implementación de una interfaz
 * “aware” para alojar objetos que puedan estar a disposición en otras partes de
 * la aplicación.
 * 
 * @author Pablo
 * 
 */
public class SaveAction extends ActionSupport implements ServletRequestAware {

    private static final long serialVersionUID = 582068251731617787L;

    // BBDD
    private InfoService infoService;
    private LogService log;
    private HttpServletRequest request;
    private Long idP;
    private Long idU;

    // Respuesta
    private Integer option;
    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String status;
    private String causas;

    private Integer probabilidad;
    private String impacto;
    private String valor;
    private String response;

    private String fechaRevision;
    private Integer probabilidadRevisada;
    private String impactoRevisado;
    private String valorRevisado;
    private String responseRevisado;

    private String derivados;
    private String residual;
    private String contingencia;
    private String presupuesto;
    private String planificacion;
    private String comentarios;
    private String monitorizacion;

    // Numero de Objetivos
    private Integer numeroI;

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
	log.debug("Invocado el getInfoService de Save de respi");
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
	log.debug("Invocado el setServletRequest de Save de respi");
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
	log.debug("Procesando el execute de Save de respi");

	Respuesta resp = new Respuesta();

	resp.setIdU(idU);
	resp.setIdP(idP);
	resp.setOption(option);
	resp.setId(id);
	resp.setNombre(nombre);
	resp.setDescripcion(descripcion);
	resp.setCategoria(categoria);
	resp.setStatus(status);
	resp.setCausas(causas);
	resp.setProbabilidad(probabilidad);

	String vImpactos = "";
	String[] impa = impacto.split(", ");
	for (int j = 0; j < numeroI; j++) {
	    vImpactos += String.valueOf(impa[j]) + " ";
	}

	resp.setImpacto(vImpactos);
	resp.setValor(Double.valueOf(valor));
	resp.setResponse(response);

	if (fechaRevision != null && fechaRevision.compareTo("") != 0) {
	    String[] f = fechaRevision.split("-");
	    String fecha = f[2] + "-" + f[1] + "-" + f[0];
	    resp.setFechaRevisada(fecha);
	}
	resp.setProbabilidadRevisada(probabilidadRevisada);

	String vImpactosRevisados = "";
	String[] impaRevisado = impactoRevisado.split(", ");
	for (int j = 0; j < numeroI; j++) {
	    vImpactosRevisados += String.valueOf(impaRevisado[j]) + " ";
	}

	resp.setImpactoRevisado(vImpactosRevisados);
	if (valorRevisado.compareTo("") == 0) {
	    valorRevisado = "0.0";
	}
	resp.setValorRevisado(Double.valueOf(valorRevisado));
	resp.setResponseRevisado(responseRevisado);

	resp.setDerivado(derivados);
	resp.setResidual(residual);
	resp.setContingencia(contingencia);
	resp.setPresupuesto(presupuesto);
	resp.setPlanificacion(planificacion);
	resp.setComentarios(comentarios);
	resp.setMonitorizacion(monitorizacion);

	String indicador = "";
	String evaluacion = "";
	Iterator<Entry<String, String[]>> it = request.getParameterMap()
		.entrySet().iterator();
	while (it.hasNext()) {
	    Entry<String, String[]> aux = it.next();
	    if (aux.getKey().contains("indicador")) {
		for (int i = 0; i < aux.getValue().length; i++) {
		    indicador += aux.getValue()[i] + "@";
		}
	    }
	    if (aux.getKey().contains("evaluacion")) {
		for (int i = 0; i < aux.getValue().length; i++) {
		    evaluacion += aux.getValue()[i] + "@";
		}
	    }
	}

	resp.setIndicador(indicador);
	resp.setEvaluacion(evaluacion);

	try {
	    infoService.setRespuesta(resp);
	    this.addActionMessage(getText("respi.correct"));
	    request.getSession().setAttribute("play", 3);
	} catch (Exception e) {
	    addActionError(getText("respi.error"));
	    log.error(e.getClass() + " " + e.getMessage());
	}

	return (SUCCESS);
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

    public Integer getOption() {
	return option;
    }

    public void setOption(Integer option) {
	this.option = option;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
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

    public String getCategoria() {
	return categoria;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getCausas() {
	return causas;
    }

    public void setCausas(String causas) {
	this.causas = causas;
    }

    public Integer getProbabilidad() {
	return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
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

    public String getFechaRevision() {
	return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
	this.fechaRevision = fechaRevision;
    }

    public Integer getProbabilidadRevisada() {
	return probabilidadRevisada;
    }

    public void setProbabilidadRevisada(Integer probabilidadRevisada) {
	this.probabilidadRevisada = probabilidadRevisada;
    }

    public String getImpactoRevisado() {
	return impactoRevisado;
    }

    public void setImpactoRevisado(String impactoRevisado) {
	this.impactoRevisado = impactoRevisado;
    }

    public String getValorRevisado() {
	return valorRevisado;
    }

    public void setValorRevisado(String valorRevisado) {
	this.valorRevisado = valorRevisado;
    }

    public String getResponseRevisado() {
	return responseRevisado;
    }

    public void setResponseRevisado(String responseRevisado) {
	this.responseRevisado = responseRevisado;
    }

    public String getDerivados() {
	return derivados;
    }

    public void setDerivados(String derivados) {
	this.derivados = derivados;
    }

    public String getResidual() {
	return residual;
    }

    public void setResidual(String residual) {
	this.residual = residual;
    }

    public String getContingencia() {
	return contingencia;
    }

    public void setContingencia(String contingencia) {
	this.contingencia = contingencia;
    }

    public String getPresupuesto() {
	return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
	this.presupuesto = presupuesto;
    }

    public String getPlanificacion() {
	return planificacion;
    }

    public void setPlanificacion(String planificacion) {
	this.planificacion = planificacion;
    }

    public String getComentarios() {
	return comentarios;
    }

    public void setComentarios(String comentarios) {
	this.comentarios = comentarios;
    }

    public String getMonitorizacion() {
	return monitorizacion;
    }

    public void setMonitorizacion(String monitorizacion) {
	this.monitorizacion = monitorizacion;
    }

    public String getResponse() {
	return response;
    }

    public void setResponse(String response) {
	this.response = response;
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