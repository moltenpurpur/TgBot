import java.util.ArrayList;

public class BotAnswer {
    final public String message;
    final public ArrayList<String> possibleAnswers;
    public boolean isLastMessage = false;

    BotAnswer(String message, ArrayList<String> possibleAnswers) {
        this.message = message;
        this.possibleAnswers = possibleAnswers;
    }

    BotAnswer(String message) {
        this.message = message;
        this.possibleAnswers = null;
    }

    BotAnswer(String message, boolean isLastMessage) {
        this.message = message;
        this.possibleAnswers = new ArrayList<>();
        this.possibleAnswers.add("/help");
        this.isLastMessage = isLastMessage;
    }
}
