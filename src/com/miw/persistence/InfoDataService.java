package com.miw.persistence;

import java.util.Vector;

import com.miw.model.Analisis;
import com.miw.model.Iden;
import com.miw.model.Info;

/**
 * Facade. Interfaz entre la capa de Negocio y Persistencia para datos de
 * riesgos
 * 
 * @author Pablo
 * 
 */
public interface InfoDataService {

    public void setUpdateInfo(Info data) throws Exception;

    public Info getInfo(Long id, Long idP, Double version) throws Exception;

    public void setIden(Vector<Iden> vIden) throws Exception;

    public Vector<Iden> getIden(Long id, Long idP) throws Exception;

    public void setAnalisis(Analisis anal) throws Exception;

    public Analisis getAnal(Long id, Long idP, Integer op) throws Exception;

    public Vector<Double> getVersion(Long id, Long idP) throws Exception;

    public void setInfo(Long id, Long idP, Double v, Info data)
	    throws Exception;

    public Vector<Double> getVersionIden(Long id, Long idP) throws Exception;

}
