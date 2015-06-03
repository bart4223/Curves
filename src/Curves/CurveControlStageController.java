package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class CurveControlStageController extends NGStageController {

    @FXML
    protected void handleAdd(){
        // ToDo
        System.out.println("OK");
    }

    public CurveControlStageController() {
        this(null);
    }

    public CurveControlStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

}
