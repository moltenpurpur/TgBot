import java.util.Random;

public class QuestionGenerator {

    public static Question GetQuestion()
    {
        var random = new Random();
        var x = random.nextInt(100);
        var y= random.nextInt(100);
        var answer =  x+y;
        return new Question(x+"+"+y,Integer.toString(answer));
    }
}
