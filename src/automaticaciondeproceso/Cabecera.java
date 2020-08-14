/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticaciondeproceso;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Phrase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wwwca
 */
 
public class Cabecera extends PdfPageEventHelper {
    private String encabezado;
    PdfTemplate total;
    
    /**
     * Crea el objecto PdfTemplate el cual contiene el numero total de
     * paginas en el documento
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
    
    /**
     * Esta es el metodo a llamar cuando ocurra el evento onEndPage, es en este evento
     * donde crearemos el encabeazado de la pagina con los elementos indicados.
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);
        try {
            // Se determina el ancho y altura de la tabla 
            table.setWidths(new int[]{24, 24, 2});
            table.setTotalWidth(527-10);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(90);
            
            
            
            // Borde de la celda
            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
            
                //-------------------------------Agregar la Imagen----------------------------------------------
            
            Image image ;
            String Rutaimagen="C:/Users/wwwca/Documents/NetBeansProjects/automaticaciondeproceso/logo.png";
            image = Image.getInstance(Rutaimagen);  
                
                image.setAbsolutePosition(5, 400);
 
            document.add(image);        
//-------------------------------------------------------------------------------
            
            
             table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(encabezado);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            
           
            table.addCell(String.format("%d de", writer.getPageNumber()));
            
            PdfPCell cell = new PdfPCell(Image.getInstance(total));
            
            cell.setBorder(Rectangle.NO_BORDER);
            
            table.addCell(cell);
            // Esta linea escribe la tabla como encabezado
            table.writeSelectedRows(0-300, -1-300, 34+250, 803-250, writer.getDirectContent());
                // pie de pagina
                
                
    //             table2.writeSelectedRows(0-300, -1-300, 34+250, 803+250, writer.getDirectContent());
    
       
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (IOException ex) {
            Logger.getLogger(Cabecera.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
    }
    
    
    /**
     * Realiza el conteo de paginas al momento de cerrar el documento
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
      //  ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(), 0, 0, 0);7
      Phrase ps = new Phrase();
      ps.add(String.valueOf(writer.getPageNumber()-1));
      ColumnText.showTextAligned(total, Element.ALIGN_MIDDLE,ps, 2, 2, 0);
      
    // Getter and Setters
    }
    public String getEncabezado() {
        return encabezado;
    }
    public void setEncabezado(String encabezado) {
       
        this.encabezado = encabezado;
    }
}
