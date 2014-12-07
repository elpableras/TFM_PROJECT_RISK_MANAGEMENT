package com.miw.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import impl.miw.persistence.info.InfoDAO;
import impl.miw.persistence.project.ProjectDAO;
import impl.miw.persistence.user.UserDAO;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.miw.model.Cambio;
import com.miw.model.Iden;
import com.miw.model.Impacto;
import com.miw.model.Info;
import com.miw.model.Probabilidad;
import com.miw.model.Project;
import com.miw.model.Respuesta;
import com.miw.model.User;
import com.miw.model.exception.BusinessException;

/**
 * Clase para probar la persistencia
 * 
 * @author Pablo
 * 
 */
public class PersistenceTest {

    private User usuario;
    private Project proyecto;
    private Info info;
    private Probabilidad probabilidad;
    private Vector<Probabilidad> vp;
    private Impacto impacto;
    private Vector<Impacto> vi;
    private Cambio cambio;
    private Vector<Cambio> vc;
    private Iden iden;
    private Respuesta resp;
    private final UserDAO userS = new UserDAO();
    private final ProjectDAO projectS = new ProjectDAO();
    private final InfoDAO infoS = new InfoDAO();

    /**
     * Método setUp para inicilizar variables
     * 
     * @throws BusinessException
     */
    @Before
    public void setUp() throws BusinessException {
	// User
	usuario = new User();
	usuario.setLogin("usuario");
	usuario.setEmail("manager@manager.com");
	usuario.setPassword("123456");
	usuario.setAdmin(false);
	usuario.setManager(false);
	usuario.setLanguage("ES");
	usuario.setIdProyecto(1L);

	// Project
	proyecto = new Project();
	proyecto.setNombre("proyecto");
	proyecto.setManager("Manager");
	proyecto.setEmail("manager@manager.com");
	proyecto.setFecha("12/12/2012");
	proyecto.setPaso(3);

	// Plan
	info = new Info();
	info.setIdP(0L);
	info.setIdU(0L);
	info.setTitulo("Titulo");
	info.setFecha("12/12/2020");
	info.setNombre("Plan1");
	info.setMetodologia("Meto");
	info.setHtecno("Htecno");
	info.setRoles("Roles");
	info.setPresu("Presu");
	info.setCalendar("Calendar");
	info.setRiesgo("Riesgo");
	vc = new Vector<Cambio>();
	cambio = new Cambio();
	cambio.setFecha("12/12/2011");
	cambio.setId(0L);
	cambio.setPlan("Plan");
	cambio.setVersion(1.0000);
	vc.add(cambio);
	info.setCambio(vc);

	probabilidad = new Probabilidad();
	probabilidad.setDescripcion("Descrip");
	probabilidad.setPorcentaje(90);
	probabilidad.setProbabilidad("Muy ALta");
	vp = new Vector<Probabilidad>();
	vp.add(probabilidad);
	info.setProbabilidad(vp);
	vi = new Vector<Impacto>();
	impacto = new Impacto();
	impacto.setObjetivo("Objectivo");
	impacto.setProbabilidad(vp);
	vi.add(impacto);
	info.setImpacto(vi);
	info.setRango("0.4,0.5");
	info.setContingencia("Conti");
	info.setFormato("Formato");
	info.setCorte(0.0);

	// Iden
	iden = new Iden();
	iden.setIdU(0L);
	iden.setIdP(0L);
	iden.setId(0);
	iden.setNombre("Iden");
	iden.setDescripcion("Descri Iden");
	iden.setResponsable("Responsable");
	iden.setProbabilidad(90);
	iden.setImpacto("10, 20, 30, 40");
	iden.setValor(0.12);
	iden.setResponse("Response");
	iden.setNotes("Notes");
	iden.setVersion(1.0000);
	iden.setCambio(vc);

	// Respuesta
	resp = new Respuesta();
	resp.setIdU(0L);
	resp.setIdP(0L);
	resp.setOption(0);
	resp.setId(0);
	resp.setNombre("resp");
	resp.setDescripcion("Descripcion");
	resp.setCategoria("Categoria");
	resp.setStatus("Status");
	resp.setCausas("Causas");
	resp.setProbabilidad(90);
	resp.setImpacto("10, 20, 30, 40");
	resp.setValor(0.23);
	resp.setResponse("Response");
	resp.setFechaRevisada("21-12-2014");
	resp.setProbabilidadRevisada(80);
	resp.setImpactoRevisado("10, 20, 30, 40");
	resp.setValorRevisado(0.63);
	resp.setResponseRevisado("ResponseRevisado");
	resp.setDerivado("Derivados");
	resp.setResidual("Residual");
	resp.setContingencia("Contingencia");
	resp.setPresupuesto("Presupuesto");
	resp.setPlanificacion("Planificacion");
	resp.setComentarios("Comentarios");
	resp.setMonitorizacion("Monitorizacion");
	resp.setIndicador("1@2@3");
	resp.setEvaluacion("evaluacion@evaluacion@evaluacion");
    }

