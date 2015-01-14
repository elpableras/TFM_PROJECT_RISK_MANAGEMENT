package impl.miw.persistence.project;

import impl.miw.persistence.Jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.miw.model.Project;
import com.miw.model.User;
import com.miw.persistence.ProjectDataService;

/**
 * Clase que implementa las operaciones de acceso a la base de datos para la
 * entidad PROYECTO.
 * 
 * @author Pablo
 */
public class ProjectDAO implements ProjectDataService {

    /**
     * Getter para la obtención de los proyectos en la base de datos
     * 
     * @return Vector vector con los datos de los proyectos
     * @throws Exception
     */
    @Override
    public Vector<Project> getProjects() throws Exception {
	// Inicializamos el Vector de retorno.
	Vector<Project> resultado = new Vector<Project>();

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select p_id,name,manager,AES_DECRYPT(email,@pass) as email,fecha,paso,created,modify from projects order by fecha asc;");
	    rs = ps.executeQuery();

	    while (rs.next()) {
		// Completamos los datos del proyecto en la entidad
		Project p = new Project();
		p.setId(rs.getLong("p_id"));
		p.setNombre(rs.getString("name"));
		p.setManager(rs.getString("manager"));
		p.setEmail(rs.getString("email"));
		Date fecha = rs.getDate("fecha");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
		String f = sdf.format(fecha);
		p.setFecha(f);
		p.setPaso(rs.getInt("paso"));
		p.setCreate(rs.getString("created"));
		p.setModify(rs.getString("modify"));
		// La añadimos al Vector de resultado
		resultado.add(p);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// Retornamos el vector de resultado.
	return resultado;
    }

    /**
     * Setter para establecer los datos del proyecto
     * 
     * @param project
     *            datos del proyecto
     * @return String cadena para comprobación
     * @throws Exception
     */
    @Override
    public String setProject(Project project) throws Exception {
	// Inicializamos el String de retorno.
	String resultado = "";

	PreparedStatement ps = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("INSERT INTO projects (name,manager,email,fecha,paso,created) VALUES (?,?,AES_ENCRYPT(?,@pass),?,?,?)");

	    ps.setString(1, project.getNombre());
	    ps.setString(2, project.getManager());
	    ps.setString(3, project.getEmail());

	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	    java.util.Date date = sdf1.parse(project.getFecha());
	    java.sql.Date sqlDate = new Date(date.getTime());

	    ps.setDate(4, sqlDate);
	    ps.setInt(5, project.getPaso());
	    ps.setTimestamp(6, null);

	    ps.executeUpdate();

	    resultado = updateProjectInUsers(project.getEmail());

	} catch (Exception e) {
	    resultado = "Se ha producido un error en la inserción";
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// Retornamos el vector de resultado.
	return resultado;
    }

    /**
     * Setter para actualizar el nuevo proyecto del usuario
     * 
     * @param email
     *            correo que identifica al usuario
     * @return String cadena para el log
     * @throws Exception
     */
    private String updateProjectInUsers(String email) throws Exception {

	Long p_id = 0L;
	// Inicializamos el String de retorno.
	String resultado = "";

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select p_id from projects where email=AES_ENCRYPT(?,@pass);");

	    ps.setString(1, email);

	    rs = ps.executeQuery();

	    if (rs.next()) {
		p_id = rs.getLong("p_id");

		ps = con.prepareStatement("UPDATE users SET p_id = ? where email=AES_ENCRYPT(?,@pass);");

		ps.setLong(1, p_id);
		ps.setString(2, email);

		ps.executeUpdate();

		resultado = "Datos Guardados Correctamente";
	    } else {
		resultado = "Se ha producido un error";
	    }

	} catch (Exception e) {
	    resultado = "Se ha producido un error en la actualización";
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return resultado;
    }

    /**
     * Getter para la obtención de los proyectos
     * 
     * @param user
     *            usuario
     * @return Project proyecto del que el usuario está inscrito
     * @throws Exception
     */
    @Override
    public Project getProject(User user) throws Exception {
	// Inicializamos el Vector de retorno.
	Project project = new Project();

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select p_id,name, manager, AES_DECRYPT(email,@pass) as email, fecha, paso, created, modify from projects where email=AES_ENCRYPT(?,@pass);");

	    ps.setString(1, user.getEmail());
	    rs = ps.executeQuery();

	    while (rs.next()) {
		// Completamos los datos del proyecto en la entidad
		project.setId(rs.getLong("p_id"));
		project.setNombre(rs.getString("name"));
		project.setManager(rs.getString("manager"));
		project.setEmail(rs.getString("email"));
		Date fecha = rs.getDate("fecha");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
		String f = sdf.format(fecha);
		project.setFecha(f);
		project.setPaso(rs.getInt("paso"));
		project.setCreate(rs.getString("created"));
		project.setModify(rs.getString("modify"));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// Retornamos el vector de resultado.
	return project;
    }

    /**
     * Método para elimianr proyectos
     * 
     * @param idProject
     *            identificador del proyecto
     * @return Boolean booleano true si se ha borrado sino false
     * @throws Exception
     */
    @Override
    public Boolean deleteProject(Long idProject) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("delete from projects where p_id=?;");

	    ps.setLong(1, idProject);
	    ps.executeUpdate();

	    ps = con.prepareStatement("UPDATE users SET p_id=? WHERE p_id=?");
	    ps.setString(1, null);
	    ps.setLong(2, idProject);
	    ps.executeUpdate();

	    return true;

	} catch (Exception e) {
	    return false;
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Setter para establecer los datos de actualización del proyecto
     * 
     * @param updateProject
     *            datos del proyecto
     * @return String cadena para el log
     * @throws Exception
     */
    @Override
    public String setUpdateProject(Project updateProject) throws Exception {
	String resultado = "";
	PreparedStatement ps = null;
	Connection con = null;

	try {

	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("UPDATE projects SET name=?, fecha=?, paso=? WHERE p_id=?");

	    ps.setString(1, updateProject.getNombre());	
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	    java.util.Date date = sdf1.parse(updateProject.getFecha());
	    java.sql.Date sqlDate = new Date(date.getTime());
	    ps.setDate(2, sqlDate);
	    ps.setInt(3, updateProject.getPaso());

	    ps.setLong(4, updateProject.getId());
	    ps.executeUpdate();

	    resultado = "Actualizado";

	} catch (Exception e) {
	    resultado = "No Actualizado";
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return resultado;
    }

    /**
     * Método para buscar proyectos mediante su id
     * 
     * @param idProjectUserUpdate
     *            identificador del proyecto del usuario
     * @return String cadena para comprobación
     * @throws Exception
     */
    @Override
    public String seekProject(Long idProjectUserUpdate) throws Exception {
	// Inicializamos el Vector de retorno.
	String resultado = " ";
	PreparedStatement ps = null;

	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select AES_DECRYPT(email,@pass) as email from projects where p_id=?");

	    ps.setLong(1, idProjectUserUpdate);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		resultado = rs.getString("email");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// Retornamos resultado.
	return resultado;
    }

    /**
     * Getter para la obtención del proyecto
     * 
     * @param idP
     *            identificador del proyecto
     * @return Project proyecto buscado
     * @throws Exception
     */
    @Override
    public Project getProject(Long idP) throws Exception {
	// Inicializamos el Vector de retorno.
	Project project = new Project();

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select name,fecha from projects where p_id=?;");

	    ps.setLong(1, idP);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		project.setNombre(rs.getString("name"));
		Date fecha = rs.getDate("fecha");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
		String f = sdf.format(fecha);
		project.setFecha(f);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return project;
    }

    /**
     * Método para actualizar en la base de datos el paso del proyecto pasado
     * como parámetro
     * 
     * @param idP
     *            identificador del proyecto
     * 
     * @param step
     *            paso a actualizar en el proyecto
     * 
     * @throws Exception
     */
    @Override
    public void updateStep(Long idP, Integer step) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	con = Jdbc.getConnection();
	ps = con.prepareStatement("UPDATE projects SET paso=? WHERE p_id=?;");
	ps.setLong(1, step);
	ps.setLong(2, idP);
	ps.executeUpdate();
    }
}