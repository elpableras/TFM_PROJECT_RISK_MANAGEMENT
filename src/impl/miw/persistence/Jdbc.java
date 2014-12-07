package impl.miw.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para la configuración de la conexión a MYSQL
 * 
 * @author Pablo
 * 
 */
public class Jdbc {

    // Variables para configuración de la conexión a MySQL
    private static String SQL_DRIVER = "com.mysql.jdbc.Driver";
    // LOCAL
    private static String SQL_URL = "jdbc:mysql://localhost:3306/tfm";
    // private static String SQL_URL =
    // "jdbc:mysql://127.7.70.130:3306/masterproject";
    private static String SQL_USER = "root";
    private static String SQL_PASS = "tfm14";

    /**
     * Getter que devuelve la conexión
     * 
     * @return Connection conexión
     */
    public static Connection getConnection() {

	loadDriver();

	try {

	    return DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASS);

	} catch (SQLException e) {
	    throw new RuntimeException("No se puede abrir conexion a "
		    + SQL_URL, e);
	}
    }

    /**
     * Método estático para caragr el driver
     */
    private static void loadDriver() {
	try {

	    Class.forName(SQL_DRIVER);

	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("Driver SQL no encontrado", e);
	}
    }
}