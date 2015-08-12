package Curves;

import Uniwork.Misc.NGStrings;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CurveConsoleStageController extends NGStageController {

    @FXML
    private TextArea Log;

    public void addLog(String aText) {
        Log.setText(NGStrings.addString(aText, Log.getText(), "\n"));
    }

    public void clearLog() {
        Log.clear();
    }

}
