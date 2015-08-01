package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Appl.NGStageManager;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import javafx.stage.Stage;

public class CurveConsoleStageItem extends NGCustomStageItem implements CurveEventListener {

    public CurveConsoleStageItem(NGStageManager aStageManager, String aName, Stage aStage) {
        super(aStageManager, aName, aStage);
        FFXMLName = "CurveConsoleStage.fxml";
        FWidth = 800;
        FHeight = 100;
    }


    @Override
    public void handleCurveAdded(CurveEvent e) {

    }

    @Override
    public void handleCurveCalculated(CurveEvent e) {

    }

    @Override
    public void handleLogAdded(NGLogEvent e) {
        CurveConsoleStageController sc = (CurveConsoleStageController)FStageController;
        sc.addLog(e.LogEntry.GetFullAsString());
    }

    @Override
    public void handleLogClear() {
        CurveConsoleStageController sc = (CurveConsoleStageController)FStageController;
        sc.clearLog();
    }
}
