import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class TestGetter {

    public static TestInfo getTest(String file){
        try {

            var listBotAnswer = new ArrayList<BotAnswer>();
            var jo = new JSONObject(read(file));

            var qw = jo.getJSONObject("вопросы");
            var keys = qw.keySet();
            for (var key : keys) {
                var w = qw.getJSONArray(key).toList();
                var t = new ArrayList<String>();
                for (var answer : w) {
                    t.add(answer.toString());
                }
                listBotAnswer.add(new BotAnswer(key, t));
            }

            var res = jo.getJSONObject("результаты").toMap();
            var r = new HashMap<Integer, String>();
            for (var key : res.keySet()) {
                r.put(Integer.parseInt(key), res.get(key).toString());
            }

            return new TestInfo(listBotAnswer, r);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static String read(String file) throws Exception {
        var br = new BufferedReader(new FileReader(file));

        return br.readLine();
    }
}

