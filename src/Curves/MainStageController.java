package Curves;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class MainStageController extends NGStageController {

    @FXML
    protected void handleQuit() {
        NGApplication.Application.Invoke("Application", "Quit");
    }

    @FXML
    protected void handleAdd(){
        NGApplication.Application.Invoke("Application", "addCurveModule");
    }

    public MainStageController() {
        this(null);
    }

    public MainStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
