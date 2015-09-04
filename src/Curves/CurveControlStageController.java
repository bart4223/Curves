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
            NGObjectRequestItem aRequest = newObjectRequest("Curve", "CurrentCurve");
            ComboboxText text = (ComboboxText)cbCurves.getSelectionModel().getSelectedItem();
            aRequest.addParam("aID", text.getId());
            Invoke(aRequest);
        }
    }

    public void setCurrentCurve(CustomCurve aCurve) {
        for (Object obj : cbCurves.getItems()) {
            ComboboxText text = (ComboboxText)obj;
            if (text.getId().equals(aCurve.getID())) {
                cbCurves.getSelectionModel().select(text);
                cbCurves.setTooltip(new Tooltip(aCurve.getDescription()));
                break;
            }
        }
    }

    public void addCurve(CustomCurve aCurve) {
        ComboboxText text = new ComboboxText(aCurve.getID(), aCurve.getName());
        cbCurves.getItems().add(text);
    }

    public void CurveChanged(CustomCurve aCurve) {
        for (Object obj : cbCurves.getItems()) {
            ComboboxText text = (ComboboxText)obj;
            if (text.getId().equals(aCurve.getID()) && text.getName() != aCurve.getName()) {
                ComboboxText newtext = new ComboboxText(aCurve.getID(), aCurve.getName());
                cbCurves.getItems().add(newtext);
                if (cbCurves.getSelectionModel().getSelectedItem().equals(text))
                    cbCurves.getSelectionModel().select(newtext);
                cbCurves.getItems().remove(text);
                break;
            }
        }
    }

}
