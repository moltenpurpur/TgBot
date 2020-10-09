import java.util.HashMap;
import java.util.Map;

public class TestSelector {
    public Map<String,String> tests= new HashMap<String, String>();

    TestSelector()
    {
        tests.put("t1","src\\main\\resources\\test1.json");
        tests.put("t2","src\\main\\resources\\test2.json");
        tests.put("t3","src\\main\\resources\\test3.json");
    }

    public String getTests(){
        StringBuilder s = new StringBuilder();
        s.append("выберите тест:\n");
        for (var name:tests.keySet()) {
            s.append(name).append("\n");
        }
        return s.toString();
    }

    public UserTest select(String testName){
        var testInfo = TestGetter.getTest(tests.get(testName));
        if(testInfo==null)
            return null;
        return new UserTest(testInfo);
    }

}
