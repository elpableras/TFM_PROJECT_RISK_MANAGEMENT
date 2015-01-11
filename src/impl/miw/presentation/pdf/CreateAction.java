package impl.miw.presentation.pdf;

import impl.miw.business.pdf.CreatePDF;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itextpdf.text.DocumentException;
import com.miw.business.InfoService;
import com.miw.business.ProjectService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Iden;
import com.miw.model.Info;
import com.miw.model.Project;
import com.miw.model.Respuesta;
import com.miw.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de la capa de presentación para la acción de creación de PDF de los
 * planes, identificación/analisis y respuesta de riesgos que se encuentran
 * almacenados en la base de datos para crear con ellos los informes, extiende
 * de ActionSupport que nos proporciona una implementación por defecto para las
 * acciones más comunes con implementación de tres interfaces “aware” para alojar
 * objetos que puedan estar a disposición en otras partes de la aplicación.
 * 
 * @author Pablo
 * 
 */
public class CreateAction extends ActionSupport implements ApplicationAware,
	ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> application;
    private HttpServletRequest request;
    private HttpServletResponse response;    
    private ProjectService projectService;
    private InfoService infoService;
    private LogService log;
    private Integer option;
    private Integer num;
    private String title;

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
	log.debug("Invocado el getInfoService de Create");
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
     * Getter para la obtención de la interfaz para los servicios aplicados al
     * modelo de proyecto
     * 
     * @return ProjectService Objeto que hace referencia a la interfaz
     */
    public ProjectService getProjectService() {
	log.debug("Invocado el getProjectService de Create");
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
	log.debug("Invocado el setProjectService  de Create");
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
	log.debug("Invocado el setServletRequest de Create");
	this.request = httpServletRequest;
    }
    
    /**
     * Setter que establece la respuesta al objeto HTTP en las clases de la
     * aplicación
     * 
     * @param httpServletResponse
     *            respuesta al Servlet
     */
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
	log.debug("Invocado el setServletResponse de Create");
	this.response = httpServletResponse;
    }

    /**
     * Método que implementa la ejecución del action de Struts con los datos del
     * request
     * 
     * @return String cadena que será procesada en struts.xml
     */
    @SuppressWarnings("unchecked")
    @Override
    public String execute() {
	log.debug("Procesando el execute de Create");

	User u = (User) request.getSession().getAttribute("usuario");
	Info info = (Info) application.get("info");

	switch (option) {
	case 1:
	    if (info == null) {
		try {
		    Vector<Double> version = infoService.getVersion(u.getId(),
			    u.getIdProyecto());
		    info = infoService.getInfo(u.getId(), u.getIdProyecto(),
			    version.get(0));
		} catch (Exception e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		}
	    }
	    try {
		Project project = projectService.getProject(info.getIdP());
		try {
		    CreatePDF create = new CreatePDF(info, u, project, title, response);
		    create.createPdf();
		    this.addActionMessage(getText("pdf.correcto"));
		} catch (DocumentException e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		} catch (IOException e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		}

	    } catch (Exception e) {
		addActionError(getText("error"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	    break;
	case 2:
	    Vector<Iden> iden = (Vector<Iden>) application.get("iden");
	    if (iden.size() == 0) {
		try {
		    iden = infoService.getIden(u.getId(), u.getIdProyecto());
		} catch (Exception e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		}
	    }
	    try {
		Project project = projectService.getProject(iden.get(0)
			.getIdP());

		try {
		    CreatePDF create = new CreatePDF(iden, u, project, title,
			    info.getImpacto(), info.getProbabilidad(),
			    info.getCorte(), response);
		    create.createPdf();
		    this.addActionMessage(getText("pdf.correcto"));
		} catch (DocumentException e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		} catch (IOException e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		}

	    } catch (Exception e) {
		addActionError(getText("error"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	    break;
	case 3:
	    Respuesta r = null;
	    try {
		r = infoService.getResp(u.getId(), u.getIdProyecto(), num);
	    } catch (Exception e) {
		addActionError(getText("error"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	    try {
		Project project = projectService.getProject(r.getIdP());
		try {
		    CreatePDF create = new CreatePDF(r, u, project, title,
			    info.getVersion(), info.getImpacto(),
			    info.getProbabilidad(), info.getCorte(), response);
		    create.createPdf();
		    this.addActionMessage(getText("pdf.correcto"));
		} catch (DocumentException e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		} catch (IOException e) {
		    addActionError(getText("error"));
		    log.error(e.getClass() + " " + e.getMessage());
		}

	    } catch (Exception e) {
		addActionError(getText("error"));
		log.error(e.getClass() + " " + e.getMessage());
	    }
	    break;
	default:
	    break;
	}
	request.getSession().setAttribute("play", 4);
	request.getSession().setAttribute("usuario", u);
	return (SUCCESS);
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

    public Integer getNum() {
	return num;
    }

    public void setNum(Integer num) {
	this.num = num;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    /*
     * Fin Getter y Setter del Request
     */
}
