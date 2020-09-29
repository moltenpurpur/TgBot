public class ChatBot {

    public Question lastQuestion;
    private int rightCount;
    private int wrongCount;

    ChatBot() {
        rightCount = 0;
        wrongCount = 0;
    }

    public String reply(String message) {
        switch (message){
            case "/help":
                return getHelp();
            case "Скажи счет":
                return getScore();
            case "Дай примеры":
                return getQuestionLine();
            case "Хватит примеров":
                return stopQuestions();
            default:
                if( Utils.isNumeric(message)&& lastQuestion!=null)
                    return CheckAnswer(message);
                else
                    return "Я не знаю такой команды :с\nПопробуй почитать /help";

        }
    }

    private String CheckAnswer(String answer){
        if(answer.equals(lastQuestion.rightAnswer)) {
            lastQuestion=QuestionGenerator.GetQuestion();
            rightCount++;
            return "И это правильный ответ!\nВот тебе ещё 1 пример\n"+getQuestionLine();
        }
        else {
            wrongCount++;
            return "К сожалению ты ошибся, попробуй снова";
        }
    }

    public static String getHelp() {
        return "Это бот который может задавать вопросы\n" +
                "Чтоб попросить у него арифметический пример напиши \"Дай примеры\"\n"+
                "\"/help\" - чтоб попросить помощь\n \"Скажи счет\" - чтоб узнать счёт \n " +
                "\"Хватит примеров\" - чтоб закончить";
    }

    private String stopQuestions()
    {
        lastQuestion=null;
        return "Хорошо";
    }

    private String getQuestionLine() {
        lastQuestion = QuestionGenerator.GetQuestion();
        return lastQuestion.question + "=?";
    }

    private String getScore(){
        return "Количество правильных: "+rightCount+
                "\nКоличество не правильных: "+wrongCount;
    }
}
