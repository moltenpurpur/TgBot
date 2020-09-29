import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class Chat extends TelegramLongPollingBot {

    private final static String tokenInFile = "src\\main\\resources\\token.txt";
    private static String token;
    private static String name;
    private final Map<Long, ChatBot> users = new HashMap<>();

    public static void main(String[] args) throws Exception {

        var reader = new BufferedReader(new FileReader(tokenInFile));
        token = reader.readLine();
        name = reader.readLine();

        ApiContextInitializer.init();
        var telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new Chat());

    }

    public void onUpdateReceived(Update update) { //приём сообщений
        try {
            var message = update.getMessage();
            if (message != null && message.hasText()) {
                var messageId = message.getChatId();

                if (message.getText().equals("/start")) {
                    execute(new SendMessage(messageId, ChatBot.getHelp()));
                    users.put(messageId, new ChatBot());
                } else
                    execute(new SendMessage(messageId, users.get(messageId).reply(message.getText())));

            }
        } catch (Exception e) {
            
        }
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return token;
    }
}
