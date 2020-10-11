import java.util.ArrayList;
import java.util.Map;

public class TestInfo {
    public ArrayList<BotAnswer> testQuestions;
    public Map<Integer, String> results;

    TestInfo (ArrayList<BotAnswer> listBotAnswer, Map<Integer, String> results){
        this.testQuestions = listBotAnswer;
        this.results = results;
    }
}
