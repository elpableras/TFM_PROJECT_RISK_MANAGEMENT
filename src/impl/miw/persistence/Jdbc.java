package impl.miw.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.opensymphony.xwork2.config.Configuration;

/**
 * Clase para la configuración de la conexión a MYSQL
 * 
 * @author Pablo
 * 
 */
public class Jdbc {

    /**
     * Getter que devuelve la conexión
     * 
     * @return Connection conexión
     */
    public static Connection getConnection() throws IOException {

	Properties dbProps = new Properties();
	try {
	    dbProps.load(Configuration.class.getClassLoader()
		    .getResourceAsStream("db.properties"));
	} catch (Exception e) {
	    throw new IOException("No se puede leer el archivo properties");
	}

	loadDriver(dbProps);

	try {
	    return DriverManager.getConnection(
		    dbProps.getProperty("jdbc.url.localhost"),
		    dbProps.getProperty("jdbc.username"),
		    dbProps.getProperty("jdbc.password"));

	} catch (SQLException e) {
	    throw new RuntimeException("No se puede abrir conexion a "
		    + dbProps.getProperty("jdbc.url.server"), e);
	}
    }

    /**
     * Método estático para caragr el driver
     */
    private static void loadDriver(Properties dbProps) {
	try {

	    Class.forName(dbProps.getProperty("jdbc.driverClass"));

	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("Driver SQL no encontrado", e);
	}
    }
}