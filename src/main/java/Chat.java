import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


public class Chat extends TelegramLongPollingBot {

    private static String token;
    private static String name;
    private static Loger loger;
    private final Map<Long, ChatBot> users = new HashMap<>();
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public static void main(String[] args) throws Exception {

        var env = System.getenv();
        token = env.get("tgToken");
        name = env.get("tgName");

        loger=new Loger("src\\main\\resources\\log");

        ApiContextInitializer.init();
        var telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new Chat());

    }

    public void onUpdateReceived(Update update) { //приём сообщений
        try {
            var message = update.getMessage();
            if (message != null && message.hasText()) {
                var chatId = message.getChatId();
                var sendMessage = new SendMessage();

                sendMessage.setChatId(chatId);
                if (message.getText().equals("/start")) {
                    var locker = new ReentrantLock();

                    var help = ChatBot.getHelp();

                    sendMessage.setText(help.message);

                    replyKeyboardMarkup.setKeyboard(TgButtons.createButtons(help.possibleAnswers));
                    while (true) {
                        if (locker.tryLock()) {
                            users.put(chatId, new ChatBot());
                            break;
                        }
                    }
                    locker.unlock();
                } else {
                    var reply = users.get(chatId).reply(message.getText());
                    if (reply.possibleAnswers != null)
                        replyKeyboardMarkup.setKeyboard(TgButtons.createButtons(reply.possibleAnswers));
                    sendMessage.setText(reply.message);
                }
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);

                replyKeyboardMarkup = new ReplyKeyboardMarkup();
                execute(sendMessage);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
            loger.Add(e);
        }
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return token;
    }
}
