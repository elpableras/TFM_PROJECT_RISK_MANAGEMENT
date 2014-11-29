package com.miw.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Clase para producir Tests de JUnit para probar diferentes partes de la
 * aplicación
 * 
 * @author Pablo
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ AssociationsTest.class, PersistenceTest.class,
	CreatePDFTest.class })
public class AllTests {
}
