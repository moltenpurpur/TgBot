import java.util.*;

public class UserTest implements Activity {

    private List<BotAnswer> testQuestions;
    private List<Integer> userAnswers;
    private Map<Integer, String> results = new HashMap<Integer, String>();
    private int index = -1;

    UserTest(TestInfo testInfo) {
        this.testQuestions = testInfo.testQuestions;
        this.results = testInfo.results;
    }

    @Override
    public BotAnswer reply(String message) {
        if (index == -1) {
            userAnswers = new ArrayList<Integer>();
            index++;
            return getQwestion(testQuestions.get(index));
        } else if (index < testQuestions.size() - 1) {
            index++;
            userAnswers.add(Integer.parseInt(message));
            return getQwestion(testQuestions.get(index));
        }
        userAnswers.add(Integer.parseInt(message));
        return getResult();
    }

    private BotAnswer getQwestion(BotAnswer qwestion) {
        StringBuilder s = new StringBuilder(qwestion.message + "\n");
        var answ = new ArrayList<String>();
        for (var i = 0; i < qwestion.possibleAnswers.size(); i++) {
            answ.add(Integer.toString(i + 1));
            s.append(" ").append(qwestion.possibleAnswers.get(i)).append("\n");
        }
        return new BotAnswer(s.toString(), answ);
    }

    private BotAnswer getResult() {
        var sum = 0;
        for (var i : userAnswers) {
            sum += i;
        }
        var finalSum = sum;
        var res = results.keySet().stream().sorted().filter(x -> x >= finalSum).findFirst().get();
        return new BotAnswer(results.get(res), true);
    }

    @Override
    public HashSet<String> getCommands() {
        var commands = new HashSet<String>();
        commands.add("1");
        commands.add("2");
        commands.add("3");
        commands.add("4");
        return commands;
    }
}

