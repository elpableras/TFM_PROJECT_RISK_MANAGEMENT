package com.miw.business;

import java.util.Vector;

import com.miw.model.Analisis;
import com.miw.model.Iden;
import com.miw.model.Info;

/**
 * Facade. Interfaz entre la capa de Presentación y Negocio para datos de
 * riesgos
 * 
 * @author Pablo
 * 
 */
public interface InfoService {

    public void setUpdateInfo(Info data) throws Exception;

    public Info getInfo(Long id, Long idP, Double version) throws Exception;

    public void setIden(Vector<Iden> vIden) throws Exception;

    public Vector<Iden> getIden(Long id, Long idP) throws Exception;

    public void setAnalisis(Analisis anal) throws Exception;

    public Analisis getAnal(Long id, Long idP, Integer option) throws Exception;

    public Vector<Double> getVersion(Long id, Long idP) throws Exception;

    public void setInfo(Long id, Long idP, Double v, Info info)
	    throws Exception;

    public Vector<Double> getVersionIden(Long idU, Long idP) throws Exception;

}
