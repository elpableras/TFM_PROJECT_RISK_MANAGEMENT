package com.miw.infrastructure.log;

/**
 * Capa de Infraestructura. Interfaz para el Servicio de Log
 * 
 * @author Pablo
 * 
 */
public interface LogService {
    public final int DEBUG = 0;
    public final int WARN = 1;
    public final int ERROR = 3;
    public final int FATAL_ERROR = 4;

    public void warn(String message);

    public void error(String message);

    public void debug(String message);

    public void fatalError(String message);
}
