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
            conversacion.put(chatId, message);
            sendMessage(generateSendMessage(chatId, "Ingresa la matricula: "));
        } else if (conversacion.containsKey(chatId) && conversacion.get(chatId).equals("/historial")) {
            String matricula = message;
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.historial(matricula);
                String asunto = "historial_academico";
                pdfs.sendPdfDocument(chatId, pdfFile, asunto);
                conversacion.remove(chatId);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        else if (message.equals("/constancia")) {
            conversacion.put(chatId, message);
            sendMessage(generateSendMessage(chatId, "Ingresa la matricula: "));
        } else if (conversacion.containsKey(chatId) && conversacion.get(chatId).equals("/constancia")) {
            String matricula = message;
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.constancia(matricula);
                String asunto = "Constancia_estudios";
                pdfs.sendPdfDocument(chatId, pdfFile, asunto);
                conversacion.remove(chatId);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (message.equals("/pago")) {
            conversacion.put(chatId, message);
            sendMessage(generateSendMessage(chatId, "Ingresa la matricula: "));
        } else if (conversacion.containsKey(chatId) && conversacion.get(chatId).equals("/pago")) {
            String matricula = message;
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.baucher_credencial(matricula);
                String asunto = "pago_credencial";
                pdfs.sendPdfDocument(chatId, pdfFile, asunto);
                conversacion.remove(chatId);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (message.equals("/alta")) {
            conversacion.put(chatId, message);
            sendMessage(generateSendMessage(chatId, "Ingresa la matricula: "));
        } else if (conversacion.containsKey(chatId) && conversacion.get(chatId).equals("/alta")) {
            String matricula = message;
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.alta_SS(matricula);
                String asunto = "Alta_SS";
                pdfs.sendPdfDocument(chatId, pdfFile, asunto);
                conversacion.remove(chatId);
            } catch (FileNotFoundException | DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (message.equals("/cardex")) {
            conversacion.put(chatId, message);
            sendMessage(generateSendMessage(chatId, "Ingresa la matricula: "));
        } else if (conversacion.containsKey(chatId) && conversacion.get(chatId).equals("/cardex")) {
            String matricula = message;
            Pdfs pdfs = new Pdfs();
            try {
                File pdfFile = pdfs.cardex(matricula);
                String asunto = "Cadex-historial";
                pdfs.sendPdfDocument(chatId, pdfFile, asunto);
                conversacion.remove(chatId);
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
            photo.setMedia(new File("Imagenes/Becas.png"));
            sendPhoto.setPhoto(photo);

            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (message.equals("/psicologia")) {
            sendMessage(generateSendMessage(chatId, "se genero una cita con la psicologia ve a esta hora: 11:30am"));
        }
        else if (message.equals("/tutorias")) {
            sendMessage(generateSendMessage(chatId, "Se confirmo tu tutoria"));
        }
    }

    public SendMessage generateSendMessage(Long chatId, String characterCount) {
        return new SendMessage(chatId.toString(), characterCount);
    }

    void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void enviar_mensaje_error(){
        Update update = null;
        Long chatId = update.getMessage().getChatId();
        sendMessage(generateSendMessage(chatId, "no valido "));
    }
}
