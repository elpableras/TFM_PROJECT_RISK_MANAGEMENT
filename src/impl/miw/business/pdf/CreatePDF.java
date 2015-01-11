package impl.miw.business.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.miw.model.Iden;
import com.miw.model.Impacto;
import com.miw.model.Info;
import com.miw.model.Probabilidad;
import com.miw.model.Project;
import com.miw.model.Respuesta;
import com.miw.model.User;

/**
 * Clase de la capa de negocio para la acción de creación de pdfs según los
 * datos facilitados y extraídos desde la bbdd de la aplicación, para la
 * generación de los informes de los mismos.
 * 
 * @author Pablo
 * 
 */
public class CreatePDF extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String nProyecto;
    private String fecha;
    private String lenguaje;
    private String version;
    private String autor;
    private String titulo;
    private Info info = null;
    private Vector<Iden> iden = null;
    private int colIden;
    private Vector<Impacto> impactos = null;
    private Vector<Probabilidad> probabilidad = null;
    private String corte;
    private Respuesta resp = null;
    private String file;
    private HttpServletResponse response;
    

    /**
     * Método principal para la creación de los documentos pdf para la
     * aplicación
     * 
     * @return String con la localización del path donde se almacena el pdf
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf() throws DocumentException,
	    IOException {
	String archivo = createPath(file);
	String lengua = "";
	lengua = "_" + lenguaje;

	if (archivo.compareToIgnoreCase("") != 0) {
	    /*
	     * Declaramos documento como un objeto Document. Asignamos el tamaño
	     * de hoja y los margenes
	     */
	    Document documento = null;
	    if (info != null) {
		documento = new Document(PageSize.A4, 80, 80, 75, 75);
	    } else if (iden != null) {
		documento = new Document(PageSize.A4.rotate(), 80, 80, 75, 75);
	    } else if (resp != null) {
		documento = new Document(PageSize.A4, 80, 80, 75, 75);
	    }

	    // writer es declarado como el método utilizado para escribir en el
	    // archivo
	    PdfWriter writer = null;

	    // Obtenemos la instancia del archivo a utilizar y lo creamos en memoria
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    writer = PdfWriter.getInstance(documento, baos);
	    //writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
	    HeaderFooter event = new HeaderFooter(lengua, autor, version);
	    writer.setPageEvent(event);

	    // Agregamos un titulo al archivo
	    documento.addTitle(titulo);

	    // Agregamos el autor del archivo
	    documento.addAuthor(autor);

	    // Abrimos el documento para edición
	    documento.open();

	    // DOCUMENTO CON LA INFORMACIÓN

	    // Declaramos un texto como Paragraph
	    // Le podemos dar formado como alineación, tamaño y color a la
	    // fuente.
	    Paragraph titulo = new Paragraph();
	    titulo.setAlignment(Paragraph.ALIGN_CENTER);
	    titulo.setFont(FontFactory.getFont("Sans", 18, Font.BOLD,
		    BaseColor.BLACK));

	    titulo.add(ResourceBundle.getBundle("global" + lengua).getString(
		    "pdf.titulo"));

	    documento.add(new Paragraph(" "));
	    documento.add(titulo);

	    Paragraph parrafo = new Paragraph();
	    parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
	    parrafo.setFont(FontFactory.getFont("Sans", 10, Font.NORMAL,
		    BaseColor.BLUE));

	    Paragraph parrafoH1 = new Paragraph();
	    parrafoH1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
	    parrafoH1.setFont(FontFactory.getFont("Sans", 14, Font.BOLD,
		    BaseColor.BLACK));

	    // ********** PLAN DE RIESGOS **********

	    if (info != null) {
		parrafoH1.add("1. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.cambio") + "\n");
		documento.add(parrafoH1);
		for (int i = 0; i < info.getCambio().size(); i++) {
		    parrafo.add(info.getCambio().get(i).getId().toString()
			    + " "
			    + info.getCambio().get(i).getPlan().toString()
			    + " "
			    + info.getCambio().get(i).getFecha().toString()
			    + "\n");
		}
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("2. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.metodologia") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getMetodologia() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("3. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.herra") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getHtecno() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("4. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.roles") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getRoles() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("5. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.presu") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getPresu() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("6. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.calendar") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getCalendar() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("7. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.categoria") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getRiesgo() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("8. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.probabilidad") + "\n");
		documento.add(parrafoH1);
		documento.add(new Paragraph(" "));
		documento.add(tablaPlanProbabilidad(lengua, titulo));

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("9. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.objetivos") + "\n");
		documento.add(parrafoH1);
		documento.add(new Paragraph(" "));
		documento.add(tablaPlanImpacto(lengua, titulo));

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("10. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.matrix") + "\n");
		documento.add(parrafoH1);
		documento.add(new Paragraph(" "));
		documento.add(tablaPlanMatrix(lengua));

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("11. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.contingencia") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getContingencia() + "\n");
		documento.add(parrafo);

		documento.add(new Paragraph(" "));
		parrafoH1.clear();
		parrafoH1.add("12. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.formato") + "\n");
		documento.add(parrafoH1);
		parrafo.clear();
		parrafo.add(info.getFormato() + "\n");
		documento.add(parrafo);

	    }

	    // ********** IDENTIFICAIÓN DE RIESGOS **********
	    if (iden != null) {
		documento.add(new Paragraph(" "));

		parrafoH1.add("1. "
			+ ResourceBundle.getBundle("global" + lengua)
				.getString("pdf.cambio") + "\n");
		documento.add(parrafoH1);
		for (int j = 0; j < iden.size(); j++) {
		    for (int i = 0; i < iden.get(j).getCambio().size(); i++) {
			parrafo.add(iden.get(j).getCambio().get(i).getId()
				.toString()
				+ " "
				+ iden.get(j).getCambio().get(i).getPlan()
					.toString()
				+ " "
				+ iden.get(j).getCambio().get(i).getFecha()
					.toString() + "\n");
		    }
		    documento.add(parrafo);
		}

		documento.add(new Paragraph(" "));

		documento.add(tablaIden(lengua, parrafo, titulo));
	    }

	    // ********** RESPUESTA DE RIESGOS **********
	    if (resp != null) {
		documento.add(new Paragraph(" "));
		documento.add(tablaResp(lengua, parrafo, titulo));
	    }

	    documento.close(); // Cerramos el documento

	    // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // Download
            response.setHeader("Content-disposition", "attachment; filename=\""+archivo+"\"");
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();           
	}
    }

    /**
     * Método para crear la tabla para las respuestas de riesgos
     * 
     * @param lengua
     *            String para saber el idioma en que se escribira el contenido
     * @param parrafo
     *            Paragraph que define estilos del parrafo
     * @param titulo
     *            Paragraph que define estilos del parrafo de los títulos
     * @return PdfPTable con la tabla que se insertará en el documento pdf
     */
    @SuppressWarnings("static-access")
    private PdfPTable tablaResp(String lengua, Paragraph parrafo,
	    Paragraph titulo) throws DocumentException {

	Paragraph parrafoTitulo = new Paragraph();
	parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
	parrafoTitulo.setFont(FontFactory.getFont("Sans", 12, Font.BOLD,
		BaseColor.BLACK));

	int cols = 3 + this.colIden;
	PdfPTable tabla = new PdfPTable(cols);
	float[] widths = new float[cols];
	for (int i = 0; i < widths.length; i++) {
	    if (i == 0) {
		widths[i] = 0.6F;
	    } else if (i == widths.length - 1) {
		widths[i] = 0.5F;
	    } else {
		widths[i] = 0.4F;
	    }
	}
	tabla.setTotalWidth(PageSize.A5.getWidth());
	tabla.setWidths(widths);
	tabla.setLockedWidth(true);

	// Declaramos un objeto para manejar las celdas
	PdfPCell celda;

	titulo.clear();
	titulo.add(ResourceBundle.getBundle("global" + lengua).getString(
		"pdf.resp"));
	celda = new PdfPCell(new Phrase(titulo));
	celda.setColspan(cols);
	celda.setHorizontalAlignment(titulo.ALIGN_CENTER);
	celda.setVerticalAlignment(titulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	Paragraph comb = new Paragraph();

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.id") + ": ");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getId()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.nombre") + ": \n");
	parrafo.clear();
	parrafo.add(resp.getNombre());
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(comb.ALIGN_LEFT);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.descripcion"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(resp.getDescripcion());
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(parrafo.ALIGN_LEFT);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.categoria"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(resp.getCategoria());
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(parrafo.ALIGN_LEFT);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.status") + ": ");
	parrafo.clear();
	parrafo.add(resp.getStatus());
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.causas") + ": \n");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getCausas()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(comb.ALIGN_LEFT);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.probabilidad"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	for (int i = 0; i < this.colIden; i++) {
	    parrafoTitulo.clear();
	    parrafoTitulo.add(impactos.get(i).getObjetivo());
	    celda = new PdfPCell(new Phrase(parrafoTitulo));
	    celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.impactoValor"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.response"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	for (int j = 0; j < probabilidad.size(); j++) {
	    if (resp.getProbabilidad() == probabilidad.get(j).getPorcentaje()) {
		parrafo.add(probabilidad.get(j).getProbabilidad());
	    }
	}
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	String[] impts = resp.getImpacto().split(" ");
	for (int k = 0; k < impts.length; k++) {
	    parrafo.clear();
	    for (int j = 0; j < impactos.get(0).getProbabilidad().size(); j++) {
		if (Integer.parseInt(impts[k]) == impactos.get(0)
			.getProbabilidad().get(j).getPorcentaje()) {
		    parrafo.add(impactos.get(0).getProbabilidad().get(j)
			    .getProbabilidad());
		    break;
		}
	    }
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}

	parrafo.clear();
	parrafo.add(String.valueOf(resp.getValor()));
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(String.valueOf(resp.getResponse()));
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.probabilidad2") + "\n");
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.fecha") + ": ");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getFechaRevisada()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	for (int i = 0; i < this.colIden; i++) {
	    parrafoTitulo.clear();
	    parrafoTitulo.add(impactos.get(i).getObjetivo());
	    celda = new PdfPCell(new Phrase(parrafoTitulo));
	    celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.impactoValor"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.response"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	for (int j = 0; j < probabilidad.size(); j++) {
	    if (resp.getProbabilidadRevisada() == probabilidad.get(j)
		    .getPorcentaje()) {
		parrafo.add(probabilidad.get(j).getProbabilidad());
	    }
	}
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	String[] impts2 = resp.getImpactoRevisado().split(" ");
	for (int k = 0; k < impts.length; k++) {
	    parrafo.clear();
	    for (int j = 0; j < impactos.get(0).getProbabilidad().size(); j++) {
		if (Integer.parseInt(impts2[k]) == impactos.get(0)
			.getProbabilidad().get(j).getPorcentaje()) {
		    parrafo.add(impactos.get(0).getProbabilidad().get(j)
			    .getProbabilidad());
		    break;
		}
	    }
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}

	parrafo.clear();
	parrafo.add(String.valueOf(resp.getValorRevisado()));
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(String.valueOf(resp.getResponseRevisado()));
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.derivados"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(resp.getDerivado());
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(parrafo.ALIGN_LEFT);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.residual"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(resp.getResidual());
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(parrafo.ALIGN_LEFT);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.contingencia") + ": \n");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getContingencia()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setColspan((this.colIden / 2) + 1);
	celda.setRowspan(2);
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.presupuesto") + ": \n");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getPresupuesto()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setColspan((this.colIden / 2) + 2);
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.planificacion") + ": \n");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getPlanificacion()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setColspan((this.colIden / 2) + 2);
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.comentarios") + ": ");
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafo.clear();
	parrafo.add(resp.getComentarios());
	celda = new PdfPCell(new Phrase(parrafo));
	celda.setColspan(this.colIden + 2);
	celda.setHorizontalAlignment(parrafo.ALIGN_LEFT);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.monitorizacion") + ": \n");
	parrafo.clear();
	parrafo.add(String.valueOf(resp.getMonitorizacion()));
	comb.clear();
	comb.add(parrafoTitulo);
	comb.add(parrafo);
	celda = new PdfPCell(new Phrase(comb));
	celda.setColspan(this.colIden + 3);
	celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("resp.indicadores"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setColspan(this.colIden + 3);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	String[] indi = resp.getIndicador().split("@");
	String[] eval = resp.getEvaluacion().split("@");
	for (int j = 0; j < indi.length; j++) {
	    parrafoTitulo.clear();
	    parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		    .getString("resp.indicador") + ": ");
	    parrafo.clear();
	    parrafo.add(indi[j]);
	    comb.clear();
	    comb.add(parrafoTitulo);
	    comb.add(parrafo);
	    celda = new PdfPCell(new Phrase(comb));
	    celda.setHorizontalAlignment(comb.ALIGN_CENTER);
	    celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    parrafoTitulo.clear();
	    parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		    .getString("resp.evaluacion") + ": \n");
	    parrafo.clear();
	    parrafo.add(eval[j]);
	    comb.clear();
	    comb.add(parrafoTitulo);
	    comb.add(parrafo);
	    celda = new PdfPCell(new Phrase(comb));
	    celda.setColspan(this.colIden + 2);
	    celda.setHorizontalAlignment(comb.ALIGN_LEFT);
	    celda.setVerticalAlignment(comb.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}

	return tabla;
    }

    /**
     * Método para crear la tabla para los riesgos identificados
     * 
     * @param lengua
     *            String para saber el idioma en que se escribira el contenido
     * @param parrafo
     *            Paragraph que define estilos del parrafo
     * @param titulo
     *            Paragraph que define estilos del parrafo de los títulos
     * @return PdfPTable con la tabla que se insertará en el documento pdf
     */
    @SuppressWarnings("static-access")
    private PdfPTable tablaIden(String lengua, Paragraph parrafo,
	    Paragraph titulo) throws DocumentException {

	Paragraph parrafoTitulo = new Paragraph();
	parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
	parrafoTitulo.setFont(FontFactory.getFont("Sans", 12, Font.BOLD,
		BaseColor.BLACK));

	int cols = 9 + this.colIden;
	// Instanciamos una tabla de x columnas
	PdfPTable tabla = new PdfPTable(cols);
	float[] widths = new float[cols];
	for (int i = 0; i < widths.length; i++) {
	    if (i == 0) {
		widths[i] = 0.1F;
	    } else if (i == 2) {
		widths[i] = 0.31F;
	    } else if (i == 4) {
		widths[i] = 0.31F;
	    } else if (i == widths.length - 4) {
		widths[i] = 0.18F;
	    } else if (i == widths.length - 3) {
		widths[i] = 0.12F;
	    } else {
		widths[i] = 0.3F;
	    }
	}
	tabla.setTotalWidth(PageSize.A4.rotate().getWidth());
	tabla.setWidths(widths);
	tabla.setLockedWidth(true);

	// Declaramos un objeto para manejar las celdas
	PdfPCell celda;
	titulo.add(ResourceBundle.getBundle("global" + lengua).getString(
		"pdf.iden"));
	celda = new PdfPCell(new Phrase(titulo));
	// La celda tomará seis espacios horizontales
	celda.setColspan(cols);

	// TÍTULOS
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.id"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.nombre"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.descripcion"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.responsable"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.probabilidad"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	for (int i = 0; i < this.colIden; i++) {
	    parrafoTitulo.clear();
	    parrafoTitulo.add(impactos.get(i).getObjetivo());
	    celda = new PdfPCell(new Phrase(parrafoTitulo));
	    celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.impactoValor"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(this.corte);
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.response"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.notes"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setRowspan(2);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	// 2 FILA
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.impacto"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setColspan(this.colIden);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("iden.priorizacion"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	tabla.addCell(celda);

	// DATOS
	for (int i = 0; i < iden.size(); i++) {
	    parrafo.clear();
	    parrafo.add(String.valueOf(iden.get(i).getId()));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    parrafo.clear();
	    parrafo.add(iden.get(i).getNombre());
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    parrafo.clear();
	    parrafo.add(iden.get(i).getDescripcion());
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    parrafo.clear();
	    parrafo.add(iden.get(i).getResponsable());
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    parrafo.clear();
	    for (int j = 0; j < probabilidad.size(); j++) {
		if (iden.get(i).getProbabilidad() == probabilidad.get(j)
			.getPorcentaje()) {
		    parrafo.add(probabilidad.get(j).getProbabilidad());
		}
	    }
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    String[] impts = iden.get(i).getImpacto().split(" ");
	    for (int k = 0; k < impts.length; k++) {
		parrafo.clear();
		for (int j = 0; j < impactos.get(0).getProbabilidad().size(); j++) {
		    if (Integer.parseInt(impts[k]) == impactos.get(0)
			    .getProbabilidad().get(j).getPorcentaje()) {
			parrafo.add(impactos.get(0).getProbabilidad().get(j)
				.getProbabilidad());
			break;
		    }
		}
		celda = new PdfPCell(new Phrase(parrafo));
		celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
		celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
		tabla.addCell(celda);
	    }
	    parrafo.clear();
	    parrafo.add(String.valueOf(iden.get(i).getValor()));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    celda = new PdfPCell(new Phrase(""));
	    if (iden.get(i).getValor() > Double.parseDouble(corte)) {
		celda.setBackgroundColor(BaseColor.RED);
	    } else {
		celda.setBackgroundColor(BaseColor.GREEN);
	    }
	    tabla.addCell(celda);

	    parrafo.clear();
	    parrafo.add(String.valueOf(iden.get(i).getResponse()));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	    parrafo.clear();
	    parrafo.add(String.valueOf(iden.get(i).getNotes()));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}
	return tabla;
    }

    /**
     * Método para crear la tabla para la matriz de los planes de riesgos
     * 
     * @param lengua
     *            String para saber el idioma en que se escribira el contenido
     * @return PdfPTable con la tabla que se insertará en el documento pdf
     */
    @SuppressWarnings("static-access")
    private PdfPTable tablaPlanMatrix(String lengua) {
	Paragraph parrafoTitulo = new Paragraph();
	parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
	parrafoTitulo.setFont(FontFactory.getFont("Sans", 12, Font.BOLD,
		BaseColor.BLACK));

	Paragraph parrafo = new Paragraph();
	parrafo.setAlignment(Paragraph.ALIGN_CENTER);
	parrafo.setFont(FontFactory.getFont("Sans", 12, Font.NORMAL,
		BaseColor.BLACK));

	// Instanciamos una tabla de 8 columnas
	PdfPTable tabla = new PdfPTable(8);

	// Declaramos un objeto para manejar las celdas
	PdfPCell celda;
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("pdf.proba"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	// La celda tomará seis espacios horizontales
	celda.setRowspan(5);
	celda.setRotation(90);
	celda.setHorizontalAlignment(parrafoTitulo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafoTitulo.ALIGN_MIDDLE);
	// Agregamos la celda a la tabla
	tabla.addCell(celda);

	// Agregamos una frase a la celda
	for (int i = 0; i < info.getProbabilidad().size(); i++) {
	    parrafo.clear();
	    parrafo.add(info.getProbabilidad().get(i).getProbabilidad());
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    Double porcentajeP = (double) (info.getProbabilidad().get(i)
		    .getPorcentaje()) / 100;
	    parrafo.clear();
	    parrafo.add(String.valueOf((double) Math.round(porcentajeP * 100) / 100));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);

	    for (int j = 0; j < info.getImpacto().get(0).getProbabilidad()
		    .size(); j++) {
		Double porcentajeI = (double) (info.getImpacto().get(0)
			.getProbabilidad().get(j).getPorcentaje()) / 100;
		parrafo.clear();
		Double total = porcentajeP * porcentajeI;

		String rango = info.getRango();
		String[] rangos = rango.split(",");

		parrafo.add(String.valueOf(String.valueOf((double) Math
			.round(total * 100) / 100)));
		celda = new PdfPCell(new Phrase(parrafo));
		celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
		celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
		if (total < Double.valueOf(rangos[0])) {
		    // Verde
		    celda.setBackgroundColor(BaseColor.GREEN);
		} else if (total >= Double.valueOf(rangos[0])
			&& total <= Double.valueOf(rangos[1])) {
		    // Amarillo
		    celda.setBackgroundColor(BaseColor.YELLOW);
		} else if (total > Double.valueOf(rangos[1])) {
		    // Rojo
		    celda.setBackgroundColor(BaseColor.RED);
		}
		tabla.addCell(celda);
	    }
	}
	tabla.addCell("");
	tabla.addCell("");
	tabla.addCell("");
	for (int i = 0; i < info.getImpacto().get(0).getProbabilidad().size(); i++) {
	    parrafo.clear();
	    Double porcentajeI = (double) (info.getImpacto().get(0)
		    .getProbabilidad().get(i).getPorcentaje()) / 100;

	    parrafo.add(String.valueOf((double) Math.round(porcentajeI * 100) / 100));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}
	tabla.addCell("");
	tabla.addCell("");
	tabla.addCell("");
	for (int i = 0; i < info.getImpacto().get(0).getProbabilidad().size(); i++) {
	    parrafo.clear();
	    parrafo.add(String.valueOf(info.getImpacto().get(0)
		    .getProbabilidad().get(i).getProbabilidad()));
	    celda = new PdfPCell(new Phrase(parrafo));
	    celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	    celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	    tabla.addCell(celda);
	}
	parrafoTitulo.clear();
	parrafoTitulo.add(ResourceBundle.getBundle("global" + lengua)
		.getString("pdf.impa"));
	celda = new PdfPCell(new Phrase(parrafoTitulo));
	// La celda tomará seis espacios horizontales
	celda.setColspan(8);
	// Agregamos la celda a la tabla
	celda.setHorizontalAlignment(parrafo.ALIGN_CENTER);
	celda.setVerticalAlignment(parrafo.ALIGN_MIDDLE);
	tabla.addCell(celda);
	return tabla;
    }

    /**
     * Método para crear la tabla para los impactos de los planes de riesgos
     * 
     * @param lengua
     *            String para saber el idioma en que se escribira el contenido
     * @param parrafoH1
     *            Paragraph que define estilos del parrafo
     * @return PdfPTable con la tabla que se insertará en el documento pdf
     */
    private PdfPTable tablaPlanImpacto(String lengua, Paragraph parrafoH1) {
	// Instanciamos una tabla de 2 columnas
	PdfPTable tabla = new PdfPTable(6);

	// Declaramos un objeto para manejar las celdas
	PdfPCell celda;

	// Agregamos una frase a la celda
	parrafoH1.clear();
	parrafoH1.add(ResourceBundle.getBundle("global" + lengua).getString(
		"pdf.impacto"));
	celda = new PdfPCell(new Phrase(parrafoH1));
	// La celda tomará seis espacios horizontales
	celda.setColspan(6);
	// Agregamos la celda a la tabla
	tabla.addCell(celda);

	// Agregamos una frase a la celda
	celda = new PdfPCell(new Phrase(ResourceBundle.getBundle(
		"global" + lengua).getString("pdf.objetivos")));
	tabla.addCell(celda);

	for (int i = 0; i < info.getImpacto().size() + 1; i++) {
	    if (i > 0) {
		celda = new PdfPCell(new Phrase(info.getImpacto().get(i - 1)
			.getObjetivo()));
		// agregamos la celta a la tabla
		tabla.addCell(celda);
	    }
	    for (int j = 0; j < info.getProbabilidad().size(); j++) {
		if (i == 0) {
		    celda = new PdfPCell(new Phrase(info.getImpacto().get(i)
			    .getProbabilidad().get(j).getProbabilidad()
			    + " / "
			    + info.getImpacto().get(i).getProbabilidad().get(j)
				    .getPorcentaje() + "%"));
		    // agregamos la celta a la tabla
		    tabla.addCell(celda);
		} else {
		    celda = new PdfPCell(new Phrase(info.getImpacto()
			    .get(i - 1).getProbabilidad().get(j)
			    .getDescripcion()));
		    // agregamos la celta a la tabla
		    tabla.addCell(celda);
		}
	    }
	}
	return tabla;
    }

    /**
     * Método para crear la tabla para las probabilidades de los planes de
     * riesgos
     * 
     * @param lengua
     *            String para saber el idioma en que se escribira el contenido
     * @param parrafoH1
     *            Paragraph que define estilos del parrafo
     * @return PdfPTable con la tabla que se insertará en el documento pdf
     */
    private PdfPTable tablaPlanProbabilidad(String lengua, Paragraph parrafoH1) {
	// Instanciamos una tabla de 2 columnas
	PdfPTable tabla = new PdfPTable(2);

	// Declaramos un objeto para manejar las celdas
	PdfPCell celda;

	// Agregamos una frase a la celda
	parrafoH1.clear();
	parrafoH1.add(ResourceBundle.getBundle("global" + lengua).getString(
		"pdf.probabilidad"));
	celda = new PdfPCell(new Phrase(parrafoH1));
	// La celda tomará dos espacios horizontales
	celda.setColspan(2);
	// Agregamos la celda a la tabla
	tabla.addCell(celda);

	// Agregamos una frase a la celda
	for (int i = 0; i < info.getProbabilidad().size(); i++) {
	    celda = new PdfPCell(new Phrase(info.getProbabilidad().get(i)
		    .getProbabilidad()
		    + " "
		    + info.getProbabilidad().get(i).getPorcentaje()
		    + " %"));
	    // agregamos la celta a la tabla
	    tabla.addCell(celda);
	    tabla.addCell(info.getProbabilidad().get(i).getDescripcion());
	}

	return tabla;
    }

    /**
     * Método para crear el filename del pdf
     * 
     * @param filename
     *            String con el nombre del fichero a crear
     * @return String con el nombre del fichero pdf
     */
    private String createPath(String filename){
	
	filename = filename.replace(" ", "_");
	filename = filename + "_" + fecha() + ".pdf";
	
	return filename;	
    }

    /**
     * Método para cambiar el formato UTF-8 a ISO-8859-1
     * 
     * @param s
     *            String con la cadena a convertir
     * @return String con la conversión realizada
     */
    public static String convertFromUTF8(String s) {
	String out = null;
	try {
	    out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
	} catch (java.io.UnsupportedEncodingException e) {
	    return null;
	}
	return out;
    }

    /**
     * Método para generar fechas
     * 
     * @return String con un formato de fecha y hora
     */
    private String fecha() {
	// Instanciamos el objeto Calendar
	// en fecha obtenemos la fecha y hora del sistema
	Calendar fecha = new GregorianCalendar();
	// Obtenemos el valor del año, mes, día,
	// hora, minuto y segundo del sistema
	// usando el método get y el parámetro correspondiente
	String yyyy = String.valueOf(fecha.get(Calendar.YEAR));
	String mm = String.valueOf(fecha.get(Calendar.MONTH));
	String dd = String.valueOf((fecha.get(Calendar.DAY_OF_MONTH) + 1));
	String h = String.valueOf(fecha.get(Calendar.HOUR_OF_DAY));
	String m = String.valueOf(fecha.get(Calendar.MINUTE));
	String s = String.valueOf(fecha.get(Calendar.SECOND));
	return (dd + (mm + 1) + (yyyy) + h + m + s);
    }

    /**
     * Clase para manejar los Header y los Footer. Toma los metodos de
     * PdfPageEventHelper
     * 
     * @author Pablo
     * 
     */
    class HeaderFooter extends PdfPageEventHelper {

	// Declaramos la imagen y texto de la cabecera
	Phrase imgCabecera;
	Phrase txtCabecera;
	Image imagen;
	// Declaramos el titulo del proyecto de página como un Phrase
	Phrase tituloProyecto = new Phrase();
	// Declaramos la fecha del proyecto de página como un Phrase
	Phrase fechaProyecto = new Phrase();
	// Declaramos el pie de página como un Phrase
	Phrase pie = new Phrase();
	// Declaramos el autor como un Phrase
	Phrase autor = new Phrase();
	// Declaramos la version como un Phrase
	Phrase version = new Phrase();
	// Declaramos el tituloPie como un Phrase
	Phrase tituloPie = new Phrase();
	// Idioma
	String lengua = "";
	// Autor
	String miembro = "";
	// Versión
	String numVersion = "";

	/**
	 * Constructor para manejar los header y footer del pdf
	 * 
	 * @param lengua
	 *            String para saber el idioma en que se escribira el
	 *            contenido
	 * @param miembro
	 *            String del creador del documento
	 * @param version
	 *            String del número de la versión
	 */
	private HeaderFooter(String lengua, String miembro, String version) {
	    this.lengua = lengua;
	    this.miembro = miembro;
	    this.numVersion = version;
	}

	/**
	 * Método para el evento que se ejecuta en cada nueva pagina del pdf
	 * 
	 * @param writer
	 *            objeto PdfWriter para escribir el pdf
	 * @param document
	 *            objeto Document que es el propio pdf
	 */
	@Override
	public void onStartPage(PdfWriter writer, Document document) {

	    Paragraph parrafo = new Paragraph();
	    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
	    parrafo.setFont(FontFactory.getFont("Sans", 10, Font.NORMAL,
		    BaseColor.BLACK));

	    try {

		// Asignamos el contenido del titulo al comienzo de página
		parrafo.clear();
		parrafo.add(ResourceBundle.getBundle("global" + lengua)
			.getString("pdf.proyecto") + ": " + nProyecto);
		tituloProyecto = new Phrase(parrafo);

		// Asignamos el contenido del titulo al comienzo de página
		parrafo.clear();
		parrafo.add(ResourceBundle.getBundle("global" + lengua)
			.getString("pdf.fecha") + ": " + fecha);
		fechaProyecto = new Phrase(parrafo);

		// Obtenemos la imagen
		Image imagen = Image.getInstance(".." + File.separator
			+ "proyecto" + File.separator + "TFM" + File.separator
			+ "WebContent" + File.separator + "img"
			+ File.separator + "uniovi.png");

		// Convertimos la imagen a un Chunck
		// Chunck es la parte mas pequeña que puede ser añadida a un
		// documento
		Chunk chunk = new Chunk(imagen, 0, -50);

		// Convertimos el Chunk en un Phrase
		// Phrase es una serie de Chunks
		imgCabecera = new Phrase(chunk);
		// parrafoTitulo.clear();

		// Agregamos tambien un texto que acompañe la imagen
		txtCabecera = new Phrase(parrafo);

	    } catch (BadElementException be) {
		be.getMessage();
	    } catch (IOException e) {
		e.getMessage();
	    }

	    // Creamos un objeto PdfContentByte donde se guarda el contenido
	    // de una página en el pdf. Gráficos y texto.
	    PdfContentByte cb = writer.getDirectContent();

	    // Agregamos la imagen y el texto al documento
	    // con la siguiente nomenclaruta
	    // ColumnText.showTextAligned(lienzo, alineacion, Phrase,
	    // posicion x, posicion y, rotacion);

	    // Titulo del proyecto
	    ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, tituloProyecto,
		    document.left() - 50, document.top() + 30, 0);

	    // Fecha del proyecto
	    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, fechaProyecto,
		    document.right() + 50, document.top() + 30, 0);

	    // Esta es la imágen
	    ColumnText.showTextAligned(
		    cb,
		    Element.ALIGN_CENTER,
		    imgCabecera,
		    (document.right() - document.left()) / 2
			    + document.leftMargin(), document.top() + 60, 0);
	}

	/**
	 * Método para el evento que se ejecuta al terminar una página
	 * 
	 * @param writer
	 *            objeto PdfWriter para escribir el pdf
	 * @param document
	 *            objeto Document que es el propio pdf
	 */
	@Override
	public void onEndPage(PdfWriter writer, Document document) {

	    Paragraph parrafoPie = new Paragraph();
	    parrafoPie.setAlignment(Paragraph.ALIGN_CENTER);
	    parrafoPie.setFont(FontFactory.getFont("Sans", 10, BaseColor.GRAY));

	    // Creamos un objeto PdfContentByte donde se guarda el contenido
	    // de una página en el pdf. Gráficos y texto.
	    PdfContentByte cb = writer.getDirectContent();

	    // Asignamos el contenido al pie de página
	    // getCurrentPageNumber() regresa la página actual
	    parrafoPie.add(String.format(
		    ResourceBundle.getBundle("global" + lengua).getString(
			    "pdf.pagina")
			    + " %d", writer.getCurrentPageNumber()));
	    pie = new Phrase(parrafoPie);

	    // Asignamos el contenido del autor al pie de página
	    parrafoPie.clear();
	    parrafoPie.add(miembro);
	    autor = new Phrase(parrafoPie);

	    // Asignamos el contenido de la version al pie de página
	    parrafoPie.clear();
	    parrafoPie.add(numVersion);
	    version = new Phrase(parrafoPie);

	    // Asignamos el contenido del titulo al pie de página
	    parrafoPie.clear();
	    if (info != null) {
		parrafoPie.add(ResourceBundle.getBundle("global" + lengua)
			.getString("pdf.plan"));
	    } else if (iden != null) {
		parrafoPie.add(ResourceBundle.getBundle("global" + lengua)
			.getString("pdf.iden"));
	    } else if (resp != null) {
		parrafoPie.add(ResourceBundle.getBundle("global" + lengua)
			.getString("pdf.resp"));
	    }
	    tituloPie = new Phrase(parrafoPie);

	    // Agregamos el pie a la página
	    // con la siguiente nomenclaruta
	    // ColumnText.showTextAligned(lienzo, alineacion, Phrase,
	    // posicion x, posicion y, rotacion);

	    // Titulo
	    ColumnText.showTextAligned(
		    cb,
		    Element.ALIGN_CENTER,
		    tituloPie,
		    (document.right() - document.left()) / 2
			    + document.leftMargin(), document.bottom() - 10, 0);

	    // Pie
	    ColumnText.showTextAligned(
		    cb,
		    Element.ALIGN_CENTER,
		    pie,
		    (document.right() - document.left()) / 2
			    + document.leftMargin(), document.bottom() - 20, 0);

	    // Autor
	    ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, autor,
		    document.left(), document.bottom() - 20, 0);

	    // Version
	    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, version,
		    document.right(), document.bottom() - 20, 0);
	}
    }

    /**
     * Constructor para la creación de los pdf para los planes de riesgos
     * 
     * @param info
     *            objecto Info donde se encuentra la información del plan de
     *            riesgos
     * @param u
     *            objeto usuario
     * @param project
     *            objeto proyecto
     * @param file
     *            cadena con el nombre del fichero
     * @return String con el path del fichero creado
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    public CreatePDF(Info info, User u, Project project, String file, HttpServletResponse response)
	    throws FileNotFoundException, DocumentException, IOException {
	this.nProyecto = project.getNombre();
	this.fecha = project.getFecha();
	this.lenguaje = u.getLanguage().toLowerCase();
	this.version = String.valueOf(info.getVersion());
	this.autor = u.getLogin();
	this.titulo = file;
	this.info = info;
	this.file = file;
	this.response = response;
    }

    /**
     * Constructor para la creación de los pdf para los riesgos identificados
     * 
     * @param iden
     *            objecto Iden donde se encuentra la información de la
     *            identificación de riesgos
     * @param u
     *            objeto usuario
     * @param project
     *            objeto proyecto
     * @param file
     *            cadena con el nombre del fichero
     * @param impactos
     *            vector con los impactos
     * @param probabilidad
     *            vector con las probabilidades
     * @param corte
     *            entero con el número de corte de los riesgos
     * @return String con el path del fichero creado
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    public CreatePDF(Vector<Iden> iden, User u, Project project,
	    String file, Vector<Impacto> impactos,
	    Vector<Probabilidad> probabilidad, Double corte, HttpServletResponse response)
	    throws FileNotFoundException, DocumentException, IOException {
	this.nProyecto = project.getNombre();
	this.fecha = project.getFecha();
	this.lenguaje = u.getLanguage().toLowerCase();
	this.version = String.valueOf(iden.get(0).getVersion());
	this.autor = u.getLogin();
	this.titulo = file;
	this.iden = iden;
	this.colIden = impactos.size();
	this.impactos = impactos;
	this.probabilidad = probabilidad;
	this.corte = String.valueOf(corte);
	this.file = file;
	this.response = response;
    }

    /**
     * Constructor para la creación de los pdf para las respuestas de riesgos
     * 
     * @param resp
     *            objecto respuesta donde se encuentra la información
     * @param u
     *            objeto usuario
     * @param project
     *            objeto proyecto
     * @param file
     *            cadena con el nombre del fichero
     * @param version
     *            cadena con la versión
     * @param impactos
     *            vector con los impactos
     * @param probabilidad
     *            vector con las probabilidades
     * @param corte
     *            entero con el número de corte de los riesgos
     * @return String con el path del fichero creado
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    public CreatePDF(Respuesta resp, User u, Project project,
	    String file, Double version, Vector<Impacto> impactos,
	    Vector<Probabilidad> probabilidad, Double corte, HttpServletResponse response)
	    throws FileNotFoundException, DocumentException, IOException {
	this.nProyecto = project.getNombre();
	this.fecha = project.getFecha();
	this.lenguaje = u.getLanguage().toLowerCase();
	this.version = String.valueOf(version);
	this.autor = u.getLogin();
	this.titulo = file;
	this.resp = resp;
	this.colIden = impactos.size();
	this.impactos = impactos;
	this.probabilidad = probabilidad;
	this.corte = String.valueOf(corte);
	this.file = file;
	this.response = response;
    }
}

