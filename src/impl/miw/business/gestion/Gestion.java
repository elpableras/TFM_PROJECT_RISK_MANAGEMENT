package impl.miw.business.gestion;

import java.util.Vector;

import com.miw.business.InfoService;
import com.miw.infrastructure.log.LogService;
import com.miw.model.Iden;
import com.miw.model.Info;
import com.miw.model.Respuesta;
import com.miw.persistence.InfoDataService;

/**
 * Clase que implementa las operaciones para la capa de Negocio de la
 * información de los riesgos
 * 
 * @author Pablo
 * 
 */
public class Gestion implements InfoService {

    private InfoDataService infoDataService;
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
     * Getter de la interfaz entre el negocio y la persistencia de datos de los
     * riesgos
     * 
     * @return InfoDataService interfaz entre el negocio y la persistencia
     */
    public InfoDataService getInfoDataService() {
	return infoDataService;
    }

    /**
     * Setter de la interfaz entre el negocio y la persistencia de datos de los
     * riesgos
     * 
     * @param infoDataService
     *            interfaz entre el negocio y la persistencia
     */
    public void setInfoDataService(InfoDataService infoDataService) {
	this.infoDataService = infoDataService;
    }

    /**
     * Setter de la interfaz entre presentación y negocio para la actualización
     * de datos de los riesgos
     * 
     * @param data
     *            información del riesgo
     * @throws Exception
     */
    @Override
    public void setUpdateInfo(Info data) throws Exception {
	log.debug("Entrando en setUpdateInfo");
	infoDataService.setUpdateInfo(data);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para la obtención de
     * datos de los riesgos
     * 
     * @param id
     *            identificador usuario
     * @param idP
     *            identificador proyecto
     * @param version
     *            versión
     * @return Info información del riesgo
     * @throws Exception
     */
    @Override
    public Info getInfo(Long id, Long idP, Double version) throws Exception {
	log.debug("Entrando en getInfo");
	return infoDataService.getInfo(id, idP, version);
    }

    /**
     * Setter de la interfaz entre presentación y negocio para establecer los
     * datos de identificación de riesgos
     * 
     * @param vIden
     *            vector con información de los riesgos
     * @throws Exception
     */
    @Override
    public void setIden(Vector<Iden> vIden) throws Exception {
	log.debug("Entrando en setIden");
	infoDataService.setIden(vIden);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * de identificación de riesgos
     * 
     * @param id
     *            identificador usuario
     * @param idP
     *            identificador proyecto
     * @return Vector vector con información de los riesgos
     * @throws Exception
     */
    @Override
    public Vector<Iden> getIden(Long id, Long idP) throws Exception {
	log.debug("Entrando en getIden");
	return infoDataService.getIden(id, idP);
    }

    /**
     * Setter de la interfaz entre presentación y negocio para establecer los
     * datos de respuesta de riesgos
     * 
     * @param resp
     *            datos con la Respuesta
     * @throws Exception
     */
    @Override
    public void setRespuesta(Respuesta resp) throws Exception {
	log.debug("Entrando en setRespuesta");
	infoDataService.setRespuesta(resp);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * de la respuesta de riesgos
     * 
     * @param id
     *            identificador usuario
     * @param idP
     *            identificador proyecto
     * @param op
     *            opción
     * @return Respuesta la respuesta con información de los riesgos
     * @throws Exception
     */
    @Override
    public Respuesta getResp(Long id, Long idP, Integer op) throws Exception {
	log.debug("Entrando en getResp");
	return infoDataService.getResp(id, idP, op);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * de la versión de los planes de riesgo
     * 
     * @param id
     *            identificador usuario
     * @param idP
     *            identificador proyecto
     * @return Vector vector con los números de las versiones
     * @throws Exception
     */
    @Override
    public Vector<Double> getVersion(Long id, Long idP) throws Exception {
	log.debug("Entrando en getVersion");
	return infoDataService.getVersion(id, idP);
    }

    /**
     * Setter de la interfaz entre presentación y negocio para establecer la
     * primera versión de un plan de riesgo
     * 
     * @param id
     *            identificador usuario
     * @param idP
     *            identificador proyecto
     * @param v
     *            versión
     * @throws Exception
     */
    @Override
    public void setInfo(Long id, Long idP, Double v, Info info)
	    throws Exception {
	log.debug("Entrando en setInfo");
	infoDataService.setInfo(id, idP, v, info);
    }

    /**
     * Getter de la interfaz entre presentación y negocio para obtener los datos
     * de la versión de la identificación de riesgos
     * 
     * @param idU
     *            identificador usuario
     * @param idP
     *            identificador proyecto
     * @return Vector vector con los números de las versiones
     * @throws Exception
     */
    @Override
    public Vector<Double> getVersionIden(Long idU, Long idP) throws Exception {
	log.debug("Entrando en getVersionIden");
	return infoDataService.getVersionIden(idU, idP);
    }

}
