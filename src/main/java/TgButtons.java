import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TgButtons {

    public static ArrayList<KeyboardRow> createButtons(List<String> possibleAnswers) {
        var rows = new ArrayList<KeyboardRow>();
        var maxRowLenght = 2;
        if (possibleAnswers.size() % 2 != 0)
            maxRowLenght = 3;
        var row = new KeyboardRow();
        for (var i = 0; i < possibleAnswers.size(); i++) {
            if (i % maxRowLenght == 0&&i!=0) {
                rows.add(row);
                row = new KeyboardRow();
            }
            row.add(possibleAnswers.get(i));
        }
        if(row.size()!=0)
            rows.add(row);
        return rows;
    }
    public static ArrayList<KeyboardRow> createDefaltButtons()
    {
        var rows = new ArrayList<KeyboardRow>();
        var row = new KeyboardRow();
        row.add("help");
        rows.add(row);
        return rows;
    }

}
