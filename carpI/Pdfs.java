package carpI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pdfs {
    public void sendPdfDocument(Long chatId, File pdfFile, String asunto) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId.toString());
        String nombreArchivo = asunto + ".pdf";
        InputFile inputFile = new InputFile(pdfFile, nombreArchivo);
        sendDocument.setDocument(inputFile);

        try {
            Botclass bot = new Botclass();
            bot.execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public File constancia(String matricula) throws FileNotFoundException, DocumentException {
        try {
            Paragraph encabezado = new Paragraph();
            Font f=new Font();
            f.setFamily(Font.FontFamily.TIMES_ROMAN.name());
            f.setStyle(Font.BOLD);
            f.setSize(9);
            Chunk texto;
            texto=new Chunk("\n\nAsunto: Constancia de estudios.\n",f);
            encabezado.add(texto);
            Alumnos alumnos = new Alumnos();
            alumnos.setId(Integer.parseInt(matricula));
            alumnos.Buscar_usuario_id(alumnos);


            String path = "constancia_" + matricula + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            Phrase p = new Phrase("Constancia de estudios de " + alumnos.getNombre());
            document.add(p);

            document.close();
            return new File(path);

        } catch (FileNotFoundException | DocumentException exception) {
            throw exception;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public File historial(String matricula) throws FileNotFoundException, DocumentException {
        try {
            String path = "Historial" + matricula + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Paragraph encabezado = new Paragraph();
            Font f = new Font();
            f.setFamily(Font.FontFamily.TIMES_ROMAN.name());
            f.setStyle(Font.BOLD);
            f.setSize(9);
            Chunk texto;
            texto = new Chunk("\n\nAsunto: Historial academico.\n", f);
            encabezado.add(texto);
            document.add(encabezado);
            Alumnos alumnos = new Alumnos();
            alumnos.setId(Integer.parseInt(matricula));
            alumnos.Buscar_usuario_id(alumnos);

            Phrase p = new Phrase("Historial academico de " + alumnos.getNombre());
            document.add(p);

            document.close();
            return new File(path);

        } catch (FileNotFoundException | DocumentException exception) {
            throw exception;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File baucher_credencial(String matricula) throws FileNotFoundException, DocumentException {
        try {
            String path = "Baucher" + matricula + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Paragraph encabezado = new Paragraph();
            Font f = new Font();
            f.setFamily(Font.FontFamily.TIMES_ROMAN.name());
            f.setStyle(Font.BOLD);
            f.setSize(9);
            Chunk texto;
            Alumnos alumnos = new Alumnos();
            alumnos.setId(Integer.parseInt(matricula));
            alumnos.Buscar_usuario_id(alumnos);

            Phrase p = new Phrase("Pago de credencial de " + alumnos.getNombre());
            texto = new Chunk("\n\nAsunto: Pago de credencial.\n Numero de referencia: 1256285548", f);
            //aqui puedo usar P para crear todo el formato de pgo
            encabezado.add(texto);
            document.add(encabezado);
            document.add(p);

            document.close();
            return new File(path);

        } catch (FileNotFoundException | DocumentException exception) {
            throw exception;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public File alta_SS(String matricula) throws FileNotFoundException, DocumentException {
        try {
            String path = "AltaSS_" + matricula + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Paragraph encabezado = new Paragraph();
            Font f = new Font();
            f.setFamily(Font.FontFamily.TIMES_ROMAN.name());
            f.setStyle(Font.BOLD);
            f.setSize(9);
            Chunk texto;
            Alumnos alumnos = new Alumnos();
            alumnos.setId(Integer.parseInt(matricula));
            alumnos.Buscar_usuario_id(alumnos);

            Phrase p = new Phrase("Alta de SS de  " + alumnos.getNombre());
            texto = new Chunk("\n\nAsunto: Alta de SS.\n", f);
            encabezado.add(texto);
            document.add(encabezado);
            document.add(p);

            document.close();
            return new File(path);

        } catch (FileNotFoundException | DocumentException exception) {
            throw exception;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File cardex(String matricula) throws FileNotFoundException, DocumentException {
        try {
            String path = "Cardex_" + matricula + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            Alumnos alumnos = new Alumnos();
            alumnos.setId(Integer.parseInt(matricula));

            if (alumnos.Buscar_usuario_id(alumnos)) {
                Paragraph encabezado = new Paragraph();
                Font f = new Font();
                f.setFamily(Font.FontFamily.TIMES_ROMAN.name());
                f.setStyle(Font.BOLD);
                f.setSize(29);
                Chunk texto;
                Phrase p = new Phrase("Cardex de el/la almn@: " + alumnos.getNombre());
                texto = new Chunk("\n\nAsunto: Cardex.\n", f);
                encabezado.add(texto);
                document.add(encabezado);
                document.add(p);
            } else {
                Botclass botclass = new Botclass();
                botclass.enviar_mensaje_error();
            }

            document.close();
            return new File(path);

        } catch (FileNotFoundException | DocumentException exception) {
            throw exception;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
