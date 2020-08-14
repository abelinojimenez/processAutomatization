/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticaciondeproceso;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author wwwca
 */

public class Generarpdf {
    
    // Fonts definitions (Definición de fuentes).
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
         
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
     
    private static final String iTextExampleImage = new File ("logo.png").getAbsolutePath () ;//"C:/Users/wwwca/Documents/NetBeansProjects/automaticaciondeproceso/logo.png";
    /**
     * We create a PDF document with iText using different elements to learn 
     * to use this library.
     * Creamos un documento PDF con iText usando diferentes elementos para aprender 
     * a usar esta librería.
     * @param pdfNewFile  <code>String</code> 
     *      pdf File we are going to write. 
     *      Fichero pdf en el que vamos a escribir. 
     * 
     */
   
    public void createPdfs(String filename,String facultad,ResultSet rs) throws IOException, DocumentException, SQLException
    {        
      //  Document document = new Document(PageSize.A4.rotate(), 36, 36, 54+100, 36);
        Document document = new Document(PageSize.A4.rotate(), 10, 10, 54+100,  36);
        
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Cabecera encabezado = new Cabecera();
        Paragraph parrafo;
        encabezado.setEncabezado("Universidad Mariano Galvez de Guatemala\nASISTENCIA DE CATEDRATICOS\nCentro Universitario de:\n Chiquimulilla, Santa Rosa\nFacultad : "+facultad);
       // encabezado.setEncabezado("Universidad Mariano Galvez de Guatemala");
        
        
        
        // indicamos que objecto manejara los eventtos al escribir el Pdf
        writer.setPageEvent(encabezado);
        
        document.open();
        
        //Creamos una cantidad significativa de paginas para probar el encabezado
        PdfPTable table=new PdfPTable(4);
        float[] with={45.4f,55.4f,30.3f,30.5f};
        table.setWidths(with);
       
        
        PdfPCell cell=new PdfPCell(new Paragraph("Nombre del Catedratico(a)"));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setBackgroundColor(BaseColor.BLUE);
        table.addCell(cell);
        cell=new PdfPCell(new Paragraph("Curso(s) que atiende "));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell=new PdfPCell(new Paragraph("Firma"));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell=new PdfPCell(new Paragraph("Fecha"));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
        int ultimoid=-1;
        String nombre="";
        String Materias="";
        boolean ultimo=false;
         int i=0;
         boolean firt=true;
          Calendar c1=Calendar.getInstance();
     String fecha=""+ Integer.toString(c1.get(Calendar.DATE))+"/"+Integer.toString(c1.get(Calendar.MONTH)+1)+"/"+Integer.toString(c1.get(Calendar.YEAR));
        
    PdfPTable subtable=new PdfPTable(2);
    subtable.setWidths(new float[]{3,23});
       while (rs.next())
               {   
                   
                   if(firt){
                       i++;
                       firt=false;
                       ultimo=false;
                       ultimoid=rs.getInt(1);
                       nombre=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                       Materias+=(i+"- "+rs.getString(6)+". \n");
                       //
                            cell=new PdfPCell(new Paragraph(String.valueOf(i)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                            cell=new PdfPCell(new Paragraph(rs.getString(6)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                       
                       //
                       
                       
                   }else if(ultimoid==rs.getInt(1)){
                      i++;
                     ultimo=false;
                    Materias+=(i+"- "+rs.getString(6)+". \n");
                    
                    //---------------------
                            cell=new PdfPCell(new Paragraph(String.valueOf(i)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                            cell=new PdfPCell(new Paragraph(rs.getString(6)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                    //-------------------
                
                     }else{
                         ultimo=true;
                         table.addCell(nombre);
                        // table.addCell(Materias);
                        i++;
                        for(;i<=4;i++){
                                     //---------------------
                            cell=new PdfPCell(new Paragraph(String.valueOf(i)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                            cell=new PdfPCell(new Paragraph(""));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                    //-------------------
                         }
                        //-----------------------
                            cell=new PdfPCell(subtable);
                            cell.setColspan(1);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        //-----------------------
                         table.addCell("");
                         
                         cell=new PdfPCell(new Paragraph(fecha));
                         cell.setColspan(1);
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         
                         table.addCell(cell);
                         i=1;
                         ultimoid=rs.getInt(1);
                         nombre=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                         Materias=i+"- "+rs.getString(6)+".\n";
                         //---------------------
                            subtable=new PdfPTable(2);
                            subtable.setWidths(new float[]{3,23});
                            cell=new PdfPCell(new Paragraph(String.valueOf(i)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                            cell=new PdfPCell(new Paragraph(rs.getString(6)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                    //-------------------
                         
                         System.out.println(" "+nombre+" "+Materias+" ");
                     }
                     
                        
                }
             //   if(ultimo==false){
                table.addCell(nombre);
                //table.addCell(Materias);
                //-----------------------
                            cell=new PdfPCell(subtable);
                            cell.setColspan(1);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        //-----------------------

                    i++;
                        for(;i<=4;i++){
                                     //---------------------
                            cell=new PdfPCell(new Paragraph(String.valueOf(i)));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                            cell=new PdfPCell(new Paragraph(""));
                            cell.setColspan(1);
                            subtable.addCell(cell);
                    //-------------------
                         }
                                 table.addCell("");
                
                
                
                cell=new PdfPCell(new Paragraph(fecha));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
               // table.addCell(fecha);   
                
                 table.setHorizontalAlignment(Element.ALIGN_CENTER);
             //   
             table.setWidthPercentage(95);
                document.add(table);
       
      
     /*  for(i=0; i < 100; i++)
        {
            
              
        table.addCell("Nombre del Catedratico(a)");

        table.addCell("Curso(s) que atiende");
        table.addCell("Firma");
        table.addCell("Fecha");
        
        document.add(table);
            parrafo = new Paragraph("Esta es una de las paginas de prueba de nuestro programa, es la pagina numero 0x" + String.format("%03X", i+42));            
            parrafo.setAlignment(Element.ALIGN_CENTER);
            
            document.add(parrafo);
            document.newPage();
       
                
        } */
      
        
        document.close();        
    }

    
    public void createPDF(File pdfNewFile) {
        // We create the document and set the file name.        
        // Creamos el documento e indicamos el nombre del fichero.
        try {
            Document document = new Document(PageSize.A4.rotate());
            try {
 
                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile.toString()));
 
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
            document.addSubject("Using iText (usando iText)");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Código Xules");
            document.addCreator("Código Xules");
             
            // First page
            // Primera página 
            Chunk chunk = new Chunk("This is the title", chapterFont);
            chunk.setBackground(BaseColor.GRAY);
            // Let's create de first Chapter (Creemos el primer capítulo)
            Chapter chapter = new Chapter(new Paragraph(chunk), 1);
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph("This is the paragraph", paragraphFont));
            // We add an image (Añadimos una imagen)
            Image image ;
            try {
                image = Image.getInstance(iTextExampleImage);  
                image.setAbsolutePosition(2, 150);
                chapter.add(image);
               
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
            document.add(chapter);
             
            // Second page - some elements
            // Segunda página - Algunos elementos
            Chapter chapSecond = new Chapter(new Paragraph(new Anchor("Some elements (Añadimos varios elementos)")), 1);
            Paragraph paragraphS = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);
             
            // Underline a paragraph by iText (subrayando un párrafo por iText)
            Paragraph paragraphE = new Paragraph("This line will be underlined with a dotted line (Está línea será subrayada con una línea de puntos).");
            DottedLineSeparator dottedline = new DottedLineSeparator();
            dottedline.setOffset(-2);
            dottedline.setGap(2f);
            paragraphE.add(dottedline);
            chapSecond.addSection(paragraphE);
             
            Section paragraphMoreS = chapSecond.addSection(paragraphS);
            // List by iText (listas por iText)
            String text = "test 1 2 3 ";
            for (int i = 0; i < 5; i++) {
                text = text + text;
            }
            List list = new List(List.UNORDERED);
            ListItem item = new ListItem(text);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            text = "a b c align ";
            for (int i = 0; i < 5; i++) {
                text = text + text;
            }
            item = new ListItem(text);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            text = "supercalifragilisticexpialidocious ";
            for (int i = 0; i < 3; i++) {
                text = text + text;
            }
            item = new ListItem(text);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            paragraphMoreS.add(list);
            document.add(chapSecond);
             
            // How to use PdfPTable
            // Utilización de PdfPTable
             
            // We use various elements to add title and subtitle
            // Usamos varios elementos para añadir título y subtítulo
            Anchor anchor = new Anchor("Table export to PDF (Exportamos la tabla a PDF)", categoryFont);
            anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");            
            Chapter chapTitle = new Chapter(new Paragraph(anchor), 1);
            Paragraph paragraph = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);
            Section paragraphMore = chapTitle.addSection(paragraph);
            paragraphMore.add(new Paragraph("This is a simple example (Este es un ejemplo sencillo)"));
            Integer numColumns = 6;
            Integer numRows = 120;
            // We create the table (Creamos la tabla).
            PdfPTable table = new PdfPTable(numColumns); 
            // Now we fill the PDF table 
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader;
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int column = 0; column < numColumns; column++) {
                columnHeader = new PdfPCell(new Phrase("COL " + column));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < numColumns; column++) {
                    table.addCell("Row " + row + " - Col" + column);
                }
            }
            // We add the table (Añadimos la tabla)
            paragraphMore.add(table);
            // We add the paragraph with the table (Añadimos el elemento con la tabla).
            document.add(chapTitle);
            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }   
}
