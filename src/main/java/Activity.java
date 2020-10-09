import java.util.HashSet;

public interface Activity {
    public BotAnswer reply(String message);
    public HashSet<String> getCommands();
}
