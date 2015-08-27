package Curves;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObjectRequestItem;
import Uniwork.Visuals.NGStageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

public class CurveControlStageController extends NGStageController {

    @FXML
    private ComboBox cbCurves;

    @FXML
    protected void handleAdd(){
    }

    @FXML
    protected void handleRemove(){
    }

    @FXML
    protected void handleProps(){
        Invoke("CurveModule", "CurveToolbox");
    }

    public CurveControlStageController() {
        this(null);
    }

    public CurveControlStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

    public void handlecbCurves(ActionEvent actionEvent) {
        if (actionEvent.getEventType().equals(ActionEvent.ACTION)) {
            if (cbCurves.getValue() != null) {
                NGObjectRequestItem aRequest = newObjectRequest("Curve", "CurrentCurve");
                aRequest.addParam("aName", cbCurves.getValue().toString());
                Invoke(aRequest);
            }
        }
    }

    public void setCurrentCurve(CustomCurve aCurve) {
        cbCurves.getSelectionModel().select(aCurve.getName());
        cbCurves.setTooltip(new Tooltip(aCurve.getDescription()));
    }

    public void addCurve(CustomCurve aCurve) {
        cbCurves.getItems().add(aCurve.getName());
    }

}
