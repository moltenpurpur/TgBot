import java.util.ArrayList;

public class ChatBot {

    private Activity activity;
    private TestSelector testSelector;


    public BotAnswer reply(String message) throws Exception {

        switch (message) {
            case "/help":
                return getHelp();
            case "/stop":
                activity = null;
                return new BotAnswer("Хорошо");
            case "/getTest":
                throw new Exception("sff");
                //testSelector = new TestSelector();
                //return new BotAnswer(testSelector.getTests());
            default:
                if (testSelector != null && testSelector.tests.containsKey(message)) {
                    activity = testSelector.select(message);
                    testSelector = null;
                    if (activity == null)
                        return new BotAnswer("Тест не найден");
                    return activity.reply("");
                }
                if (activity != null && activity.getCommands().contains(message)) {
                    var w = activity.reply(message);
                    if (w.isLastMessage)
                        activity = null;
                    return w;
                } else
                    return new BotAnswer("Я не знаю такой команды :с\nПопробуй почитать /help", true);
        }
    }


    public static BotAnswer getHelp() {
        var helpMessage ="/getTest";
        var commands = new ArrayList<String>();
        commands.add("/getTest");
        return new BotAnswer(helpMessage,commands);
    }
}
