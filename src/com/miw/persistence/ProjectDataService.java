package com.miw.persistence;

import java.util.Vector;

import com.miw.model.Project;
import com.miw.model.User;

/**
 * Facade. Interfaz entre la capa de Negocio y Persistencia para datos de
 * proyectos
 * 
 * @author Pablo
 * 
 */
public interface ProjectDataService {
    public Vector<Project> getProjects() throws Exception;

    public String setProject(Project project) throws Exception;

    public Project getProject(User user) throws Exception;

    public Boolean deleteProject(Long idProject) throws Exception;

    public String setUpdateProject(Project updateProject) throws Exception;

    public String seekProject(Long idProjectUserUpdate) throws Exception;

    public Project getProject(Long idP) throws Exception;

    public void updateStep(Long idP, Integer step) throws Exception;

}
