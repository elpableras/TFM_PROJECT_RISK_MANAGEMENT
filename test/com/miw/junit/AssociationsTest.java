package com.miw.junit;

import static org.junit.Assert.assertTrue;

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
 * Clase para probar diferentes asociaciones dentro de cada módelo
 * 
 * @author Pablo
 * 
 */
public class AssociationsTest {

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

    /**
     * Método para inicializar variables
     * 
     * @throws BusinessException
     */
    @Before
    public void setUp() throws BusinessException {
	// User
	usuario = new User();
	usuario.setId(2L);
	usuario.setLogin("usuario");
	usuario.setEmail("0000@0000.com");
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
	resp.setNombre("Resp");
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
     * Método de Test para añadir posesión
     * 
     * @throws BusinessException
     */
    @Test
    public void testPoseerAdd() throws BusinessException {
	assertTrue(info.getProbabilidad().contains(probabilidad));
	assertTrue(info.getProbabilidad() == vp);
	assertTrue(!info.getProbabilidad().isEmpty());
	assertTrue(info.getProbabilidad().size() > 0);

	assertTrue(info.getImpacto().contains(impacto));
	assertTrue(info.getImpacto() == vi);
	assertTrue(!info.getImpacto().isEmpty());
	assertTrue(info.getImpacto().size() > 0);

	assertTrue(info.getCambio().contains(cambio));
	assertTrue(info.getCambio() == vc);
	assertTrue(!info.getCambio().isEmpty());
	assertTrue(info.getCambio().size() > 0);

	assertTrue(iden.getCambio().contains(cambio));
	assertTrue(iden.getCambio() == vc);
	assertTrue(!iden.getCambio().isEmpty());
	assertTrue(iden.getCambio().size() > 0);

    }

    /**
     * Método de Test eliminación de posesión
     * 
     * @throws BusinessException
     */
    @Test
    public void testPoseerRemove() throws BusinessException {
	info.getProbabilidad().remove(probabilidad);
	assertTrue(!info.getProbabilidad().contains(probabilidad));
	assertTrue(info.getProbabilidad().isEmpty());
	assertTrue(info.getProbabilidad().size() == 0);

	info.getImpacto().remove(impacto);
	assertTrue(!info.getImpacto().contains(impacto));
	assertTrue(info.getImpacto().isEmpty());
	assertTrue(info.getImpacto().size() == 0);

	info.getCambio().remove(cambio);
	assertTrue(!info.getCambio().contains(cambio));
	assertTrue(info.getCambio().isEmpty());
	assertTrue(info.getCambio().size() == 0);

	iden.getCambio().remove(cambio);
	assertTrue(!iden.getCambio().contains(cambio));
	assertTrue(iden.getCambio().isEmpty());
	assertTrue(iden.getCambio().size() == 0);
    }

    /**
     * Método para agregar pertenencias dentro de otro ya agregado
     * 
     * @throws BusinessException
     */
    @Test
    public void testTenerAdd() throws BusinessException {
	for (int i = 0; i < info.getImpacto().size(); i++) {
	    assertTrue(info.getImpacto().get(i).getProbabilidad()
		    .contains(probabilidad));
	    assertTrue(info.getImpacto().get(i).getProbabilidad() == vp);
	    assertTrue(!info.getImpacto().get(i).getProbabilidad().isEmpty());
	    assertTrue(info.getImpacto().get(i).getProbabilidad().size() > 0);
	}
    }

    /**
     * Método para eliminar pertenencias dentro de otro ya agregado
     * 
     * @throws BusinessException
     */
    @Test
    public void testTenerRemove() throws BusinessException {
	for (int i = 0; i < info.getImpacto().size(); i++) {
	    info.getImpacto().get(i).getProbabilidad().remove(probabilidad);
	    assertTrue(!info.getImpacto().get(i).getProbabilidad()
		    .contains(probabilidad));
	    assertTrue(info.getImpacto().get(i).getProbabilidad().isEmpty());
	    assertTrue(info.getImpacto().get(i).getProbabilidad().size() == 0);
	}
    }
}
