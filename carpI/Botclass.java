package carpI;

import com.itextpdf.text.DocumentException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Botclass extends TelegramLongPollingBot {
    private Map<Long, String> conversacion = new HashMap<>();

    public String getBotUsername() {
        return "Servicies_Bot";
    }

    @Override
    public String getBotToken() {
        return "6918382953:AAFdXf06XYtVrvmHOrWAq_obBK6zSAp3fq0";
    }

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        if (message.equals("/start")) {
            int length = message.length();
            sendMessage(generateSendMessage(chatId, "Menu de servicios escolares: "));
            sendMessage(generateSendMessage(chatId, "/historial ver historial academico "));
            sendMessage(generateSendMessage(chatId, "/constancia ver constancia de estudios "));
            sendMessage(generateSendMessage(chatId, "/pago de credencial "));
            sendMessage(generateSendMessage(chatId, "/alta de seg social "));
            sendMessage(generateSendMessage(chatId, "/cardex "));

            sendMessage(generateSendMessage(chatId, "Menu de servicios estudiantiles:"));
            sendMessage(generateSendMessage(chatId, "/becas "));
            sendMessage(generateSendMessage(chatId, "/psicologia "));
            sendMessage(generateSendMessage(chatId, "/tutorias "));

        } else if (message.equals("/historial")) {
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.historial("2231232");
                String asunto= "historial_academico";
                pdfs.sendPdfDocument(chatId, pdfFile,asunto);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (message.equals("/constancia")) {
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.constancia("2231232");
                String asunto= "Constancia";
                pdfs.sendPdfDocument(chatId, pdfFile, asunto);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (message.equals("/pago")) {
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.baucher_credencial("2231252");
                String asunto= "baucher_credencial";
                pdfs.sendPdfDocument(chatId, pdfFile,asunto);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (message.equals("/alta")) {
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.alta_SS("2231252");
                String asunto= "Alta_SS";
                pdfs.sendPdfDocument(chatId, pdfFile,asunto);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (message.equals("/cardex")) {
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.cardex("2231252");
                String asunto= "Cardex";
                pdfs.sendPdfDocument(chatId, pdfFile,asunto);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (message.equals("/becas")) {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId.toString());
            InputFile photo = new InputFile();
            photo.setMedia(new File("Imagenes/Becas.png")); // Ruta al archivo de la imagen
            sendPhoto.setPhoto(photo);

            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } //ya
        else if (message.equals("/psicologia")) {
            sendMessage(generateSendMessage(chatId, "se genero una cita psicologia: "));
        }
        else if (message.equals("/tutorias")) {
            sendMessage(generateSendMessage(chatId, "Entraste a asistencia de tutorias: "));
        } else {
            sendMessage(generateSendMessage(chatId, "opcion no valida"));
        }
    }

    private SendMessage generateSendMessage(Long chatId, String characterCount) {
        return new SendMessage(chatId.toString(), characterCount);
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
