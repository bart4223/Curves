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
    private ComboBox cbLineSizes;

    @FXML
    protected void handleHelp(){
        Invoke("CurveModule", "Help");
    }

    @FXML
    protected void handleAdd(){

    }

    @FXML
    protected void handleRemove(){
        Invoke("Curve", "RemoveCurrent");
    }

    @FXML
    protected void handleProps(){
        Invoke("CurveModule", "CurveToolbox");
    }

    @FXML
    protected void handlecbCurves(ActionEvent actionEvent) {
        if (actionEvent.getEventType().equals(ActionEvent.ACTION)) {
            NGObjectRequestItem aRequest = newObjectRequest("Curve", "CurrentCurve");
            if (cbCurves.getSelectionModel().getSelectedItem() != null) {
                ComboboxText text = (ComboboxText)cbCurves.getSelectionModel().getSelectedItem();
                aRequest.addParam("aID", text.getId());
                Invoke(aRequest);
            }
        }
    }

    @FXML
    protected void handlecbLineSizes(ActionEvent actionEvent) {
        if (actionEvent.getEventType().equals(ActionEvent.ACTION) && cbLineSizes.getValue() != null) {
            NGObjectRequestItem aRequest = newObjectRequest("Curve", "LineSize");
            Integer lineSize = Integer.parseInt(cbLineSizes.getValue().toString());
            aRequest.addParam("aLineSize", lineSize);
            Invoke(aRequest);
        }
    }

    protected void DoInitialize() {
        super.DoInitialize();
        for (int i = 1; i <= 5; i++)
            cbLineSizes.getItems().add(i);
        cbLineSizes.getSelectionModel().select("1");
    }

    public CurveControlStageController() {
        this(null);
    }

    public CurveControlStageController(NGCustomStageItem aStageItem) {
        super(aStageItem);
    }

    public void setCurrentCurve(CustomCurve aCurve) {
        if (aCurve != null) {
            for (Object obj : cbCurves.getItems()) {
                ComboboxText text = (ComboboxText)obj;
                if (text.getId().equals(aCurve.getID())) {
                    cbCurves.getSelectionModel().select(text);
                    cbCurves.setTooltip(new Tooltip(aCurve.getDescription()));
                    break;
                }
            }
        }
        else
            cbCurves.getSelectionModel().clearSelection();
    }

    public void addCurve(CustomCurve aCurve) {
        ComboboxText text = new ComboboxText(aCurve.getID(), aCurve.getName());
        cbCurves.getItems().add(text);
    }

    public void CurveChanged(CustomCurve aCurve) {
        if (aCurve != null) {
            for (Object obj : cbCurves.getItems()) {
                ComboboxText text = (ComboboxText) obj;
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
        else
            cbCurves.getSelectionModel().clearSelection();
    }

    public void CurveRemoved(CustomCurve aCurve) {
        for (Object obj : cbCurves.getItems()) {
            ComboboxText text = (ComboboxText) obj;
            if (text.getId().equals(aCurve.getID())) {
                cbCurves.getItems().remove(text);
                break;
            }
        }
    }

}
