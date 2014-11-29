package com.miw.junit;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.miw.model.exception.BusinessException;

/**
 * Clase de Test para probar la creación de ficheros pdf
 * 
 * @author Pablo
 * 
 */
public class CreatePDFTest {

    /**
     * Método para inicilaizar variables
     * 
     * @throws BusinessException
     */
    @Before
    public void setUp() throws BusinessException {
	CreatePDFTest p = new CreatePDFTest();
	Document document = new Document();
	p.ITextHelloWorld(document);
    }

    /**
     * Método de Test para generar diferentes tipos de documentos pdf
     * 
     * @param document
     */
    @Test
    public void ITextHelloWorld(Document document) {
	Calendar cal = Calendar.getInstance();
	Date fecha = new Date(cal.getTimeInMillis());
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	String parrafo = "Usaremos este parrafo como ejemplo de lo sencillo que es el uso de la libreria, pues como podria observar en el codigo esta cadena no lleva saltos de linea, mismos que la libreria agrego.";

	String parrafo2 = "A su vez podemos agregar datos con una simple concatenación, como ejemplo ponemos la fecha del dia en el formato deseado: "
		+ formato.format(fecha)
		+ " y como podemos ver tambien hacer uso de las diversas funciones de formateo de fecha y numeros, incluso formatos personalidades.";

	try {
	    PdfWriter.getInstance(document, new FileOutputStream("prueba.pdf"));
	    document.open();
	    assertTrue(document.add(new Paragraph(parrafo)));
	    assertTrue(document.add(new Paragraph(parrafo2)));
	    assertTrue(document.add(tablaNormal()));
	    assertTrue(document.add(tablaColor()));
	    assertTrue(tablaAlineacion(document));
	    document.close();
	    assertTrue(true);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (DocumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Método para probar la generación de tablas
     * 
     * @return PdfPTable con una tabla simple
     * @throws Exception
     */
    public PdfPTable tablaNormal() throws Exception {
	PdfPTable table = new PdfPTable(3);
	PdfPCell cell = new PdfPCell(new Paragraph("header"));

	table.addCell(cell);
	table.addCell("1.1");
	table.addCell("2.1");
	table.addCell("3.1");
	table.addCell("1.2");
	table.addCell("2.2");
	table.addCell("3.2");
	return table;
    }

    /**
     * Método para probar la generación de tablas y el cambio de color de las
     * celdas
     * 
     * @return PdfPTable con una tabla simple con celda con fondo amarillo
     * @throws Exception
     */
    public PdfPTable tablaColor() throws Exception {
	float[] widths = { 1, 4 };
	PdfPTable table = new PdfPTable(widths);
	table.setWidthPercentage(30);
	PdfPCell cell = new PdfPCell(new Paragraph("fox"));
	cell.setBackgroundColor(BaseColor.YELLOW);
	table.addCell(cell);
	table.addCell("asdf");
	return table;
    }

    /**
     * Método para probar la generación de tablas alineadas
     * 
     * @return Boolean si se ha creado correctamente una tabla alineada
     * 
     * @throws Exception
     */
    public boolean tablaAlineacion(Document document) throws Exception {

	PdfPTable table = new PdfPTable(3);
	PdfPCell cell = new PdfPCell(new Paragraph("header with colspan 3"));
	cell.setColspan(3);
	table.addCell(cell);
	table.addCell("1");
	table.addCell("2");
	table.addCell("3");
	table.setWidthPercentage(100);
	document.add(table);
	table.setWidthPercentage(50);
	table.setHorizontalAlignment(Element.ALIGN_RIGHT);
	document.add(table);
	table.setHorizontalAlignment(Element.ALIGN_LEFT);
	document.add(table);

	return true;
    }
}