    /**
     * Método de Test para Usuario
     * 
     * @throws Exception
     */
    @Test
    public void testUsuario() throws Exception {

	assertEquals(userS.setUser(usuario), "Datos tratados correctamente");
	Vector<User> users = userS.getUsers();

	for (int i = 0; i < users.size(); i++) {
	    if (usuario.getEmail().equals(users.get(i).getEmail())
		    && usuario.getPassword().equals(users.get(i).getPassword())) {
		assertEquals(users.get(i).getIdProyecto(), "1L");
		assertEquals(users.get(i).getLanguage(), "ES");
		assertEquals(users.get(i).getLogin(), "usuario");
		break;
	    }
	}
    }

    /**
     * Método de Test para Proyecto
     * 
     * @throws Exception
     */
    @Test
    public void testProyecto() throws Exception {

	assertEquals(projectS.setProject(proyecto),
		"Datos Guardados Correctamente");

	assertEquals(projectS.getProject(usuario),
		"Datos Guardados Correctamente");

	assertSame(projectS.getProject(usuario), proyecto);
	assertEquals(projectS.getProject(usuario).getNombre(), "proyecto");
	assertEquals(projectS.getProject(usuario).getManager(), "Manager");
	assertEquals(projectS.getProject(usuario).getEmail(),
		"manager@manager.com");
	assertEquals(projectS.getProject(usuario).getFecha(), "12/12/2012");

    }

    /**
     * Método de Test para Planes de Riesgos
     * 
     * @throws Exception
     */
    @Test
    public void testInfo() throws Exception {

	infoS.setInfo(info.getIdU(), info.getIdP(), info.getVersion(), info);

	assertSame(
		infoS.getInfo(info.getIdU(), info.getIdP(), info.getVersion()),
		info);
	assertEquals(
		infoS.getInfo(info.getIdU(), info.getIdP(), info.getVersion())
			.getCalendar(), "Calendar");
	assertEquals(
		infoS.getInfo(info.getIdU(), info.getIdP(), info.getVersion())
			.getTitulo(), "Titulo");
	assertTrue(infoS
		.getInfo(info.getIdU(), info.getIdP(), info.getVersion())
		.getCambio().contains(cambio));

	assertTrue(infoS
		.getInfo(info.getIdU(), info.getIdP(), info.getVersion())
		.getProbabilidad().size() == 1);

	for (int i = 0; i < infoS
		.getInfo(info.getIdU(), info.getIdP(), info.getVersion())
		.getImpacto().size(); i++) {
	    assertTrue(infoS
		    .getInfo(info.getIdU(), info.getIdP(), info.getVersion())
		    .getImpacto().get(i).getProbabilidad() == vp);
	}
    }

    /**
     * Método de Test para Riesgos Identificados
     * 
     * @throws Exception
     */
    @Test
    public void testIden() throws Exception {

	Vector<Iden> vIden = new Vector<Iden>();
	vIden.add(iden);
	infoS.setIden(vIden);

	assertTrue(infoS.getIden(info.getIdU(), info.getIdP()).size() > 0);
	assertSame(infoS.getIden(info.getIdU(), info.getIdP()), iden);

	for (int i = 0; i < infoS.getIden(info.getIdU(), info.getIdP()).size(); i++) {
	    assertEquals(infoS.getIden(info.getIdU(), info.getIdP()).get(i)
		    .getDescripcion(), "Descri Iden");
	    assertEquals(infoS.getIden(info.getIdU(), info.getIdP()).get(i)
		    .getNotes(), "Notes");
	    assertEquals(infoS.getIden(info.getIdU(), info.getIdP()).get(i)
		    .getResponse(), "Response");
	    assertTrue(infoS.getIden(info.getIdU(), info.getIdP()).get(i)
		    .getCambio().size() > 0);
	    assertTrue(infoS.getIden(info.getIdU(), info.getIdP()).get(i)
		    .getCambio().contains(cambio));
	}
    }

    /**
     * Método de Test para Respuesta de Riesgos
     * 
     * @throws Exception
     */
    @Test
    public void testResp() throws Exception {

	infoS.setRespuesta(resp);

	assertSame(
		infoS.getResp(resp.getIdU(), resp.getIdP(), resp.getOption()),
		resp);

	assertSame(infoS
		.getResp(resp.getIdU(), resp.getIdP(), resp.getOption())
		.getCategoria(), "Categoria");

	assertSame(infoS
		.getResp(resp.getIdU(), resp.getIdP(), resp.getOption())
		.getComentarios(), "Comentarios");

	assertTrue(infoS
		.getResp(resp.getIdU(), resp.getIdP(), resp.getOption())
		.getProbabilidad() == 90);

	assertTrue(infoS
		.getResp(resp.getIdU(), resp.getIdP(), resp.getOption())
		.getImpacto().split(", ").length == 4);

	assertTrue(infoS
		.getResp(resp.getIdU(), resp.getIdP(), resp.getOption())
		.getIndicador().split("@").length == 3);
    }
}
