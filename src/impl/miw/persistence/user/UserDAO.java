package impl.miw.persistence.user;

import impl.miw.persistence.Jdbc;
import impl.miw.persistence.project.ProjectDAO;
import impl.miw.presentation.passforgotten.PassForgottenAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.miw.model.User;
import com.miw.persistence.UserDataService;

/**
 * Clase que implementa las operaciones de acceso a la base de datos para la
 * entidad USER.
 * 
 * @author Pablo
 */
public class UserDAO implements UserDataService {

    /**
     * Getter para la obtención de los usuarios en la base de datos
     * 
     * @return Vector vector con los datos de los usuarios
     * @throws Exception
     */
    @Override
    public Vector<User> getUsers() throws Exception {
	// Inicializamos el Vector de retorno.
	Vector<User> resultado = new Vector<User>();
	PreparedStatement ps = null;

	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select u_id as id, p_id,user, AES_DECRYPT(email,@pass) as email, AES_DECRYPT(pass,@pass) as pass, admin, manager, language from users");
	    rs = ps.executeQuery();

	    while (rs.next()) {
		// Completamos los datos del user en la entidad
		User u = new User();
		u.setId(rs.getLong("id"));
		u.setLogin(rs.getString("user"));
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("pass"));
		u.setAdmin(rs.getBoolean("admin"));
		u.setManager(rs.getBoolean("manager"));
		u.setLanguage(rs.getString("language"));
		u.setIdProyecto(rs.getLong("p_id"));
		// La añadimos al Vector de resultado
		resultado.add(u);
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
     * Setter para establecer los datos del usuario
     * 
     * @param user
     *            datos del usuario
     * @return String cadena para log
     * @throws Exception
     */
    @Override
    public String setUser(User user) throws Exception {
	// Inicializamos el String de retorno.
	String resultado = "";

	if (!existEmail(user)) {

	    PreparedStatement ps = null;
	    Connection con = null;

	    try {

		con = Jdbc.getConnection();
		ps = con.prepareStatement("SELECT @pass:='tfm14';");
		ps.executeQuery();

		if (user.getIdProyecto() != null) {
		    ps = con.prepareStatement("INSERT INTO users (user,email,pass,language,admin,manager,created,p_id) VALUES (?,AES_ENCRYPT(?,@pass),AES_ENCRYPT(?,@pass),?,?,?,?,?);");

		    ps.setString(1, user.getLogin());
		    ps.setString(2, user.getEmail());
		    ps.setString(3, user.getPassword());
		    String language = user.getLanguage();
		    String[] pieces = language.split(" ");
		    language = pieces[0];
		    ps.setString(4, language);
		    ps.setBoolean(5, user.isAdmin());
		    ps.setBoolean(6, user.isManager());
		    ps.setTimestamp(7, null);
		    ps.setLong(8, user.getIdProyecto());

		    ps.executeUpdate();
		} else {
		    ps = con.prepareStatement("INSERT INTO users (user,email,pass,language,admin,manager,created) VALUES (?,AES_ENCRYPT(?,@pass),AES_ENCRYPT(?,@pass),?,?,?,?);");

		    ps.setString(1, user.getLogin());
		    ps.setString(2, user.getEmail());
		    ps.setString(3, user.getPassword());
		    String language = user.getLanguage();
		    String[] pieces = language.split(" ");
		    language = pieces[0];
		    ps.setString(4, language);
		    ps.setBoolean(5, user.isAdmin());
		    ps.setBoolean(6, user.isManager());
		    ps.setTimestamp(7, null);

		    ps.executeUpdate();
		}

		if (user.isManager() && user.getIdProyecto() != null) {
		    updateStepProject(ps, con, user.getIdProyecto(), 1);
		} else {

		}

		resultado = "Datos tratados correctamente";

	    } catch (Exception e) {
		resultado = "Datos no tratados";
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

	} else {
	    resultado = "Email";
	}
	// Retornamos el vector de resultado.
	return resultado;
    }

    /**
     * Método para actualizar el paso de generación de la Gestión de Riesgos en
     * el proyecto actual
     * 
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param idProyecto
     *            identificador del proyecto a actualizar
     * @param paso
     *            número de paso
     * @exception SQLException
     */
    private void updateStepProject(PreparedStatement ps, Connection con,
	    Long idProyecto, int paso) throws Exception {

	ProjectDAO pDAO = new ProjectDAO();
	pDAO.updateStep(idProyecto, paso);
    }

    /**
     * Método para verificar la existencia del correo del usuario
     * 
     * @param user
     *            usuario
     * @return boolean booleano true si existe false sino
     * @throws Exception
     */
    private boolean existEmail(User user) throws Exception {
	boolean resultado = false;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select user from users where email=AES_ENCRYPT(?,@pass)");

	    ps.setString(1, user.getEmail());
	    rs = ps.executeQuery();

	    if (rs.next()) {
		resultado = true;
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
	return resultado;
    }

    /**
     * Getter para la obtención de la contraseña
     * 
     * @param passForgottenAction
     *            action con todos los datos recibidos de la request
     * @return User usuario con todos sus datos
     * @throws Exception
     */
    @Override
    public User getPassForgotten(PassForgottenAction passForgottenAction)
	    throws Exception {
	// Inicializamos el Vector de retorno.
	User resultado = new User();

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select user, AES_DECRYPT(pass,@pass) as pass, AES_DECRYPT(email,@pass) as email, language  from users where email=AES_ENCRYPT(?,@pass)");

	    ps.setString(1, passForgottenAction.getModel().getEmail());
	    rs = ps.executeQuery();

	    User u = null;
	    while (rs.next()) {
		u = new User();
		u.setLanguage(rs.getString("language"));
		u.setPassword(rs.getString("pass"));
		u.setLogin(rs.getString("user"));
		u.setEmail(rs.getString("email"));
	    }
	    resultado = u;

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
	// Retornamos el resultado.
	return resultado;
    }

    /**
     * Setter para actualizar el usuario
     * 
     * @param user
     *            datos del usuario que va a actualizarse
     * @return String cadena para el log
     * @throws Exception
     */
    @Override
    public String setUpdateUser(User user) throws Exception {
	String resultado = "";
	PreparedStatement ps = null;
	Connection con = null;

	try {

	    con = Jdbc.getConnection();
	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("UPDATE users SET user=?, email=AES_ENCRYPT(?,@pass), pass=AES_ENCRYPT(?,@pass), language=? WHERE  u_id=?");

	    ps.setString(1, user.getLogin());
	    ps.setString(2, user.getEmail());
	    ps.setString(3, user.getPassword());
	    String language = user.getLanguage();
	    String[] pieces = language.split(" ");
	    language = pieces[0];
	    ps.setString(4, language);
	    ps.setLong(5, user.getId());

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
     * Getter para la obtención de los managers
     * 
     * @return Vector vector con los datos de los usuarios tipo manager
     * @throws Exception
     */
    @Override
    public Vector<User> getManagers() throws Exception {
	// Inicializamos el Vector de retorno.
	Vector<User> resultado = new Vector<User>();
	PreparedStatement ps = null;

	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select u_id as id, p_id,user, AES_DECRYPT(email,@pass) as email, created, modify from users where manager = '1';");
	    rs = ps.executeQuery();

	    while (rs.next()) {
		// Completamos los datos del user en la entidad
		User u = new User();
		u.setId(rs.getLong("id"));
		u.setLogin(rs.getString("user"));
		u.setEmail(rs.getString("email"));
		u.setIdProyecto(rs.getLong("p_id"));
		u.setCreate(rs.getString("created"));
		u.setModify(rs.getString("modify"));
		// La añadimos al Vector de resultado
		resultado.add(u);
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
     * Getter para la obtención de los usuarios por medio de su correo
     * 
     * @param email
     *            correo identificador del usuario
     * 
     * @return User usuarios de base de datos
     * @throws Exception
     */
    @Override
    public User getUser(String email) throws Exception {
	User u = null;
	PreparedStatement ps = null;

	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select user, AES_DECRYPT(email,@pass) as email, AES_DECRYPT(pass,@pass) as pass, language from users where email=AES_ENCRYPT(?,@pass);");

	    ps.setString(1, email);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		u = new User();
		u.setLogin(rs.getString("user"));
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("pass"));
		u.setLanguage(rs.getString("language"));
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
	return u;
    }

    /**
     * Getter para la obtención de los usuarios por medio del proyecto
     * 
     * @param idProyecto
     *            identificador del proyecto
     * 
     * @return Vector vector con los usurios de ese proyecto
     * @throws Exception
     */
    @Override
    public Vector<User> getUsers(Long idProyecto) throws Exception {
	// Inicializamos el Vector de retorno.
	Vector<User> resultado = new Vector<User>();
	PreparedStatement ps = null;

	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select u_id as id, p_id,user, AES_DECRYPT(email,@pass) as email, manager, language, created, modify from users where p_id=?");

	    ps.setLong(1, idProyecto);
	    rs = ps.executeQuery();

	    while (rs.next()) {
		// Completamos los datos del user en la entidad
		User u = new User();
		u.setId(rs.getLong("id"));
		u.setLogin(rs.getString("user"));
		u.setEmail(rs.getString("email"));
		u.setManager(rs.getBoolean("manager"));
		u.setLanguage(rs.getString("language"));
		u.setIdProyecto(rs.getLong("p_id"));
		u.setCreate(rs.getString("created"));
		u.setModify(rs.getString("modify"));
		// La añadimos al Vector de resultado
		resultado.add(u);
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
     * Método para elimianr usuarios
     * 
     * @param idUser
     *            identificador del usuario a eliminar
     * @return Boolean booleano true si se ha borrado sino false
     * @throws Exception
     */
    @Override
    public Boolean deleteUser(Long idUser) throws Exception {
	// Inicializamos el String de retorno.
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("select user, AES_DECRYPT(email,@pass) as email from users where u_id=?;");
	    ps.setLong(1, idUser);

	    String manager = "";
	    String email = "";

	    rs = ps.executeQuery();
	    while (rs.next()) {
		manager = rs.getString("user");
		email = rs.getString("email");
	    }

	    ps = con.prepareStatement("UPDATE projects SET manager='', email=AES_ENCRYPT('',@pass) WHERE email=AES_ENCRYPT(?,@pass) and manager=?;");
	    ps.setString(1, email);
	    ps.setString(2, manager);
	    ps.executeUpdate();

	    ps = con.prepareStatement("delete from users where u_id=?;");
	    ps.setLong(1, idUser);
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
     * Setter para actualizar el usuario admin
     * 
     * @param updateUser
     *            datos del usuario que va a actualizarse
     * @return String cadena para el log
     * @throws Exception
     */
    @Override
    public String setUpdateUserAdmin(User updateUser) throws Exception {
	String resultado = "";
	PreparedStatement ps = null;
	Connection con = null;

	try {

	    con = Jdbc.getConnection();
	    ps = con.prepareStatement("SELECT @pass:='tfm14';");
	    ps.executeQuery();

	    ps = con.prepareStatement("UPDATE users SET user=?, email=AES_ENCRYPT(?,@pass), p_id=? WHERE u_id=?");

	    ps.setString(1, updateUser.getLogin());
	    ps.setString(2, updateUser.getEmail());
	    ps.setLong(3, updateUser.getIdProyecto());
	    ps.setLong(4, updateUser.getId());

	    ps.executeUpdate();

	    ps = con.prepareStatement("UPDATE projects SET manager=?, email=AES_ENCRYPT(?,@pass) WHERE p_id=?;");
	    ps.setString(1, updateUser.getLogin());
	    ps.setString(2, updateUser.getEmail());
	    ps.setLong(3, updateUser.getIdProyecto());
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
}
