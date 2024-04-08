package carpI;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Botclass extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "Servicies_Bot";
    }

    @Override
    public String getBotToken(){
        return "6918382953:AAFdXf06XYtVrvmHOrWAq_obBK6zSAp3fq0";
    }


    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        System.out.println("Message Received: " + message);
        int length = message.length();
        System.out.println("The Message have " + length + " characters");
        sendMessage(generateSendMessage(chatId, length));
    }

    private SendMessage generateSendMessage(Long chatId, int characterCount) {
        return new SendMessage(chatId.toString(), "The Message have " + characterCount + " characters");
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
