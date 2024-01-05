package controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.GestorException;

public class GestorReportes {

    private static final String RUTA_PDF = "./reportes/";

    public void GenerarReporte(String nombre, String apellido, String sexo, Date fechaNacimiento, String descripcion) {

        try {
            // Crear el directorio
            File directorio = new File(RUTA_PDF);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Fecha ~ Nombre del Archivo
            SimpleDateFormat formatoFechaArchivo = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fechaActualArchivo = formatoFechaArchivo.format(new Date());

            // Fecha ~ Pie de Página
            SimpleDateFormat formatoFechaPie = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
            String fechaActualPie = formatoFechaPie.format(new Date());
            
            String nombreArchivo = RUTA_PDF + "Reporte_" + fechaActualArchivo + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();

            // Título
            Font fontTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Información Personal", fontTitulo);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titulo);

            // Contenido
            document.add(new Paragraph("\nNombre: " + nombre));
            document.add(new Paragraph("\nApellido: " + apellido));
            document.add(new Paragraph("\nSexo: " + sexo));
            SimpleDateFormat formatoFechaNac = new SimpleDateFormat("dd/MM/yyyy");
            String fechaNacimientoFormateada = formatoFechaNac.format(fechaNacimiento);
            document.add(new Paragraph("\nFecha de Nacimiento: " + fechaNacimientoFormateada));
            if (!(descripcion.isEmpty())) document.add(new Paragraph("\n\nDescripción: \n" + descripcion));

            // Pie de Página
            Font fontPiePagina = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Paragraph piePagina = new Paragraph("\n\n\nReporte Generado: " + fechaActualPie, fontPiePagina);
            piePagina.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(piePagina);
            
            document.close();
            AbrirReporte(nombreArchivo);
        } catch (Exception e) {
            throw new GestorException("Error: Generar Reporte", e);
        }
    }

    public void AbrirReporte(String ruta) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File(ruta));
            } else {
                throw new GestorException("Error: Abrir Reporte");
            }
        } catch (Exception e) {
            throw new GestorException("Error: Abrir Reporte", e);
        }
    }
}
