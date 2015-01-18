package impl.miw.persistence.info;

import impl.miw.persistence.Jdbc;
import impl.miw.persistence.project.ProjectDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.miw.model.Cambio;
import com.miw.model.Iden;
import com.miw.model.Impacto;
import com.miw.model.Info;
import com.miw.model.Probabilidad;
import com.miw.model.Respuesta;
import com.miw.persistence.InfoDataService;

/**
 * Clase que implementa las operaciones de acceso a la base de datos para las
 * entidades relacionadas con los RIESGOS.
 * 
 * @author Pablo
 * 
 */
public class InfoDAO implements InfoDataService {

    /**
     * Setter de la información del riesgo, mediante la identificación del
     * usuario del proyecto y de la versión
     * 
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @param version
     *            versión
     * @param data
     *            Datos del riesgo
     * @throws Exception
     */
    @Override
    public void setInfo(Long id, Long idP, Double version, Info data)
	    throws Exception {

	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;

	try {
	    con = Jdbc.getConnection();

	    if (uniquePlans(ps, con, rs, id, idP)) {
		ps = con.prepareStatement("INSERT INTO plans (project_id,u_id,version,created) VALUES (?,?,?,?)");
		ps.setLong(1, idP);
		ps.setLong(2, id);
		ps.setDouble(3, version);
		ps.setTimestamp(4, null);
		ps.executeUpdate();

		Long pgr_id = getId_pgr(ps, con, rs, idP, id, version);
		setVersion(id, idP, pgr_id, version, 0);

		ProjectDAO pDAO = new ProjectDAO();
		pDAO.updateStep(idP, 4);
	    } else {
		int versionE = (int) Math.floor(version);
		Double versionNew = (double) versionE + 1;
		versionNew = (double) Math.round(versionNew * 10000) / 10000;
		ps = con.prepareStatement("INSERT INTO plans (project_id,u_id,version,metodologia,herrotecno,roles,presu,calendario,riesgos,corte,rango,contingencia,formatos,created) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setLong(1, idP);
		ps.setLong(2, id);
		ps.setDouble(3, versionNew);
		ps.setString(4, data.getMetodologia());
		ps.setString(5, data.getHtecno());
		ps.setString(6, data.getRoles());
		ps.setString(7, data.getPresu());
		ps.setString(8, data.getCalendar());
		ps.setString(9, data.getRiesgo());
		ps.setDouble(10, data.getCorte());
		ps.setString(11, data.getRango());
		ps.setString(12, data.getContingencia());
		ps.setString(13, data.getFormato());
		ps.setTimestamp(14, null);
		ps.executeUpdate();

		Long pgr_id = getId_pgr(ps, con, rs, idP, id, versionNew);
		// Insertar nueva version de probabilidad
		insertProbabilidad(data, ps, con, pgr_id);
		// Insertar impactos
		insertImpactos(data, ps, con, pgr_id);
		// Insertar las probabilidaddes de los impactos
		inserProbabilidadImpactos(data, ps, con, rs, pgr_id);

		setVersion(id, idP, pgr_id, versionNew, 0);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Método para confirmación de planes de riesgos únicos o no
     * 
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param rs
     *            resultado
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return Boolea si true si es único false si no
     * @throws SQLException
     */
    private Boolean uniquePlans(PreparedStatement ps, Connection con,
	    ResultSet rs, Long id, Long idP) throws SQLException {
	boolean vacio = true;
	ps = con.prepareStatement("select pgr_id from plans where project_id=? and u_id=?;");
	ps.setLong(1, idP);
	ps.setLong(2, id);
	rs = ps.executeQuery();
	while (rs.next()) {
	    vacio = false;
	}
	return vacio;
    }

    /**
     * Setter para establecer los datos de actualización de los planes de
     * riesgos en la base de datos
     * 
     * @param data
     *            datos del riesgo
     * @throws Exception
     */
    @Override
    public void setUpdateInfo(Info data) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;

	try {

	    con = Jdbc.getConnection();
	    Double version = data.getVersion() - 0.0001;
	    version = (double) Math.round(version * 10000) / 10000;
	    Long pgr_id = getId_pgr(ps, con, rs, data.getIdP(), data.getIdU(),
		    version);
	    setCambio(data, pgr_id);
	    ps = con.prepareStatement("UPDATE plans SET version=? WHERE u_id=? and pgr_id=?");
	    ps.setDouble(1, data.getVersion());
	    ps.setLong(2, data.getIdU());
	    ps.setLong(3, pgr_id);
	    ps.executeUpdate();

	    if (data.getMetodologia() != null
		    && data.getMetodologia().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET metodologia=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getMetodologia());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 1);
	    }
	    if (data.getHtecno() != null && data.getHtecno().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET herrotecno=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getHtecno());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 2);
	    }
	    if (data.getRoles() != null && data.getRoles().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET roles=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getRoles());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 3);
	    }
	    if (data.getPresu() != null && data.getPresu().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET presu=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getPresu());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 4);
	    }
	    if (data.getCalendar() != null
		    && data.getCalendar().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET calendario=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getCalendar());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 5);
	    }
	    if (data.getRiesgo() != null && data.getRiesgo().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET riesgos=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getRiesgo());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 6);
	    }
	    if (data.getProbabilidad() != null) {

		if (getProbabilidad(pgr_id) != null) {
		    ps = con.prepareStatement("delete from probabilidades where pgr_id=? and codplan=8;");
		    ps.setLong(1, pgr_id);
		    ps.executeUpdate();
		}
		insertProbabilidad(data, ps, con, pgr_id);

		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 7);
	    }
	    if (data.getImpacto() != null) {

		if (getImpacto(pgr_id) != null) {
		    ps = con.prepareStatement("delete from impactos where pgr_id=?;");
		    ps.setLong(1, pgr_id);
		    ps.executeUpdate();
		}

		insertImpactos(data, ps, con, pgr_id);

		inserProbabilidadImpactos(data, ps, con, rs, pgr_id);

		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 8);
	    }
	    if (data.getCorte() > 0.0) {
		ps = con.prepareStatement("UPDATE plans SET corte=? WHERE u_id=? and pgr_id=?");
		ps.setDouble(1, data.getCorte());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
	    }
	    if (data.getRango().compareToIgnoreCase("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET rango=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getRango());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
	    }
	    if (data.getContingencia() != null
		    && data.getContingencia().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET contingencia=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getContingencia());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 9);
	    }
	    if (data.getFormato() != null
		    && data.getFormato().compareTo("") != 0) {
		ps = con.prepareStatement("UPDATE plans SET formatos=? WHERE u_id=? and pgr_id=?");
		ps.setString(1, data.getFormato());
		ps.setLong(2, data.getIdU());
		ps.setLong(3, pgr_id);
		ps.executeUpdate();
		setVersion(data.getIdU(), data.getIdP(), pgr_id,
			data.getVersion(), 10);
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
    }

    /**
     * Método para la inserción de las probabilidades por medio de la busqueda
     * de los impactos de los planes de riesgo
     * 
     * @param data
     *            datos de los impactos
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param rs
     *            resultado
     * @param pgr_id
     *            identificación del plan de riesgos
     * @throws SQLException
     */
    private void inserProbabilidadImpactos(Info data, PreparedStatement ps,
	    Connection con, ResultSet rs, Long pgr_id) throws Exception {
	Vector<Long> id = new Vector<Long>();
	ps = con.prepareStatement("select i_id from impactos where pgr_id=?;");
	ps.setLong(1, pgr_id);
	rs = ps.executeQuery();
	while (rs.next()) {
	    id.add(rs.getLong("i_id"));
	}

	for (int i = 0; i < id.size(); i++) {
	    insertProbabilidades(pgr_id, data.getImpacto().get(i)
		    .getProbabilidad(), id.get(i));
	}
    }

    /**
     * Método para la inserción de los impactos de los planes de riesgo
     * 
     * @param data
     *            datos de los impactos
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param pgr_id
     *            identificación del plan de riesgos
     * @throws SQLException
     */
    private void insertImpactos(Info data, PreparedStatement ps,
	    Connection con, Long pgr_id) throws SQLException {
	for (int i = 0; i < data.getImpacto().size(); i++) {
	    ps = con.prepareStatement("INSERT INTO impactos (objetivo,pgr_id) VALUES (?,?)");
	    ps.setString(1, data.getImpacto().get(i).getObjetivo());
	    ps.setLong(2, pgr_id);
	    ps.executeUpdate();
	}
    }

    /**
     * Método para la inserción de las probabilidades de los planes de riesgo
     * 
     * @param data
     *            datos de los impactos
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param pgr_id
     *            identificación del plan de riesgos
     * @throws SQLException
     */
    private void insertProbabilidad(Info data, PreparedStatement ps,
	    Connection con, Long pgr_id) throws SQLException {
	for (int i = 0; i < data.getProbabilidad().size(); i++) {
	    ps = con.prepareStatement("INSERT INTO probabilidades (porcentaje,probabilidad,descripcion,pgr_id,codplan) VALUES (?,?,?,?,8)");
	    ps.setInt(1, data.getProbabilidad().get(i).getPorcentaje());
	    ps.setString(2, data.getProbabilidad().get(i).getProbabilidad());
	    ps.setString(3, data.getProbabilidad().get(i).getDescripcion());
	    ps.setLong(4, pgr_id);
	    ps.executeUpdate();
	}
    }

    /**
     * Setter para los datos de las versiones de los planes de riesgo
     * 
     * @param idU
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @param pgr_id
     *            identificación del plan de riesgos
     * @param version
     *            versión
     * @param accion
     *            acción del plan realizada
     * @throws Exception
     */
    private void setVersion(Long idU, Long idP, Long pgr_id, Double version,
	    Integer accion) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	con = Jdbc.getConnection();

	switch (accion) {
	case 1:
	    ps = con.prepareStatement("UPDATE versiones SET metodologia=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 2:
	    ps = con.prepareStatement("UPDATE versiones SET herrotecno=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 3:
	    ps = con.prepareStatement("UPDATE versiones SET roles=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 4:
	    ps = con.prepareStatement("UPDATE versiones SET presu=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 5:
	    ps = con.prepareStatement("UPDATE versiones SET calendario=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 6:
	    ps = con.prepareStatement("UPDATE versiones SET riesgos=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 7:
	    ps = con.prepareStatement("UPDATE versiones SET probabilidad=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 8:
	    ps = con.prepareStatement("UPDATE versiones SET impacto=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 9:
	    ps = con.prepareStatement("UPDATE versiones SET contingencia=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	case 10:
	    ps = con.prepareStatement("UPDATE versiones SET formatos=?, version=? WHERE u_id=? and project_id=? and pgr_id=?;");
	    ps.setInt(1, 1);
	    ps.setDouble(2, version);
	    ps.setLong(3, idU);
	    ps.setLong(4, idP);
	    ps.setLong(5, pgr_id);
	    ps.executeUpdate();
	    break;
	default:
	    ps = con.prepareStatement("INSERT INTO versiones (version,u_id,project_id,pgr_id,total) VALUES (?,?,?,?,0);");
	    ps.setDouble(1, version);
	    ps.setLong(2, idU);
	    ps.setLong(3, idP);
	    ps.setLong(4, pgr_id);
	    ps.executeUpdate();
	    break;
	}
	AllUpdate(idU, idP, pgr_id);
    }

    /**
     * Método para saber caundo pasar de versión, al actualizarse todos los
     * objetivos del plan
     * 
     * @param idU
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @param pgr_id
     *            identificación del plan de riesgos
     * @throws Exception
     */
    private void AllUpdate(Long idU, Long idP, Long pgr_id) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	int total = 0;
	Double version = 0.0;
	int nuevo = 0;

	con = Jdbc.getConnection();

	ps = con.prepareStatement("select version, metodologia, herrotecno, roles, presu, calendario, riesgos, probabilidad, impacto, contingencia, formatos, total from versiones where project_id=? and u_id=? and pgr_id=?;");
	ps.setLong(1, idP);
	ps.setLong(2, idU);
	ps.setLong(3, pgr_id);

	rs = ps.executeQuery();
	while (rs.next()) {
	    total = total + rs.getInt("metodologia") + rs.getInt("herrotecno")
		    + rs.getInt("roles") + rs.getInt("presu")
		    + rs.getInt("calendario") + rs.getInt("riesgos")
		    + rs.getInt("probabilidad") + rs.getInt("impacto")
		    + rs.getInt("contingencia") + rs.getInt("formatos");

	    version = rs.getDouble("version");

	    nuevo = rs.getInt("total");
	}

	if (total == 10 && nuevo != 1) {
	    Info data = getInfo(idU, idP, version);
	    setInfo(idU, idP, version, data);
	    ps = con.prepareStatement("UPDATE versiones SET total=1 WHERE version=? and u_id=? and project_id=? and pgr_id=?;");
	    ps.setDouble(1, version);
	    ps.setLong(2, idU);
	    ps.setLong(3, idP);
	    ps.setLong(4, pgr_id);
	    ps.executeUpdate();
	}
    }

    /**
     * Método para obtener el identificador del plan de riesgos
     * 
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param rs
     *            resultado
     * @param idP
     *            identificación del proyecto
     * 
     * @param idU
     *            identificación del usuario
     * 
     * @param version
     *            versión
     * @return Long identificación del plan de riesgos
     * @throws SQLException
     */
    private Long getId_pgr(PreparedStatement ps, Connection con, ResultSet rs,
	    Long idP, Long idU, Double version) throws SQLException {
	Long pgr_id = 0L;

	ps = con.prepareStatement("select pgr_id from plans where project_id=? and u_id=? and version=?;");
	ps.setLong(1, idP);
	ps.setLong(2, idU);
	ps.setDouble(3, version);
	rs = ps.executeQuery();
	while (rs.next()) {
	    pgr_id = rs.getLong("pgr_id");
	}
	return pgr_id;
    }

    /**
     * Setter para establecer el cambio de actualización en el plan de riesgos
     * 
     * @param data
     *            información del plan
     * @param pgr_id
     *            identificación del plan de riesgos
     * @throws SQLException
     */
    private void setCambio(Info data, Long pgr_id) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;

	con = Jdbc.getConnection();

	ps = con.prepareStatement("INSERT INTO cambios (pgr_id,plan,version) VALUES (?,?,?);");
	ps.setLong(1, pgr_id);
	ps.setString(2, data.getCambio().get(0).getPlan());
	ps.setDouble(3, data.getCambio().get(0).getVersion());
	ps.executeUpdate();
    }

    /**
     * Getter para obtener la probabilidad de los impactos
     * 
     * @param pgr_id
     *            identificación de plan de riesgos
     * @param i_id
     *            identificación del impacto
     * @return Vector vector con los datos de las probabilidades
     * @throws SQLException
     */
    private Vector<Probabilidad> getProbabilidadImpactos(Long pgr_id, Long i_id)
	    throws Exception {
	Vector<Probabilidad> vp = new Vector<Probabilidad>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	con = Jdbc.getConnection();
	ps = con.prepareStatement("select porcentaje,probabilidad,descripcion from probabilidades where pgr_id=? and i_id=? and codplan=9;");
	ps.setLong(1, pgr_id);
	ps.setLong(2, i_id);

	rs = ps.executeQuery();

	while (rs.next()) {
	    Probabilidad p = new Probabilidad();
	    p.setPorcentaje(rs.getInt("porcentaje"));
	    p.setProbabilidad(rs.getString("probabilidad"));
	    p.setDescripcion(rs.getString("descripcion"));
	    vp.add(p);
	}

	return vp;
    }

    /**
     * Método para insertar las probabilidades de los impactos
     * 
     * @param pgr_id
     *            identificación del plan de riesgo
     * @param probabilidad
     *            datos de la probabilidad
     * @param i_id
     *            identificación del impacto
     * @throws SQLException
     */
    private void insertProbabilidades(Long pgr_id,
	    Vector<Probabilidad> probabilidad, Long i_id) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	con = Jdbc.getConnection();
	for (int i = 0; i < probabilidad.size(); i++) {
	    ps = con.prepareStatement("INSERT INTO probabilidades (porcentaje,probabilidad,descripcion,pgr_id,i_id,codplan) VALUES (?,?,?,?,?,9)");
	    ps.setInt(1, probabilidad.get(i).getPorcentaje());
	    ps.setString(2, probabilidad.get(i).getProbabilidad());
	    ps.setString(3, probabilidad.get(i).getDescripcion());
	    ps.setLong(4, pgr_id);
	    ps.setLong(5, i_id);
	    ps.executeUpdate();
	}
    }

    /**
     * Getter para la obtención de los datos del plan de riesgo
     * 
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @param version
     *            versión
     * @throws Exception
     */
    @Override
    public Info getInfo(Long id, Long idP, Double version) throws Exception {
	Info info = new Info();
	boolean existe = false;
	Double versionI = 1.0;
	version = (double) Math.round(version * 10000) / 10000;

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("select pgr_id,project_id,version,metodologia,herrotecno,roles,presu,calendario,riesgos,corte,rango,contingencia,formatos from plans where project_id=? and u_id=? and version=?;");
	    ps.setLong(1, idP);
	    ps.setLong(2, id);
	    ps.setDouble(3, version);

	    rs = ps.executeQuery();

	    while (rs.next()) {
		info.setId(rs.getLong("pgr_id"));

		info.setVersion(rs.getDouble("version"));
		info.setIdP(rs.getLong("project_id"));
		info.setCambio(getCambio(info.getId(), 0L));
		info.setMetodologia(rs.getString("metodologia"));
		info.setHtecno(rs.getString("herrotecno"));
		info.setRoles(rs.getString("roles"));
		info.setPresu(rs.getString("presu"));
		info.setCalendar(rs.getString("calendario"));
		info.setRiesgo(rs.getString("riesgos"));
		info.setProbabilidad(getProbabilidad(info.getId()));
		info.setImpacto(getImpacto(info.getId()));
		info.setCorte(rs.getDouble("corte"));
		info.setRango(rs.getString("rango"));
		info.setContingencia(rs.getString("contingencia"));
		info.setFormato(rs.getString("formatos"));
		existe = true;
	    }

	    // SE CREA EL INFODATA SINO EXISTE
	    if (!existe) {
		setInfo(id, idP, versionI, null);
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
	return info;
    }

    /**
     * Getter para la obtención de los cambios producidos en las actualizaciones
     * de los planes de riesgos
     * 
     * @param pgr_id
     *            identificación del plan de riesgo
     * @return Vector vector con los datos del plan
     * @throws SQLException
     */
    private Vector<Cambio> getCambio(Long pgr_id, Long iden_id)
	    throws Exception {
	Vector<Cambio> vc = new Vector<Cambio>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	con = Jdbc.getConnection();

	if (pgr_id != 0L) {
	    ps = con.prepareStatement("select cb_id,plan,version,created from cambios where pgr_id=?;");
	    ps.setLong(1, pgr_id);

	    rs = ps.executeQuery();

	    while (rs.next()) {
		Cambio cb = new Cambio();
		cb.setId(rs.getLong("cb_id"));
		cb.setPlan(rs.getString("plan"));
		cb.setVersion(rs.getDouble("version"));
		Date fecha = rs.getDate("created");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
		cb.setFecha(sdf.format(fecha));
		vc.add(cb);
	    }
	} else if (iden_id != 0L) {
	    ps = con.prepareStatement("select cb_id,plan,version,created from cambios where iden_id=?;");
	    ps.setLong(1, iden_id);

	    rs = ps.executeQuery();

	    while (rs.next()) {
		Cambio cb = new Cambio();
		cb.setId(rs.getLong("cb_id"));
		cb.setPlan(rs.getString("plan"));
		cb.setVersion(rs.getDouble("version"));
		Date fecha = rs.getDate("created");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
		cb.setFecha(sdf.format(fecha));
		vc.add(cb);
	    }
	}
	return vc;
    }

    /**
     * Getter para obtener los datos de las probabilidad del plan de riesgo
     * 
     * @param pgr_id
     *            identificación del plan de riesgo
     * @return Vector vector con los datos de las probabilidades del plan
     * @throws SQLException
     */
    private Vector<Probabilidad> getProbabilidad(Long pgr_id) throws Exception {
	Vector<Probabilidad> vp = new Vector<Probabilidad>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	con = Jdbc.getConnection();
	ps = con.prepareStatement("select porcentaje,probabilidad,descripcion from probabilidades where pgr_id=? and codplan=8;");
	ps.setLong(1, pgr_id);

	rs = ps.executeQuery();

	while (rs.next()) {
	    Probabilidad p = new Probabilidad();
	    p.setPorcentaje(rs.getInt("porcentaje"));
	    p.setProbabilidad(rs.getString("probabilidad"));
	    p.setDescripcion(rs.getString("descripcion"));
	    vp.add(p);
	}
	return vp;
    }

    /**
     * Getter para obtener los datos de los impactos del plan de riesgo
     * 
     * @param pgr_id
     *            identificación del plan de riesgo
     * @return Vector vector con los datos de los impactos del plan
     * @throws SQLException
     */
    private Vector<Impacto> getImpacto(Long pgr_id) throws Exception {
	Vector<Impacto> vi = new Vector<Impacto>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	con = Jdbc.getConnection();
	ps = con.prepareStatement("select i_id, objetivo from impactos where pgr_id=?;");
	ps.setLong(1, pgr_id);

	rs = ps.executeQuery();

	while (rs.next()) {
	    Impacto i = new Impacto();
	    i.setObjetivo(rs.getString("objetivo"));
	    Long i_id = rs.getLong("i_id");
	    i.setProbabilidad(getProbabilidadImpactos(pgr_id, i_id));
	    vi.add(i);
	}
	return vi;
    }

    /**
     * Setter para establecer los datos de las identificaciones de los riesgos
     * 
     * @param vIden
     *            vector con las identificaciones
     * @throws Exception
     */
    @Override
    public void setIden(Vector<Iden> vIden) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;

	try {
	    Double version = vIden.get(0).getVersion();
	    version = (double) Math.round(version * 10000) / 10000;
	    con = Jdbc.getConnection();

	    if (uniqueIdens(ps, con, rs, vIden.get(0).getIdU(), vIden.get(0)
		    .getIdP())) {
		insertIden(vIden, ps, con);
	    } else {
		version = version - 0.0001;
		version = (double) Math.round(version * 10000) / 10000;
		Vector<Long> iden_id = getId_iden(ps, con, rs, vIden.get(0)
			.getIdP(), vIden.get(0).getIdU(), version);
		for (int j = 0; j < vIden.size(); j++) {
		    ps = con.prepareStatement("UPDATE idens SET project_id=?, u_id=?, id=?, nombre=?, descripcion=?, responsable=?, probabilidad=?, valorImpacto=?, vImpacto=?, response=?, notes=?, version=? WHERE u_id=? and iden_id=? and version=?");
		    ps.setLong(1, vIden.get(j).getIdP());
		    ps.setLong(2, vIden.get(j).getIdU());
		    ps.setInt(3, vIden.get(j).getId());
		    ps.setString(4, vIden.get(j).getNombre());
		    ps.setString(5, vIden.get(j).getDescripcion());
		    ps.setString(6, vIden.get(j).getResponsable());
		    ps.setInt(7, vIden.get(j).getProbabilidad());
		    ps.setString(8, vIden.get(j).getImpacto());
		    ps.setDouble(9, vIden.get(j).getValor());
		    ps.setString(10, vIden.get(j).getResponse());
		    ps.setString(11, vIden.get(j).getNotes());
		    ps.setDouble(12, vIden.get(j).getVersion());
		    ps.setLong(13, vIden.get(j).getIdU());
		    ps.setLong(14, iden_id.get(j));
		    ps.setDouble(15, version);
		    ps.executeUpdate();
		    setCambioIden(vIden.get(j), iden_id.get(j));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Setter para establecer el cambio de actualización en el plan de riesgos
     * 
     * @param vIden
     *            información de os difderentes identificadores de riesgos
     * @param iden_id
     *            identificación del identificador de riesgos
     * @throws SQLException
     */
    private void setCambioIden(Iden iden, Long iden_id) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;

	con = Jdbc.getConnection();

	ps = con.prepareStatement("INSERT INTO cambios (iden_id,plan,version) VALUES (?,?,?);");
	ps.setLong(1, iden_id);
	ps.setString(2, iden.getCambio().get(0).getPlan());
	ps.setDouble(3, iden.getCambio().get(0).getVersion());
	ps.executeUpdate();
    }

    /**
     * Método para obtener el identificador del identificador de riesgos
     * 
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param rs
     *            resultado
     * @param idP
     *            identificación del proyecto
     * 
     * @param idU
     *            identificación del usuario
     * 
     * @param version
     *            versión
     * @return Vector de longs, identificación de riesgos
     * @throws SQLException
     */
    private Vector<Long> getId_iden(PreparedStatement ps, Connection con,
	    ResultSet rs, Long idP, Long idU, Double version)
	    throws SQLException {
	Vector<Long> iden_id = new Vector<Long>();

	ps = con.prepareStatement("select iden_id from idens where project_id=? and u_id=? and version=?;");
	ps.setLong(1, idP);
	ps.setLong(2, idU);
	ps.setDouble(3, version);
	rs = ps.executeQuery();
	while (rs.next()) {
	    iden_id.add(rs.getLong("iden_id"));
	}
	return iden_id;
    }

    /**
     * Método para insertar las identificaciones de los riesgos
     * 
     * @param vIden
     *            vector con los datos de las identificaciones
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @throws SQLException
     */
    private void insertIden(Vector<Iden> vIden, PreparedStatement ps,
	    Connection con) throws SQLException {
	for (int i = 0; i < vIden.size(); i++) {
	    ps = con.prepareStatement("INSERT INTO idens (project_id,u_id,id,nombre,descripcion,responsable,probabilidad,valorImpacto,vImpacto,response,notes,version,created) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
	    ps.setLong(1, vIden.get(i).getIdP());
	    ps.setLong(2, vIden.get(i).getIdU());
	    ps.setInt(3, vIden.get(i).getId());
	    ps.setString(4, vIden.get(i).getNombre());
	    ps.setString(5, vIden.get(i).getDescripcion());
	    ps.setString(6, vIden.get(i).getResponsable());
	    ps.setInt(7, vIden.get(i).getProbabilidad());
	    ps.setString(8, vIden.get(i).getImpacto());
	    ps.setDouble(9, vIden.get(i).getValor());
	    ps.setString(10, vIden.get(i).getResponse());
	    ps.setString(11, vIden.get(i).getNotes());
	    ps.setDouble(12, vIden.get(i).getVersion());
	    ps.setTimestamp(13, null);
	    ps.executeUpdate();
	}
    }

    /**
     * Método para confirmación de identificación de riesgos únicos o no
     * 
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param rs
     *            resultado
     * @param idU
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return valor booleano si true si es único false si no
     * @throws SQLException
     */
    private boolean uniqueIdens(PreparedStatement ps, Connection con,
	    ResultSet rs, Long idU, Long idP) throws SQLException {
	boolean vacio = true;
	ps = con.prepareStatement("select iden_id from idens where project_id=? and u_id=?;");
	ps.setLong(1, idP);
	ps.setLong(2, idU);
	rs = ps.executeQuery();
	while (rs.next()) {
	    vacio = false;
	    break;
	}
	return vacio;
    }

    /**
     * Getter para la obtención de las identificaciones de los riesgos
     * 
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return Vector vector con los datos de la identificación de los riesgos
     * @throws Exception
     */
    @Override
    public Vector<Iden> getIden(Long id, Long idP) throws Exception {
	Iden iden;
	Vector<Iden> vIden = new Vector<Iden>();

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("select iden_id,id,project_id,nombre,descripcion,responsable,probabilidad,valorImpacto,vImpacto,response,notes,version from idens where project_id=? and u_id=?;");
	    ps.setLong(1, idP);
	    ps.setLong(2, id);

	    rs = ps.executeQuery();

	    while (rs.next()) {
		iden = new Iden();
		iden.setId(rs.getInt("id"));
		iden.setIdP(rs.getLong("project_id"));
		iden.setNombre(rs.getString("nombre"));
		iden.setDescripcion(rs.getString("descripcion"));
		iden.setResponsable(rs.getString("responsable"));
		iden.setProbabilidad(rs.getInt("probabilidad"));
		iden.setImpacto(rs.getString("valorImpacto"));
		iden.setValor(rs.getDouble("vImpacto"));
		iden.setResponse(rs.getString("response"));
		iden.setNotes(rs.getString("notes"));
		iden.setCambio(getCambio(0L, rs.getLong("iden_id")));
		iden.setVersion(rs.getDouble("version"));
		vIden.add(iden);
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
	return vIden;
    }

    /**
     * Setter para establecer los datos de la respuesta de riesgos
     * 
     * @param resp
     *            datos de la respuesta de riesgos
     * @throws Exception
     */
    @Override
    public void setRespuesta(Respuesta resp) throws Exception {
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;

	try {
	    con = Jdbc.getConnection();

	    if (uniqueRespuesta(ps, con, rs, resp.getIdU(), resp.getIdP(),
		    resp.getOption())) {
		insertResp(resp, ps, con);
	    } else {
		ps = con.prepareStatement("DELETE FROM analisis WHERE project_id=? and u_id=? and opcion=?;");
		ps.setLong(1, resp.getIdP());
		ps.setLong(2, resp.getIdU());
		ps.setLong(3, resp.getOption());
		ps.executeUpdate();
		insertResp(resp, ps, con);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw (e);
	} finally {
	    try {
		if (ps != null)
		    ps.close();
		con.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Método para insertar los datos de las respuestas de los riesgos
     * 
     * @param resp
     *            información sobre la respuesta
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @throws SQLException
     * @throws ParseException
     */
    private void insertResp(Respuesta resp, PreparedStatement ps, Connection con)
	    throws SQLException, ParseException {
	ps = con.prepareStatement("INSERT INTO analisis (project_id,u_id,id,opcion,nombre,descripcion,categoria,status,causas,probabilidad,impacto,valor,response,fechaRevision,probabilidadRevisada,impactoRevisado,valorRevisado,responseRevisado,derivado,residual,contingencia,presupuesto,planificacion,comentarios,monitorizacion,indicador,evaluacion,created) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
	ps.setLong(1, resp.getIdP());
	ps.setLong(2, resp.getIdU());
	ps.setInt(3, resp.getId());
	ps.setInt(4, resp.getOption());
	ps.setString(5, resp.getNombre());
	ps.setString(6, resp.getDescripcion());
	ps.setString(7, resp.getCategoria());
	ps.setString(8, resp.getStatus());
	ps.setString(9, resp.getCausas());
	ps.setInt(10, resp.getProbabilidad());
	ps.setString(11, resp.getImpacto());
	ps.setDouble(12, resp.getValor());
	ps.setString(13, resp.getResponse());

	if (resp.getFechaRevisada().compareTo("") != 0) {
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	    java.util.Date date = sdf1.parse(resp.getFechaRevisada());
	    java.sql.Date sqlDate = new Date(date.getTime());
	    ps.setDate(14, sqlDate);
	} else {
	    ps.setDate(14, null);
	}

	ps.setInt(15, resp.getProbabilidadRevisada());
	ps.setString(16, resp.getImpactoRevisado());
	ps.setDouble(17, resp.getValorRevisado());
	ps.setString(18, resp.getResponseRevisado());

	ps.setString(19, resp.getDerivado());
	ps.setString(20, resp.getResidual());
	ps.setString(21, resp.getContingencia());
	ps.setString(22, resp.getPresupuesto());
	ps.setString(23, resp.getPlanificacion());
	ps.setString(24, resp.getComentarios());
	ps.setString(25, resp.getMonitorizacion());
	ps.setString(26, resp.getIndicador());
	ps.setString(27, resp.getEvaluacion());
	ps.setTimestamp(28, null);

	ps.executeUpdate();
    }

    /**
     * Método para confirmación de existencia de mismas respuestas de riesgo es
     * único o no
     * 
     * @param ps
     *            declaración de la qs
     * @param con
     *            conexión
     * @param rs
     *            resultado
     * @param idU
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return valor booleano si true si es único false si no
     * @throws SQLException
     */
    private boolean uniqueRespuesta(PreparedStatement ps, Connection con,
	    ResultSet rs, Long idU, Long idP, Integer option)
	    throws SQLException {
	boolean vacio = true;
	ps = con.prepareStatement("select anal_id from analisis where project_id=? and u_id=? and opcion=?;");
	ps.setLong(1, idP);
	ps.setLong(2, idU);
	ps.setInt(3, option);
	rs = ps.executeQuery();
	while (rs.next()) {
	    vacio = false;
	}
	return vacio;
    }

    /**
     * Getter para la obtención de las respuestas de los riesgos
     * 
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return Respuesta datos de la respuesta de los riesgos
     * @throws Exception
     */
    @Override
    public Respuesta getResp(Long id, Long idP, Integer op) throws Exception {
	Respuesta resp = new Respuesta();

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	try {
	    con = Jdbc.getConnection();

	    ps = con.prepareStatement("select id,project_id,opcion,nombre,descripcion,categoria,status,causas,probabilidad,impacto,valor,response,fechaRevision,probabilidadRevisada,impactoRevisado,valorRevisado,responseRevisado,derivado,residual,contingencia,presupuesto,planificacion,comentarios,monitorizacion,indicador,evaluacion from analisis where project_id=? and u_id=? and opcion=?;");
	    ps.setLong(1, idP);
	    ps.setLong(2, id);
	    ps.setLong(3, op);

	    rs = ps.executeQuery();

	    while (rs.next()) {
		resp.setOption(rs.getInt("opcion"));
		resp.setId(rs.getInt("id"));
		resp.setIdP(rs.getLong("project_id"));
		resp.setNombre(rs.getString("nombre"));
		resp.setDescripcion(rs.getString("descripcion"));
		resp.setCategoria(rs.getString("categoria"));
		resp.setStatus(rs.getString("status"));
		resp.setCausas(rs.getString("causas"));
		resp.setProbabilidad(rs.getInt("probabilidad"));
		resp.setImpacto(rs.getString("impacto"));
		resp.setValor(rs.getDouble("valor"));
		resp.setResponse(rs.getString("response"));
		String f = "";
		if (rs.getDate("fechaRevision") != null) {
		    Date fecha = rs.getDate("fechaRevision");
		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			    "dd/MM/yyyy");
		    f = sdf.format(fecha);
		}
		resp.setFechaRevisada(f);
		resp.setProbabilidadRevisada(rs.getInt("probabilidadRevisada"));
		resp.setImpactoRevisado(rs.getString("impactoRevisado"));
		resp.setValorRevisado(rs.getDouble("valorRevisado"));
		resp.setResponseRevisado(rs.getString("responseRevisado"));
		resp.setDerivado(rs.getString("derivado"));
		resp.setResidual(rs.getString("residual"));
		resp.setContingencia(rs.getString("contingencia"));
		resp.setPresupuesto(rs.getString("presupuesto"));
		resp.setPlanificacion(rs.getString("planificacion"));
		resp.setComentarios(rs.getString("comentarios"));
		resp.setMonitorizacion(rs.getString("monitorizacion"));
		resp.setIndicador(rs.getString("indicador"));
		resp.setEvaluacion(rs.getString("evaluacion"));
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
	return resp;
    }

    /**
     * Getter para la obtención de las versiones de los plnaes de versiones
     * 
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return Vector vector con las diferentes versiones
     * @throws Exception
     */
    @Override
    public Vector<Double> getVersion(Long id, Long idP) throws Exception {
	Vector<Double> version = new Vector<Double>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	con = Jdbc.getConnection();
	ps = con.prepareStatement("select version from plans where u_id=? and project_id=? ORDER BY created DESC;");
	ps.setLong(1, id);
	ps.setLong(2, idP);

	rs = ps.executeQuery();

	while (rs.next()) {
	    version.add(rs.getDouble("version"));
	}
	return version;
    }

    /**
     * Getter para la obtención de las versiones de la identificación de riesgos
     * 
     * @param id
     *            identificación del usuario
     * @param idP
     *            identificación del proyecto
     * @return Vector vector con las diferentes versiones
     * @throws Exception
     */
    @Override
    public Vector<Double> getVersionIden(Long id, Long idP) throws Exception {
	Vector<Double> version = new Vector<Double>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;

	con = Jdbc.getConnection();
	ps = con.prepareStatement("select version from idens where u_id=? and project_id=? ORDER BY created DESC;");
	ps.setLong(1, id);
	ps.setLong(2, idP);

	rs = ps.executeQuery();

	while (rs.next()) {
	    version.add(rs.getDouble("version"));
	}
	return version;
    }
}
